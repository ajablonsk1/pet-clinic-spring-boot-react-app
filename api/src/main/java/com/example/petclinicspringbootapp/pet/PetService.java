package com.example.petclinicspringbootapp.pet;

import com.example.petclinicspringbootapp.user.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PetService {
    private final PetRepo petRepo;

    public Pet savePet(Pet pet){
        log.info("Saving new pet {} to the database", pet.getName());
        return petRepo.save(pet);
    }

    public List<Pet> getPetsByUser(AppUser user){
        log.info("Fetching {} pets", user.getUsername());
        return petRepo.findPetsByUser(user);
    }

    public List<Pet> getPets(){
        return petRepo.findAll();
    }
}
