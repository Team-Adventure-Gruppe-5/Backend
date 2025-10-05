package com.example.adventurexpbackend.controller;

import com.example.adventurexpbackend.model.Activity;
import com.example.adventurexpbackend.repository.ActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ActivityController {

    @Autowired
    ActivityRepo activityRepo;

    @GetMapping("/index")
    public List<Activity> indexPage() {
        return activityRepo.findAll();
    }

    @GetMapping("/activity/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable int id){
        Optional<Activity> activity = activityRepo.findById(id);

        if(activity.isPresent()){
            return ResponseEntity.ok(activity.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
