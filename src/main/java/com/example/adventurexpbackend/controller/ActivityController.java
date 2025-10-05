package com.example.adventurexpbackend.controller;

import com.example.adventurexpbackend.model.Activity;
import com.example.adventurexpbackend.repository.ActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ActivityController {
    @Autowired
    ActivityRepo activityRepo;

    @GetMapping("/index")
    public List<Activity> indexPage() {
        return activityRepo.findAll();
    }

}
