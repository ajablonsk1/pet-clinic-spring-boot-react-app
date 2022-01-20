package com.example.petclinicspringbootapp.customer;

import com.example.petclinicspringbootapp.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>, CustomerCustomRepo {

    Customer findCustomerByEmail(String email);

    Integer deleteCustomerByEmail(String email);
}
