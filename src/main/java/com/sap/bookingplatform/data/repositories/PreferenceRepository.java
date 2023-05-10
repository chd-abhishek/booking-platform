package com.sap.bookingplatform.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.bookingplatform.data.entities.Preference;

public interface PreferenceRepository extends JpaRepository<Preference, Integer> {

}
