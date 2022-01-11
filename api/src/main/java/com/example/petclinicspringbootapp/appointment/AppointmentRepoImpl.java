package com.example.petclinicspringbootapp.appointment;

import com.example.petclinicspringbootapp.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AppointmentRepoImpl implements AppointmentCustomRepo{
    private final EntityManager entityManager;

    @Autowired
    public AppointmentRepoImpl(JpaContext jpaContext){
        entityManager = jpaContext.getEntityManagerByManagedType(Appointment.class);
    }

    @Override
    public List<Appointment> findByCustomerEmail(String email) {
        List<Appointment> appointments = entityManager.createQuery("select a from Appointment a", Appointment.class)
                .getResultList();
        return appointments.stream()
                .filter(appointment -> appointment.getCustomer().getEmail().equals(email))
                .toList();
    }

    @Override
    public List<Appointment> findByEmployeeEmail(String email) {
        List<Appointment> appointments = entityManager.createQuery("select a from Appointment a", Appointment.class)
                .getResultList();
        return appointments.stream()
                .filter(appointment -> appointment.getEmployee().getEmail().equals(email))
                .toList();
    }
}
