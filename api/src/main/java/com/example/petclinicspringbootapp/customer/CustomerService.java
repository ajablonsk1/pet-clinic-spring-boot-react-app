package com.example.petclinicspringbootapp.customer;

import com.example.petclinicspringbootapp.appointment.Appointment;
import com.example.petclinicspringbootapp.appointment.AppointmentRepo;
import com.example.petclinicspringbootapp.appointment.AppointmentService;
import com.example.petclinicspringbootapp.pet.Pet;
import com.example.petclinicspringbootapp.pet.PetRepo;
import com.example.petclinicspringbootapp.pet.PetService;
import com.example.petclinicspringbootapp.user.AppUser;
import com.example.petclinicspringbootapp.user.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final UserRepo userRepo;
    private final AppointmentRepo appointmentRepo;
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

    public void addPetToCustomer(String email, Pet pet){
        Customer customer = customerRepo.findCustomerByEmail(email);
        customer.getPets().add(pet);
    }

    public Customer getCustomerByUser(String email){
        Optional<AppUser> appUser = userRepo.findByEmail(email);
        if(appUser.isPresent()){
            return this.customerRepo.findCustomerByUser(appUser.get());
        }
        else {
            throw new UsernameNotFoundException("Customer with given email " + email + " was not found");
        }
    }

    public Integer deleteCustomer(String email){
        Customer customer = customerRepo.findCustomerByEmail(email);
        if(customer == null){
            throw new UsernameNotFoundException("Customer with given email " + email + " was not found");
        }
        petRepo.findAll()
                .stream()
                .filter(pet -> {
                    if(pet.getOwner() != null){
                        return pet.getOwner().equals(customer);
                    } else{
                        return false;
                    }
                })
                .forEach(pet -> pet.setOwner(null));
        appointmentRepo.findAll()
                .stream()
                .filter(appointment -> {
                    if(appointment.getCustomer() != null) {
                        return appointment.getCustomer().getEmail().equals(email);
                    } else{
                        return false;
                    }
                })
                .forEach(appointment -> appointment.setCustomer(null));
        return customerRepo.deleteCustomerByEmail(email);
    }
}
