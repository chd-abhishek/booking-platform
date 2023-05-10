package com.sap.bookingplatform.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Show;

public interface ShowRepository extends JpaRepository<Show, Integer> {

}
