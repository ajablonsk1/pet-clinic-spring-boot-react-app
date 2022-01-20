package com.example.petclinicspringbootapp.pet;

import com.example.petclinicspringbootapp.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long>, PetCustomRepo {

    Integer deletePetById(Long id);
}
