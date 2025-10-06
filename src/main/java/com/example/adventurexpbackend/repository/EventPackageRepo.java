package com.example.adventurexpbackend.repository;

import com.example.adventurexpbackend.model.EventPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPackageRepo extends JpaRepository<EventPackage, Integer> {


}
