package com.example.petclinicspringbootapp.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
