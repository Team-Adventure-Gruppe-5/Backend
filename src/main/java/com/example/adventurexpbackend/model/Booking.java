package com.example.adventurexpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date date;
    private int time;
    private int participents;

    @ManyToOne //owns the relationship
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    User user;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = true)
    @JsonBackReference
    Activity activity;

    @ManyToOne
    @JoinColumn(name = "eventpackage_id", nullable = true)
    @JsonBackReference
    EventPackage eventPackage;

    public Booking() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getParticipents() {
        return participents;
    }

    public void setParticipents(int participents) {
        this.participents = participents;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public EventPackage getaPackage() {
        return eventPackage;
    }

    public void setaPackage(EventPackage aEventPackage) {
        this.eventPackage = aEventPackage;
    }
}
