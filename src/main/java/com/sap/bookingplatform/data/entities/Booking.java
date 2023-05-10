package com.sap.bookingplatform.data.entities;

import com.sap.bookingplatform.data.enums.BookingStatus;

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
public class Booking {
    @Id
    @GeneratedValue
    int id;
    @ManyToOne
    Users user;
    @OneToOne
    Show show;
    @OneToOne
    Payment payment;
    @Enumerated(EnumType.STRING)
    BookingStatus status;
    @OneToOne
    Seat seat;

}
