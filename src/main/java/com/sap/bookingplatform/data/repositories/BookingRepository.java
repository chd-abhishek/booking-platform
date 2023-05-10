package com.sap.bookingplatform.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
