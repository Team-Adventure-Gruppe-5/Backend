package com.example.adventurexpbackend.controller;


import com.example.adventurexpbackend.model.*;
import com.example.adventurexpbackend.repository.BookingRepo;
import com.example.adventurexpbackend.repository.CustomerRepo;
import com.example.adventurexpbackend.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<?> postLoginCustomer(@RequestBody LoginCustomerRequest loginCustomerRequest) {
        Customer customer = customerRepo.findByMail(loginCustomerRequest.getMail());
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Customer not found");
        }

        boolean bookingExists = customer.getBookings().stream().anyMatch(b -> b.getId() == loginCustomerRequest.getBookingId());

        if (!bookingExists) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Booking ID not found");
        }

        return ResponseEntity.ok(customer);
    }

    @GetMapping("/login-customer/{customerId}/booking/{bookingId}")
    public ResponseEntity<?> getCustomerById(@PathVariable int customerId, @PathVariable int bookingId) {
        Optional<Customer> customer = customerRepo.findById(customerId);

        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Customer customerLoggedIn = customer.get();

        Optional<Booking> booking = customerLoggedIn.getBookings().stream().filter(b -> b.getId() == bookingId).findFirst();

        if (booking.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("customer", customerLoggedIn);
        response.put("booking", booking);

        return ResponseEntity.ok(response);
    }
}
