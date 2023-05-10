package com.sap.bookingplatform.data.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.bookingplatform.data.enums.ScreenSize;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hall implements Serializable {
    @Id
    @GeneratedValue
    int id;
    String hallId;
    @ManyToOne
    @JsonIgnore
    Cinema cinema;
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Show> shows;
    int numOfSeats;
    int numOfRows;
    @Enumerated(EnumType.STRING)
    ScreenSize screenSize;

    public List<Show> addShow(Show show) {
        this.shows.add(show);
        return this.shows;

    }
}
