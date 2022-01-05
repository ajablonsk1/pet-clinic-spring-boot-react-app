package com.example.petclinicspringbootapp.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long>, PetCustomRepo {
}
