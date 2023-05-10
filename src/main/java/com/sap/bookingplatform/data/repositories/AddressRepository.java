package com.sap.bookingplatform.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
