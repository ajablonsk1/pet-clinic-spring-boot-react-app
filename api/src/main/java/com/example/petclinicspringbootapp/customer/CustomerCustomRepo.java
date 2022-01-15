package com.example.petclinicspringbootapp.customer;

import com.example.petclinicspringbootapp.pet.Pet;
import com.example.petclinicspringbootapp.user.AppUser;

public interface CustomerCustomRepo {

    Customer findCustomerByUser(AppUser appUser);

    Customer findCustomerByPet(Pet pet);
}
