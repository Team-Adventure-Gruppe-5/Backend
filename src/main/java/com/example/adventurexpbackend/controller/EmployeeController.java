package com.example.adventurexpbackend.controller;


import com.example.adventurexpbackend.model.Employee;
import com.example.adventurexpbackend.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepo.save(employee);
    }
}
