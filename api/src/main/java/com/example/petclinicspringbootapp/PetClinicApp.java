package com.example.petclinicspringbootapp;

import com.example.petclinicspringbootapp.user.AppUser;
import com.example.petclinicspringbootapp.user.Role;
import com.example.petclinicspringbootapp.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class PetClinicApp {

    public static void main(String[] args) {
        SpringApplication.run(PetClinicApp.class, args);
    }

    @GetMapping("/home")
    public ResponseEntity<?> getHome(){
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/")
    public RedirectView localRedirect() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/home");
        return redirectView;
    }
}
