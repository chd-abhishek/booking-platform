package com.sap.bookingplatform.data.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.bookingplatform.data.enums.SubscriptionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Account implements Serializable {


    @Id
    @GeneratedValue
    int id;
    @OneToOne(mappedBy = "account")
    @JsonIgnore
    Users user;
    @Enumerated(EnumType.STRING)
    SubscriptionType type;

}
