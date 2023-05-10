package com.sap.bookingplatform.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Rating;

public interface RaitingRepository extends JpaRepository<Rating, Integer> {

}
