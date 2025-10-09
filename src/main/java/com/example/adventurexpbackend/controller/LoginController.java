package com.example.adventurexpbackend.controller;


import com.example.adventurexpbackend.model.Customer;
import com.example.adventurexpbackend.model.Employee;
import com.example.adventurexpbackend.model.LoginCustomerRequest;
import com.example.adventurexpbackend.model.LoginRequest;
import com.example.adventurexpbackend.repository.CustomerRepo;
import com.example.adventurexpbackend.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class LoginController {
    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    CustomerRepo customerRepo;


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

    @PostMapping("/login-customer")
    public ResponseEntity<?> postLoginCustomer (@RequestBody LoginCustomerRequest loginCustomerRequest){
        Customer customer = customerRepo.findByMail(loginCustomerRequest.getMail());
        if(customer == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Customer not found");
        }

        boolean bookingExists = customer.getBookings().stream().anyMatch(b -> b.getId() == loginCustomerRequest.getBookingId());

        if(!bookingExists){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Booking ID not found");
        }

        return ResponseEntity.ok(customer);
    }



}
