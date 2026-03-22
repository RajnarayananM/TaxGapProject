
package com.example.taxgap.config;
import com.example.taxgap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.taxgap.domain.entity.User;
import java.util.stream.Collectors;
@Configuration @EnableWebSecurity @EnableMethodSecurity
public class SecurityConfig {
  private final UserRepository userRepo; private final String jwtSecret;
  public SecurityConfig(UserRepository userRepo, @Value("${security.jwt.secret}") String jwtSecret){ this.userRepo=userRepo; this.jwtSecret=jwtSecret; }
  @Bean public UserDetailsService userDetailsService(){ return username -> {
    User u = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new org.springframework.security.core.userdetails.User(
      u.getUsername(), u.getPassword(), u.isEnabled(), true, true, true,
      u.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toSet()));
  }; }
  @Bean public BCryptPasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }
  @Bean public DaoAuthenticationProvider authProvider(){ DaoAuthenticationProvider p=new DaoAuthenticationProvider(); p.setUserDetailsService(userDetailsService()); p.setPasswordEncoder(passwordEncoder()); return p; }
  @Bean public JwtAuthenticationFilter jwtAuthenticationFilter(){ return new JwtAuthenticationFilter(userDetailsService(), jwtSecret); }
  @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf->csrf.disable())
      .authorizeHttpRequests(auth->auth.requestMatchers("/auth/login","/actuator/health").permitAll().anyRequest().authenticated())
      .httpBasic(Customizer.withDefaults());
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
