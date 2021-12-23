package com.example.petclinicspringbootapp.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
public class UserConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner addUsersAndRoles(UserService userService){
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_EMPLOYEE"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUser(new AppUser(null, "adrian", "1234", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "tom", "1234", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "ola", "1234", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "will", "1234", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "juliet", "1234", new ArrayList<>()));

            userService.addRoleToUser("adrian", "ROLE_ADMIN");
            userService.addRoleToUser("adrian", "ROLE_EMPLOYEE");
            userService.addRoleToUser("tom", "ROLE_EMPLOYEE");
            userService.addRoleToUser("ola", "ROLE_USER");
            userService.addRoleToUser("will", "ROLE_USER");
            userService.addRoleToUser("juliet", "ROLE_USER");
        };
    }
}
