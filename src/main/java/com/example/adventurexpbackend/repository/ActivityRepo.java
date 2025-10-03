package com.example.adventurexpbackend.repository;

import com.example.adventurexpbackend.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Integer> {



}
