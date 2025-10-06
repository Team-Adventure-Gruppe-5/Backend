package com.example.adventurexpbackend.controller;


import com.example.adventurexpbackend.model.Employee;
import com.example.adventurexpbackend.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    EmployeeRepo employeeRepo;

    @GetMapping("/employees")
    public List<Employee> employeepage(){
        return employeeRepo.findAll();
    }
}
