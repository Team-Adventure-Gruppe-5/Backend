package com.example.adventurexpbackend.repository;

import com.example.adventurexpbackend.model.Booking;
import com.example.adventurexpbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Customer findByMail(String mail);

}
