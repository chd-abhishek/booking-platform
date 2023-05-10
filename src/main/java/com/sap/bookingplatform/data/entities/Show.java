package com.sap.bookingplatform.data.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Show implements Serializable {
    @Id
    @GeneratedValue
    int id;
    @ManyToOne
    @JsonIgnore
    Movie movie;
    @OneToOne
    @JsonIgnore
    Hall hall;
    LocalDateTime showTime;
    int totalSeats;
    int seatBooked;
    @OneToMany(mappedBy = "show")
    @JsonIgnore
    List<Seat> seats;
    int duration;

    public List<Seat> bookSeat(Seat seat) {
        this.seats.add(seat);
        return this.seats;
    }

}
