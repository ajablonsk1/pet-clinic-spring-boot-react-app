package com.example.petclinicspringbootapp.customer;

import com.example.petclinicspringbootapp.pet.Pet;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>, CustomerCustomRepo {
    Optional<Customer> findById(ID id);
}
