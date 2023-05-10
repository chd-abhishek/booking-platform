package com.sap.bookingplatform.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address implements Serializable {
    @Id
    @GeneratedValue
    int id;
    String state;
    String city;
    String area;
    int pincode;
    @OneToOne(mappedBy = "address")
    @JsonIgnore
    Users user;
    @OneToOne(mappedBy = "address")
    @JsonIgnore
    Cinema cinema;

}
