
package com.example.taxgap.domain.dto.auth; import java.util.Set; public class LoginResponse { private String token; private String username; private Set<String> roles; public LoginResponse(String t,String u, Set<String> r){token=t;username=u;roles=r;} public String getToken(){return token;} public String getUsername(){return username;} public Set<String> getRoles(){return roles;} }
