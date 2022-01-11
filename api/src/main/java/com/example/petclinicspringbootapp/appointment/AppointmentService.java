package com.example.petclinicspringbootapp.appointment;

import com.example.petclinicspringbootapp.customer.Customer;
import com.example.petclinicspringbootapp.customer.CustomerRepo;
import com.example.petclinicspringbootapp.employee.Employee;
import com.example.petclinicspringbootapp.employee.EmployeeRepo;
import com.example.petclinicspringbootapp.pet.Pet;
import com.example.petclinicspringbootapp.pet.PetRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final CustomerRepo customerRepo;
    private final PetRepo petRepo;
    private final EmployeeRepo employeeRepo;

    public Appointment saveAppointment(Appointment appointment){
        log.info("Saving new appointment with {} id to the database", appointment.getId());
        return appointmentRepo.save(appointment);
    }

    public List<Appointment> getAppointments(){
        log.info("Fetching all appointments");
        return appointmentRepo.findAll();
    }

    public void addCustomerToAppointment(Long appointmentId, Long customerId){
        Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);
        Optional<Customer> customer = customerRepo.findById(customerId);
        if(appointment.isPresent() && customer.isPresent()){
            appointment.get().setCustomer(customer.get());
        }
    }

    public void addPetToAppointment(Long appointmentId, Long petId){
        Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);
        Optional<Pet> pet = petRepo.findById(petId);
        if(appointment.isPresent() && pet.isPresent()){
            appointment.get().setPet(pet.get());
        }
    }

    public void addEmployeeToAppointment(Long appointmentId, Long employeeId){
        Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);
        Optional<Employee> employee= employeeRepo.findById(employeeId);
        if(appointment.isPresent() && employee.isPresent()){
            appointment.get().setEmployee(employee.get());
        }
    }

    public List<Appointment> getAppointmentsByCustomerEmail(String email){
        return appointmentRepo.findByCustomerEmail(email);
    }

    public List<Appointment> getAppointmentsByEmployeeEmail(String email){
        return appointmentRepo.findByEmployeeEmail(email);
    }
}
