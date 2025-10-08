package com.example.adventurexpbackend.controller;

import com.example.adventurexpbackend.model.EventPackage;
import com.example.adventurexpbackend.repository.EventPackageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class EventPackageController {

    @Autowired
    EventPackageRepo eventPackageRepo;

    @GetMapping("/packages")
    public List<EventPackage> getPackages(){
        return eventPackageRepo.findAll();
    }

    @GetMapping("/packages/{id}")
    public ResponseEntity<EventPackage> getPackageById(@PathVariable int id){
        Optional<EventPackage> eventPackage = eventPackageRepo.findById(id);

        if(eventPackage.isPresent()){
            return ResponseEntity.ok(eventPackage.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
