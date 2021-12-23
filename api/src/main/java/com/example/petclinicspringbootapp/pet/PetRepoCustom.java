package com.example.petclinicspringbootapp.pet;

import com.example.petclinicspringbootapp.user.AppUser;

import java.util.List;

public interface PetRepoCustom {
    List<Pet> findPetsByUser(AppUser user);
}
