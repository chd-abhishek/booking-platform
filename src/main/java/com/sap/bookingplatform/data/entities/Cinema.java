package com.sap.bookingplatform.data.entities;

import com.sap.bookingplatform.data.enums.ScreenType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cinema implements Serializable {
    @Id
    @GeneratedValue
    int id;
    @NotBlank(message = "Please enter proper cinema name")
    String name;
    @OneToOne(cascade = CascadeType.ALL)
    Address address;
    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
    List<Hall> halls;
    @Enumerated(EnumType.STRING)
    ScreenType type;
    @OneToOne(cascade = CascadeType.ALL)
    Rating rating;

    public List<Hall> addHall(Hall hall) {
        this.halls.add(hall);
        return this.halls;
    }
}
