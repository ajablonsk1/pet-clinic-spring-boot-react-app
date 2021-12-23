package com.example.petclinicspringbootapp.pet;

import com.example.petclinicspringbootapp.customer.Customer;
import com.example.petclinicspringbootapp.user.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class PetConfig {

    @Bean
    CommandLineRunner addPets(PetService petService){
        return args -> {
        };
    }
}
