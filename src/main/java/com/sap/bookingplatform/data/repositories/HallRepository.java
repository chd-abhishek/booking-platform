package com.sap.bookingplatform.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Hall;

public interface HallRepository extends JpaRepository<Hall, Integer> {

}
