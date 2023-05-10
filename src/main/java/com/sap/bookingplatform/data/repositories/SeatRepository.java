package com.sap.bookingplatform.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
	

}
