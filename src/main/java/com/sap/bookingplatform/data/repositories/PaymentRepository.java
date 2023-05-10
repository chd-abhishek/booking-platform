package com.sap.bookingplatform.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
