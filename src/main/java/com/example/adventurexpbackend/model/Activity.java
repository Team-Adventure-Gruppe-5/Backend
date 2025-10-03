package com.example.adventurexpbackend.model;

import jakarta.persistence.*;

@Entity
public class Activity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price; //price is in EUR.
    private int duration; //duration is measured in hours, i.e 1 = 1 hour.

    public Activity(String name, int price, int duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public Activity(){}

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
