
package com.example.taxgap.config;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*; import jakarta.servlet.http.*; import java.io.IOException; import java.nio.charset.StandardCharsets; import java.security.Key; import java.util.*; import java.util.stream.Collectors;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails; import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final UserDetailsService uds; private final Key key;
  public JwtAuthenticationFilter(UserDetailsService uds, String secret){ this.uds=uds; this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); }
  @Override protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
    String auth=req.getHeader("Authorization");
    if(auth!=null && auth.startsWith("Bearer ")){
      String token=auth.substring(7);
      try{
        Jws<Claims> jws=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        Claims claims=jws.getBody(); String username=claims.getSubject(); Object rolesClaim=claims.get("roles");
        Collection<String> roles;
        if(rolesClaim instanceof Collection<?> c){ roles=c.stream().map(Object::toString).collect(Collectors.toList()); }
        else if(rolesClaim instanceof String s){ roles=Arrays.stream(s.split(",")).map(String::trim).toList(); }
        else roles=List.of();
        UserDetails user=uds.loadUserByUsername(username);
        var authorities=roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        var authToken=new UsernamePasswordAuthenticationToken(user,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }catch(Exception e){ SecurityContextHolder.clearContext(); }
    }
    chain.doFilter(req,res);
  }
}
