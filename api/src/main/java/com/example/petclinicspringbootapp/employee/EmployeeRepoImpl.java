package com.example.petclinicspringbootapp.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class EmployeeRepoImpl implements EmployeeCustomRepo{
}
