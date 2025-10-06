package com.example.adventurexpbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BookingRequest {

    private String firstname;
    private String lastname;
    private String mail;
    private int phoneNumber;
    private int participents;
    private Date date;
    private int time;
    private int activityId;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getParticipents() {
        return participents;
    }

    public void setParticipents(int participents) {
        this.participents = participents;
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

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
