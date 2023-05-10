package com.sap.bookingplatform.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Optional<Movie> findByName(String name);
}
