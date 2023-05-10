package com.sap.bookingplatform.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

}
