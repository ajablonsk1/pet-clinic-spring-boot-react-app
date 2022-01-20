package com.example.petclinicspringbootapp.pet;

import com.example.petclinicspringbootapp.appointment.Appointment;
import com.example.petclinicspringbootapp.appointment.AppointmentRepo;
import com.example.petclinicspringbootapp.customer.Customer;
import com.example.petclinicspringbootapp.customer.CustomerRepo;
import com.example.petclinicspringbootapp.user.AppUser;
import com.example.petclinicspringbootapp.user.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PetService {
    private final PetRepo petRepo;
    private final CustomerRepo customerRepo;
    private final UserRepo userRepo;
    private final AppointmentRepo appointmentRepo;

    public Pet savePet(Pet pet){
        log.info("Saving new pet {} to the database", pet.getName());
        List<Appointment> appointments = new ArrayList<>();
        pet.setAppointments(appointments);
        return petRepo.save(pet);
    }

    public List<Pet> getPetsByUserEmail(String email){
        AppUser user;
        if(userRepo.findByEmail(email).isPresent()){
            user = userRepo.findByEmail(email).get();
        }
        else{
            throw new UsernameNotFoundException("User with given email doesn't exists");
        }
        log.info("Fetching {} pets", user.getEmail());
        return petRepo.findPetsByUser(user);
    }

    public List<Pet> getPets(){
        log.info("Fetching all pets");
        return petRepo.findAll();
    }

    public Pet savePet(OwnerToPetForm ownerToPetForm){
        log.info("Saving new pet {} to the database", ownerToPetForm.getPet().getName());

        List<Appointment> appointments = new ArrayList<>();
        ownerToPetForm.getPet().setAppointments(appointments);

        petRepo.save(ownerToPetForm.getPet());

        Customer customer = customerRepo.findCustomerByEmail(ownerToPetForm.getEmail());

        addOwnerToPet(ownerToPetForm.getPet().getId(), customer.getId());
        customer.getPets().add(ownerToPetForm.getPet());

        return ownerToPetForm.getPet();
    }

    public void addOwnerToPet(Long petId, Long ownerId){
        Optional<Pet> pet = petRepo.findById(petId);
        Optional<Customer> customer = customerRepo.findById(ownerId);
        if(pet.isPresent() && customer.isPresent()){
            pet.get().setOwner(customer.get());
        }
    }

    public Integer deletePet(Long id){
        Pet pet = petRepo.getById(id);
        appointmentRepo.findAll()
                .stream()
                .filter(appointment -> {
                    if(appointment.getPet() != null){
                        return appointment.getPet().equals(pet);
                    } else{
                        return false;
                    }})
                .forEach(appointment -> appointment.setPet(null));
        customerRepo.findCustomerByPet(pet).getPets().remove(pet);
        return petRepo.deletePetById(id);
    }
}
