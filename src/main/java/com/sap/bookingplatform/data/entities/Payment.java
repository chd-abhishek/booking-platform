package com.sap.bookingplatform.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.bookingplatform.data.enums.PaymentMethod;
import com.sap.bookingplatform.data.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue
    int id;
    @OneToOne(mappedBy = "payment")
    @JsonIgnore
    Booking booking;
    @Enumerated(EnumType.STRING)
    PaymentStatus status;
    @Enumerated(EnumType.STRING)
    PaymentMethod method;
    int amount;
    @ManyToOne
    Coupon coupon;

}
