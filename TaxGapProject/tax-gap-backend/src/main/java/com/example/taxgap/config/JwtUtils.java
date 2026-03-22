
package com.example.taxgap.config;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets; import java.security.Key; import java.time.Instant; import java.util.*;
public class JwtUtils {
  private final Key key; private final long expiryMinutes;
  public JwtUtils(String secret,long expiryMinutes){ this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); this.expiryMinutes=expiryMinutes; }
  public String generate(String username, Map<String,Object> claims){ Instant now=Instant.now(); Instant exp=now.plusSeconds(expiryMinutes*60);
    return Jwts.builder().setSubject(username).addClaims(claims).setIssuedAt(Date.from(now)).setExpiration(Date.from(exp)).signWith(key, SignatureAlgorithm.HS256).compact(); }
}
