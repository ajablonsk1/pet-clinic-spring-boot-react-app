package com.example.petclinicspringbootapp.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>, CustomerCustomRepo {

    Customer findCustomerByEmail(String email);
}
