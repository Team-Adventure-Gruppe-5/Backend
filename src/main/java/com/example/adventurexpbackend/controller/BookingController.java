package com.example.adventurexpbackend.controller;

import com.example.adventurexpbackend.model.*;
import com.example.adventurexpbackend.repository.ActivityRepo;
import com.example.adventurexpbackend.repository.BookingRepo;
import com.example.adventurexpbackend.repository.EventPackageRepo;
import com.example.adventurexpbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
