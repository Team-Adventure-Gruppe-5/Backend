package com.example.adventurexpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EventPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(length = 1000)
    private String description;
    private int price;

    @ManyToMany
    @JoinTable(
            name = "package_activity", //the name of the join table
            joinColumns = @JoinColumn(name = "package_id"), //column pointing at this table
            inverseJoinColumns = @JoinColumn(name = "activity_id") //column pointing at the second table
    )
    @JsonManagedReference
    List<Activity> activities = new ArrayList<>();

    @OneToMany (mappedBy = "eventPackage")
    @JsonManagedReference
    List<Booking> bookings = new ArrayList<>();

    public EventPackage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
