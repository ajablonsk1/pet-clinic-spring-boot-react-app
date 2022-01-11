package com.example.petclinicspringbootapp.customer;

import com.example.petclinicspringbootapp.appointment.Appointment;
import com.example.petclinicspringbootapp.pet.Pet;
import com.example.petclinicspringbootapp.pet.PetRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final PetRepo petRepo;

    public Customer saveCustomer(Customer customer){
        log.info("Saving new customer {} to the database", customer.getFirstname());
        List<Appointment> appointments = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();
        customer.setAppointments(appointments);
        customer.setPets(pets);
        return customerRepo.save(customer);
    }

    public List<Customer> getCustomers(){
        log.info("Fetching all customers");
        return customerRepo.findAll();
    }

    public void addPetToCustomer(Long customerId, Long petId){
        Optional<Customer> customer = customerRepo.findById(customerId);
        Optional<Pet> pet = petRepo.findById(petId);
        if(customer.isPresent() && pet.isPresent()){
            customer.get().getPets().add(pet.get());
        }
    }
}
