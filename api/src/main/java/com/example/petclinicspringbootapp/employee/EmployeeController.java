package com.example.petclinicspringbootapp.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @PostMapping("/employee/save")
    ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok().body(employeeService.saveEmployee(employee));
    }

    @DeleteMapping("/employee")
    ResponseEntity<Integer> saveEmployee(@RequestParam String email){
        return ResponseEntity.ok().body(employeeService.deleteEmployee(email));
    }
}
