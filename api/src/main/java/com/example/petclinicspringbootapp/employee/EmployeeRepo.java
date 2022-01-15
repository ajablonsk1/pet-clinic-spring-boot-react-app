package com.example.petclinicspringbootapp.employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long>, EmployeeCustomRepo {

    Employee findEmployeeByEmail(String email);
}
