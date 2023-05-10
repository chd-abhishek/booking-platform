package com.sap.bookingplatform.data.entities;

import java.io.Serializable;
import java.util.List;

import com.sap.bookingplatform.data.enums.LanguageEnum;
import com.sap.bookingplatform.data.enums.MovieType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Movie implements Serializable {
    @Id
    @GeneratedValue
    int id;
    String name;
    @Enumerated(EnumType.STRING)
    MovieType type;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Show> shows;
    @Enumerated(EnumType.STRING)
    LanguageEnum language;
    @OneToOne(cascade = CascadeType.ALL)
    Rating rating;

    public List<Show> addShow(Show show) {
        this.shows.add(show);
        return this.shows;
    }

}
