package com.example.adventurexpbackend;

import com.example.adventurexpbackend.model.Activity;
import com.example.adventurexpbackend.model.Employee;
import com.example.adventurexpbackend.model.EventPackage;
import com.example.adventurexpbackend.repository.ActivityRepo;
import com.example.adventurexpbackend.repository.EmployeeRepo;
import com.example.adventurexpbackend.repository.EventPackageRepo;
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
    public CommandLineRunner importData(ActivityRepo activityRepo, EmployeeRepo employeeRepo, EventPackageRepo eventPackageRepo) {
        return (args -> {

            final List<Activity> activities = new ArrayList<>();
            activities.add(new Activity("Go-karting", "Experience the thrill of go-karting at Adventure" +
                    " XP! Race around an exciting track in high-speed karts designed for fun and safety." +
                    " Feel the adrenaline as you take tight corners and compete for the best lap times." +
                    " Go-karting is perfect for both beginners and experienced racers looking for an" +
                    " action-packed activity. Participants must be at least 16 years old. Join us for" +
                    " an unforgettable racing adventure that will get your heart pumping!", 500, 1));
            activities.add(new Activity("Paintball", "Get ready for an action-packed paintball" +
                    " experience at Adventure XP! Dive into thrilling battles in our specially" +
                    " designed arenas, where strategy, speed, and teamwork are key. Perfect for" +
                    " adrenaline seekers, paintball offers excitement for both beginners and" +
                    " experienced players. Participants must be at least 18 years old, but those" +
                    " aged 16 and 17 can join if accompanied by a parent or guardian. Grab your gear" +
                    " and prepare for a colorful, high-energy adventure that will test your skills" +
                    " and reflexes!", 400, 1));
            activities.add(new Activity("Sumo-wrestling", "Step into the ring for a fun and exciting" +
                    " sumo wrestling experience at Adventure XP! Put on a padded sumo suit and" +
                    " challenge your friends in a safe and entertaining match. This activity is" +
                    " perfect for kids and families, combining strength, balance, and laughter." +
                    " Participants must be at least 10 years old. Enjoy a unique and energetic" +
                    " adventure that will leave everyone smiling and wanting more!", 300, 1));
            activities.add(new Activity("Mini golf", "Enjoy a fun and relaxing round of mini golf at" +
                    " Adventure XP! Navigate creative and challenging courses designed for all skill" +
                    " levels, where every hole offers a new twist and turn. Perfect for families," +
                    " friends, and beginners, mini golf is a great way to have fun and test your" +
                    " putting skills. There is no age limit, so everyone can join in on the excitement." +
                    " Experience a playful and engaging activity that guarantees laughter and friendly" +
                    " competition!", 100, 1));
            activityRepo.saveAll(activities);


            //creates a list of employees
            final List<Employee> employees = new ArrayList<>();
            employees.add(new Employee("Lærke", "Lønborg", "lalo", "1234"));
            employees.add(new Employee("Kasper", "Persson", "kape", "1234"));
            employees.add(new Employee("Cami", "Hansen", "caha", "1234"));
            employees.add(new Employee("Brandon", "Walsh", "brwa", "1234"));
            employees.add(new Employee("Andrea", "Zuckerman", "anzu", "1234"));
            employees.add(new Employee("Kelly", "Taylor", "keta", "1234"));
            employees.add(new Employee("Dylan", "McKay", "dymc", "1234"));
            employees.add(new Employee("Steve", "Sanders", "stsa", "1234"));
            employees.add(new Employee("Donna", "Martin", "doma", "1234"));
            employees.add(new Employee("David", "Silver", "dasi", "1234"));
            employeeRepo.saveAll(employees);


            //creates packages
            final EventPackage birthdayAdult = new EventPackage();
            birthdayAdult.setName("Adult Birthday Package");
            birthdayAdult.setDescription("Celebrate your birthday in style with our thrilling Adult Birthday Package! Enjoy an action-packed day featuring Go-Kart racing and Paintball, perfect for adrenaline lovers. This package is designed for guests aged 18 and above, requires a minimum of 10 participants, and we recommend setting aside around 4 hours for the full experience. Make your special day unforgettable with Adventure XP!");
            birthdayAdult.setPrice(800);
            final List<Activity> activitiesAdultBirthday = new ArrayList<>();
            activitiesAdultBirthday.add(activityRepo.findById(1).get());
            activitiesAdultBirthday.add(activityRepo.findById(2).get());
            birthdayAdult.setActivities(activitiesAdultBirthday);

            final EventPackage birthdayChild = new EventPackage();
            birthdayChild.setName("Child Birthday Package");
            birthdayChild.setDescription("Make your child's birthday unforgettable with our fun-packed Child Birthday Package! Enjoy Mini Golf and Sumo Wrestling, guaranteed to keep the kids entertained. This package is designed for children aged 6 and above, requires a minimum of 8 participants, and we recommend setting aside around 5 hours for the full experience. Celebrate with Adventure XP and let the fun begin!");
            birthdayChild.setPrice(300);
            final List<Activity> activitiesChildBirthday = new ArrayList<>();
            activitiesChildBirthday.add(activityRepo.findById(3).get());
            activitiesChildBirthday.add(activityRepo.findById(4).get());
            birthdayChild.setActivities(activitiesChildBirthday);

            final EventPackage familyEvent = new EventPackage();
            familyEvent.setName("Family event");
            familyEvent.setDescription("Bring the whole family together for an unforgettable day with our Family Event Package! Enjoy Go-Kart racing, Sumo Wrestling, and Mini Golf, offering fun for all ages. This package is designed for participants aged 6 and above, requires a minimum of 10 people, and we recommend setting aside around 6 hours for the full experience. Celebrate, play, and create lasting memories with Adventure XP!");
            familyEvent.setPrice(700);
            final List<Activity> activitiesFamilyEvent = new ArrayList<>();
            activitiesFamilyEvent.add(activityRepo.findById(1).get());
            activitiesFamilyEvent.add(activityRepo.findById(3).get());
            activitiesFamilyEvent.add(activityRepo.findById(4).get());
            familyEvent.setActivities(activitiesFamilyEvent);

            final EventPackage companyEvent = new EventPackage();
            companyEvent.setName("Company event");
            companyEvent.setDescription("Host an exciting and memorable day for your team with our Company Event Package! Experience Go-Kart racing, Paintball, Mini Golf, and Sumo Wrestling, offering something for everyone. This package is designed for a minimum of 20 participants, and we recommend setting aside around 6 hours for the full experience. Strengthen team spirit, have fun, and make lasting memories with Adventure XP!");
            companyEvent.setPrice(1100);
            final List<Activity> activitiesCompanyEvent = new ArrayList<>();
            activitiesCompanyEvent.add(activityRepo.findById(1).get());
            activitiesCompanyEvent.add(activityRepo.findById(2).get());
            activitiesCompanyEvent.add(activityRepo.findById(3).get());
            activitiesCompanyEvent.add(activityRepo.findById(4).get());
            companyEvent.setActivities(activitiesCompanyEvent);

            final List<EventPackage> allEventPackages = new ArrayList<>();
            allEventPackages.add(birthdayAdult);
            allEventPackages.add(birthdayChild);
            allEventPackages.add(familyEvent);
            allEventPackages.add(companyEvent);

            eventPackageRepo.saveAll(allEventPackages);

        });
    }
}







