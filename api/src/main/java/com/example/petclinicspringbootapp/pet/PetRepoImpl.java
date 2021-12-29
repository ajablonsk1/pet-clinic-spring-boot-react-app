package com.example.petclinicspringbootapp.pet;

import com.example.petclinicspringbootapp.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PetRepoImpl implements PetCustomRepo {
    private final EntityManager entityManager;

    @Autowired
    public PetRepoImpl(JpaContext jpaContext){
        entityManager = jpaContext.getEntityManagerByManagedType(Pet.class);
    }

    @Override
    public List<Pet> findPetsByUser(AppUser user) {
        List<Pet> pets = entityManager.createQuery("select a from Pet a", Pet.class)
                .getResultList();
        pets = pets.stream()
                .filter(pet -> pet.getOwner().getAppUser().getUsername().equals(user.getUsername()))
                .toList();
        return pets;
    }
}
