package com.example.petclinicspringbootapp.customer;

import com.example.petclinicspringbootapp.pet.Pet;
import com.example.petclinicspringbootapp.user.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class CustomerRepoImpl implements CustomerCustomRepo{
    private final EntityManager entityManager;

    @Autowired
    public CustomerRepoImpl(JpaContext jpaContext){
        entityManager = jpaContext.getEntityManagerByManagedType(Customer.class);
    }

    public Customer findCustomerByUser(AppUser appUser){
        return entityManager.createQuery("select a from Customer a where a.appUser = ?1", Customer.class)
                .setParameter(1, appUser)
                .getSingleResult();
    }

    public Customer findCustomerByPet(Pet pet){
        List<Customer> customers = entityManager.createQuery("select a from Customer a", Customer.class)
                .getResultList();
        customers = customers.stream()
                .filter(customer -> customer.getPets().contains(pet)).toList();
        if(customers.size() > 1){
            log.error("Found more than one owner to a pet!");
        }
        return customers.get(0);
    }
}
