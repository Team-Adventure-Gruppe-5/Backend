package com.example.adventurexpbackend.controller;

import com.example.adventurexpbackend.model.*;
import com.example.adventurexpbackend.repository.ActivityRepo;
import com.example.adventurexpbackend.repository.BookingRepo;
import com.example.adventurexpbackend.repository.EventPackageRepo;
import com.example.adventurexpbackend.repository.CustomerRepo;
import com.example.adventurexpbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class BookingController {

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ActivityRepo activityRepo;

    @Autowired
    EventPackageRepo eventPackageRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/booking-confirmation/{id}")
    public ResponseEntity<Booking> getBookingConfirmation(@PathVariable int id) {
        Optional<Booking> booking = bookingRepo.findById(id);

        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/booking")
    public ResponseEntity<Booking> postBooking(@RequestBody BookingRequest bookingRequest) {

        Customer customer = customerRepo.findByMail(bookingRequest.getMail());

        if(customer == null){
            customer = new Customer();
            customer.setFirstname(bookingRequest.getFirstname());
            customer.setLastname(bookingRequest.getLastname());
            customer.setMail(bookingRequest.getMail());
            customer.setPhoneNumber(bookingRequest.getPhoneNumber());
            customerRepo.save(customer);

        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        if (bookingRequest.getActivityId() != null) {
            Activity activity = activityRepo.findById(bookingRequest.getActivityId()).orElseThrow(() -> new RuntimeException("activity not found"));
            booking.setActivity(activity);
            booking.setName(activity.getName());
        }else if(bookingRequest.getPackageId() != null){
            EventPackage eventPackage = eventPackageRepo.findById(bookingRequest.getPackageId()).orElseThrow(() -> new RuntimeException("package not found"));
            booking.setEventPackage(eventPackage);
            booking.setName(eventPackage.getName());
        } else{
            throw new RuntimeException("No activityId or packageId is found");
        }

        booking.setParticipents(bookingRequest.getParticipents());
        booking.setDate(bookingRequest.getDate());
        booking.setTime(bookingRequest.getTime());

        bookingRepo.save(booking);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepo.findAll();
        return ResponseEntity.ok(bookings);
    }


    @PutMapping("/booking/{bookingId}/employees/{employeeId}")
    public ResponseEntity<?> assignEmployeeToBooking(@PathVariable int bookingId, @PathVariable int employeeId) {
        Optional<Booking> optionalBooking = bookingRepo.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Booking not found"));
        }

        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Employee not found"));
        }

        Booking booking = optionalBooking.get();
        Employee employee = optionalEmployee.get();

        booking.getEmployees().add(employee);

        bookingRepo.save(booking);

        return ResponseEntity.ok(Map.of("message", "Employee assigned successfully!"));
    }





}
