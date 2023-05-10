package com.sap.bookingplatform.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	
	Optional<Users> findByUserId(String id);

}
