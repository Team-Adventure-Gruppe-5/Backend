package com.example.adventurexpbackend.controller;


import com.example.adventurexpbackend.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    EmployeeRepo employeeRepo;
}
