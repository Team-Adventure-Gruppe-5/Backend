package com.example.adventurexpbackend.controller;

import com.example.adventurexpbackend.model.*;
import com.example.adventurexpbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class BookingController {

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    UserRepo userRepo;

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

        User user = new User();
        user.setFirstname(bookingRequest.getFirstname());
        user.setLastname(bookingRequest.getLastname());
        user.setMail(bookingRequest.getMail());
        user.setPhoneNumber(bookingRequest.getPhoneNumber());
        userRepo.save(user);

        Booking booking = new Booking();
        booking.setUser(user);
        if (bookingRequest.getActivityId() != null) {
            Activity activity = activityRepo.findById(bookingRequest.getActivityId()).orElseThrow(() -> new RuntimeException("activity not found"));
            booking.setActivity(activity);
        }else if(bookingRequest.getPackageId() != null){
            EventPackage eventPackage = eventPackageRepo.findById(bookingRequest.getPackageId()).orElseThrow(() -> new RuntimeException("package not found"));
            booking.setaPackage(eventPackage);
        } else{
            throw new RuntimeException("No activityId or packageId is found");
        }

        booking.setParticipents(bookingRequest.getParticipents());
        booking.setDate(bookingRequest.getDate());
        booking.setTime(bookingRequest.getTime());

        bookingRepo.save(booking);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/bookings") //change later for "/employee/{employeeid}/bookings" when employee and booking have a relation
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
