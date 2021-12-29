package com.example.petclinicspringbootapp.appointment;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepo extends JpaRepository<Appointment, Long>, AppointmentCustomRepo {
    Optional<Appointment> findById(ID id);
}
