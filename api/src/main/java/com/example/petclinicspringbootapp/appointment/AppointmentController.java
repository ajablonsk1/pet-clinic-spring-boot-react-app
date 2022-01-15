package com.example.petclinicspringbootapp.appointment;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @PostMapping("/appointments/add")
    public ResponseEntity<Appointment> getAppointmentsByEmployeeEmail(@RequestBody AppointmentForm appointmentForm){
        return ResponseEntity.ok().body(appointmentService.saveAppointment(appointmentForm));
    }

    @GetMapping("/appointments/dates")
    public ResponseEntity<List<Date>> getDates(@RequestParam String currDate) throws ParseException {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        Date date = new SimpleDateFormat(pattern).parse(currDate);
        return ResponseEntity.ok().body(appointmentService.getDates(date));
    }
}

@Data
class AppointmentForm{
    private String customerEmail;
    private String employeeEmail;
    private String petName;
    private Appointment appointment;
}
