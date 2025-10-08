package com.example.adventurexpbackend.controller;


import com.example.adventurexpbackend.model.Employee;
import com.example.adventurexpbackend.model.LoginRequest;
import com.example.adventurexpbackend.repository.EmployeeRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginController {

    private final EmployeeRepo employeeRepo;

    public LoginController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Employee employee = employeeRepo.findByUsername(loginRequest.getUsername());
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
        if (!employee.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        return ResponseEntity.ok(employee);
    }

}
