package com.example.petclinicspringbootapp.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
