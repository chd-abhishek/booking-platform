package com.sap.bookingplatform.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

}
