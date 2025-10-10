package com.example.adventurexpbackend.model;

public class LoginCustomerRequest {

    private String mail;
    private int bookingId;

    public LoginCustomerRequest(String mail, int bookingId) {
        this.mail = mail;
        this.bookingId = bookingId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}
