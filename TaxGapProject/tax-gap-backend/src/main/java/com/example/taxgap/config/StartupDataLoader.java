
package com.example.taxgap.config;
import com.example.taxgap.domain.entity.*; import com.example.taxgap.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder; import java.util.Set;
@Configuration public class StartupDataLoader {
  @Bean CommandLineRunner initUsers(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder){
    return args -> {
      Role adminRole = roleRepo.findByName("ROLE_ADMIN").orElseGet(() -> { Role r=new Role(); r.setName("ROLE_ADMIN"); return roleRepo.save(r);} );
      Role auditorRole = roleRepo.findByName("ROLE_AUDITOR").orElseGet(() -> { Role r=new Role(); r.setName("ROLE_AUDITOR"); return roleRepo.save(r);} );
      userRepo.findByUsername("admin").orElseGet(() -> { User u=new User(); u.setUsername("admin"); u.setPassword(encoder.encode("admin123")); u.setEnabled(true); u.setRoles(Set.of(adminRole, auditorRole)); return userRepo.save(u);} );
      userRepo.findByUsername("auditor").orElseGet(() -> { User u=new User(); u.setUsername("auditor"); u.setPassword(encoder.encode("auditor123")); u.setEnabled(true); u.setRoles(Set.of(auditorRole)); return userRepo.save(u);} );
    }; }
}
