package com.example.adventurexpbackend.controller;

import com.example.adventurexpbackend.model.*;
import com.example.adventurexpbackend.repository.ActivityRepo;
import com.example.adventurexpbackend.repository.BookingRepo;
import com.example.adventurexpbackend.repository.EventPackageRepo;
import com.example.adventurexpbackend.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        Customer customer = new Customer();
        customer.setFirstname(bookingRequest.getFirstname());
        customer.setLastname(bookingRequest.getLastname());
        customer.setMail(bookingRequest.getMail());
        customer.setPhoneNumber(bookingRequest.getPhoneNumber());
        customerRepo.save(customer);

        Booking booking = new Booking();
        booking.setCustomer(customer);
        if (bookingRequest.getActivityId() != null) {
            Activity activity = activityRepo.findById(bookingRequest.getActivityId()).orElseThrow(() -> new RuntimeException("activity not found"));
            booking.setActivity(activity);
        }else if(bookingRequest.getPackageId() != null){
            EventPackage eventPackage = eventPackageRepo.findById(bookingRequest.getPackageId()).orElseThrow(() -> new RuntimeException("package not found"));
            booking.setEventPackage(eventPackage);
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

}
