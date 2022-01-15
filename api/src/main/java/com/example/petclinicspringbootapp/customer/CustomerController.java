package com.example.petclinicspringbootapp.customer;

import com.example.petclinicspringbootapp.pet.Pet;
import com.example.petclinicspringbootapp.user.AppUser;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok().body(customerService.getCustomers());
    }

    @GetMapping("/customer/user")
    public ResponseEntity<Customer> getCustomerByUser(@RequestParam String email){
        return ResponseEntity.ok().body(customerService.getCustomerByUser(email));
    }

    @PostMapping("/customer/save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok().body(customerService.saveCustomer(customer));
    }
}