package com.sap.bookingplatform.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.bookingplatform.data.enums.SeatStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Seat {
    @Id
    @GeneratedValue
    int id;
    @NotNull(message = "Please enter a valid seat num")
    int seatNum;
    @OneToOne
    @JsonIgnore
    Show show;
    @Enumerated(EnumType.STRING)
    SeatStatus seatStatus;

}
