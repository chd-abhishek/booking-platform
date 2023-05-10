package com.sap.bookingplatform.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
