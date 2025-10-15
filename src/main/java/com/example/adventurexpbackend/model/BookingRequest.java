package com.example.adventurexpbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BookingRequest {

    private String firstname;
    private String lastname;
    private String mail;
    private int phoneNumber;
    private int participents;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String time;
    private Integer activityId; //nullable
    private Integer packageId; //nullable


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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
}
