package com.example.petclinicspringbootapp.pet;

import com.example.petclinicspringbootapp.customer.Customer;
import com.example.petclinicspringbootapp.customer.CustomerRepo;
import com.example.petclinicspringbootapp.user.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PetService {
    private final PetRepo petRepo;
    private final CustomerRepo customerRepo;

    public Pet savePet(Pet pet){
        log.info("Saving new pet {} to the database", pet.getName());
        return petRepo.save(pet);
    }

    public List<Pet> getPetsByUser(AppUser user){
        log.info("Fetching {} pets", user.getEmail());
        return petRepo.findPetsByUser(user);
    }

    public List<Pet> getPets(){
        log.info("Fetching all pets");
        return petRepo.findAll();
    }

    public void addOwnerToPet(Long petId, Long ownerId){
        Optional<Pet> pet = petRepo.findById(petId);
        Optional<Customer> customer = customerRepo.findById(ownerId);
        if(pet.isPresent() && customer.isPresent()){
            pet.get().setOwner(customer.get());
        }
    }
}
