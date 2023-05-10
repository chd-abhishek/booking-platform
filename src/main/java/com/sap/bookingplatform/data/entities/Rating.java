package com.sap.bookingplatform.data.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.bookingplatform.data.enums.RaitingEntityType;
import com.sap.bookingplatform.data.enums.RatingEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Rating implements Serializable {
    @Id
    @GeneratedValue
    int id;
    @Enumerated(EnumType.STRING)
    RaitingEntityType entity;
    @Enumerated(EnumType.STRING)
    RatingEnum ratings;
    @JsonIgnore
    @OneToOne(mappedBy = "rating")
    Movie movie;
    @JsonIgnore
    @OneToOne(mappedBy = "rating")
    Cinema cinema;

}
