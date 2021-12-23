package com.example.petclinicspringbootapp.employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public Employee saveEmployee(Employee employee){
        log.info("Saving new employee {} to the database", employee.getFirstname());
        return employeeRepo.save(employee);
    }

    public List<Employee> getEmployees(){
        return employeeRepo.findAll();
    }
}
