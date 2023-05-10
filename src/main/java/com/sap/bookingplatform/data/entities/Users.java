package com.sap.bookingplatform.data.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.bookingplatform.data.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users implements Serializable {

    @Id
    @GeneratedValue
    int id;
    @NotBlank(message = "Please enter proper user name")
    String name;
    String userId;
    @OneToOne(cascade = CascadeType.ALL)
    Address address;
    @Enumerated(EnumType.STRING)
    UserType type;
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    Preference preference;
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    Account account;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Booking> bookings;

    public List<Booking> addBooking(Booking booking) {
        this.bookings.add(booking);
        return this.bookings;
    }

}
