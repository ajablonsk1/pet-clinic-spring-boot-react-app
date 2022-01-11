package com.example.petclinicspringbootapp.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(){
        return ResponseEntity.ok().body(appointmentService.getAppointments());
    }

    @PostMapping("/appointment/save")
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment){
        return ResponseEntity.ok().body(appointmentService.saveAppointment(appointment));
    }

    @GetMapping("/appointments/customer")
    public ResponseEntity<List<Appointment>> getAppointmentsByCustomerEmail(@RequestParam String email){
        return ResponseEntity.ok().body(appointmentService.getAppointmentsByCustomerEmail(email));
    }

    @GetMapping("/appointments/employee")
    public ResponseEntity<List<Appointment>> getAppointmentsByEmployeeEmail(@RequestParam String email){
        return ResponseEntity.ok().body(appointmentService.getAppointmentsByEmployeeEmail(email));
    }
}
