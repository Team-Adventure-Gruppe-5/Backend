package com.example.adventurexpbackend.controller;

import com.example.adventurexpbackend.model.Activity;
import com.example.adventurexpbackend.model.Booking;
import com.example.adventurexpbackend.model.BookingRequest;
import com.example.adventurexpbackend.model.User;
import com.example.adventurexpbackend.repository.ActivityRepo;
import com.example.adventurexpbackend.repository.BookingRepo;
import com.example.adventurexpbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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

    @GetMapping("/booking-confirmation/{id}")
    public ResponseEntity<Booking> getBookingConfirmation(@PathVariable int id){
        Optional<Booking> booking = bookingRepo.findById(id);

        if(booking.isPresent()){
            return ResponseEntity.ok(booking.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/booking")
    public ResponseEntity<Booking> postBooking(@RequestBody BookingRequest bookingRequest){

        User user = new User();
        user.setFirstname(bookingRequest.getFirstname());
        user.setLastname(bookingRequest.getLastname());
        user.setMail(bookingRequest.getMail());
        user.setPhoneNumber(bookingRequest.getPhoneNumber());
        userRepo.save(user);

        Activity activity = activityRepo.findById(bookingRequest.getActivityId()).orElseThrow(() -> new RuntimeException("activity not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setActivity(activity);
        booking.setParticipents(bookingRequest.getParticipents());
        booking.setDate(bookingRequest.getDate());
        booking.setTime(bookingRequest.getTime());

        bookingRepo.save(booking);
        return ResponseEntity.ok(booking);
    }

}
