package com.example.petclinicspringbootapp.employee;

import com.example.petclinicspringbootapp.appointment.AppointmentRepo;
import com.example.petclinicspringbootapp.customer.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final AppointmentRepo appointmentRepo;

    public Employee saveEmployee(Employee employee){
        log.info("Saving new employee {} to the database", employee.getFirstname());
        return employeeRepo.save(employee);
    }

    public List<Employee> getEmployees(){
        log.info("Fetching all employees");
        return employeeRepo.findAll();
    }

    public Integer deleteEmployee(String email) {
        Employee employee = employeeRepo.findEmployeeByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("Customer with given email " + email + " was not found");
        }
        appointmentRepo.findAll()
                .stream()
                .filter(appointment -> {
                    if (appointment.getEmployee() != null) {
                        return appointment.getEmployee().getEmail().equals(email);
                    } else {
                        return false;
                    }
                })
                .forEach(appointment -> appointment.setEmployee(null));
        return employeeRepo.deleteEmployeeByEmail(email);
    }
}
