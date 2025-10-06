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
            activities.add(new Activity("Go-karting","Experience the thrill of go-karting at Adventure" +
                    " XP! Race around an exciting track in high-speed karts designed for fun and safety." +
                    " Feel the adrenaline as you take tight corners and compete for the best lap times." +
                    " Go-karting is perfect for both beginners and experienced racers looking for an" +
                    " action-packed activity. Participants must be at least 16 years old. Join us for" +
                    " an unforgettable racing adventure that will get your heart pumping!", 500, 1));
            activities.add(new Activity("Paintball","Get ready for an action-packed paintball" +
                    " experience at Adventure XP! Dive into thrilling battles in our specially" +
                    " designed arenas, where strategy, speed, and teamwork are key. Perfect for" +
                    " adrenaline seekers, paintball offers excitement for both beginners and" +
                    " experienced players. Participants must be at least 18 years old, but those" +
                    " aged 16 and 17 can join if accompanied by a parent or guardian. Grab your gear" +
                    " and prepare for a colorful, high-energy adventure that will test your skills" +
                    " and reflexes!", 400, 1));
            activities.add(new Activity("Sumo-wrestling","Step into the ring for a fun and exciting" +
                    " sumo wrestling experience at Adventure XP! Put on a padded sumo suit and" +
                    " challenge your friends in a safe and entertaining match. This activity is" +
                    " perfect for kids and families, combining strength, balance, and laughter." +
                    " Participants must be at least 10 years old. Enjoy a unique and energetic" +
                    " adventure that will leave everyone smiling and wanting more!", 300, 1));
            activities.add(new Activity("Mini golf","Enjoy a fun and relaxing round of mini golf at" +
                    " Adventure XP! Navigate creative and challenging courses designed for all skill" +
                    " levels, where every hole offers a new twist and turn. Perfect for families," +
                    " friends, and beginners, mini golf is a great way to have fun and test your" +
                    " putting skills. There is no age limit, so everyone can join in on the excitement." +
                    " Experience a playful and engaging activity that guarantees laughter and friendly" +
                    " competition!", 100, 1));
            activityRepo.saveAll(activities);

        } );
    }






}
