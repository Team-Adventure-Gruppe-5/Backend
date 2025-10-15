package com.example.adventurexpbackend.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date date;
    private String time;
    private int participents;

    @ManyToOne //owns the relationship
    @JoinColumn(name = "costumer_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    Activity activity;

    @ManyToOne
    @JoinColumn(name = "eventpackage_id", nullable = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    EventPackage eventPackage;


    @ManyToMany
    @JoinTable(
            name = "booking_employee",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees = new ArrayList<>();


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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getParticipents() {
        return participents;
    }

    public void setParticipents(int participents) {
        this.participents = participents;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public EventPackage getEventPackage() {
        return eventPackage;
    }

    public void setEventPackage(EventPackage eventPackage) {
        this.eventPackage = eventPackage;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
