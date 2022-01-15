package com.example.petclinicspringbootapp.appointment;

import java.util.List;

public interface AppointmentCustomRepo {
    List<Appointment> findByCustomerEmail(String email);
    List<Appointment> findByEmployeeEmail(String email);
}
