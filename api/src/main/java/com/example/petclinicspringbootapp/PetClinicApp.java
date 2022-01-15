package com.example.petclinicspringbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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
