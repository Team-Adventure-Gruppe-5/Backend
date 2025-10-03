package com.example.adventurexpbackend;

import com.example.adventurexpbackend.model.Activity;
import com.example.adventurexpbackend.repository.ActivityRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AdventureXpBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdventureXpBackendApplication.class, args);
    }


    @Bean
    public CommandLineRunner importData (ActivityRepo activityRepo){
        return (args ->{

            final List<Activity> activities = new ArrayList<>();
            activities.add(new Activity("Go-karting", 500, 1));
            activities.add(new Activity("Paintball", 400, 1));
            activities.add(new Activity("Sumo-wrestling", 300, 1));
            activities.add(new Activity("Mini golf", 100, 1));
            activityRepo.saveAll(activities);

        } );
    }






}
