package com.sap.bookingplatform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.sap.bookingplatform.data.entities.Address;
import com.sap.bookingplatform.data.entities.Cinema;
import com.sap.bookingplatform.data.entities.Hall;
import com.sap.bookingplatform.data.entities.Movie;
import com.sap.bookingplatform.data.entities.Rating;
import com.sap.bookingplatform.data.entities.Show;
import com.sap.bookingplatform.data.repositories.AddressRepository;
import com.sap.bookingplatform.data.repositories.CinemaRepository;
import com.sap.bookingplatform.data.repositories.HallRepository;
import com.sap.bookingplatform.data.repositories.MovieRepository;
import com.sap.bookingplatform.data.repositories.RaitingRepository;
import com.sap.bookingplatform.data.repositories.ShowRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TheatreService {

    @Autowired
    CinemaRepository cinRepo;
    @Autowired
    HallRepository hallRepo;
    @Autowired
    ShowRepository showRepo;
    @Autowired
    AddressRepository addRepo;
    @Autowired
    MovieRepository movieRepo;
    @Autowired
    RaitingRepository raitingRepo;

    @Cacheable(cacheNames = "cinema")
    public List<Cinema> getAllCinema() {
        return cinRepo.findAll();
    }

    public Cinema addCinema(Cinema cinema) {
        Cinema cinCreated = cinRepo.save(cinema);
        List<Hall> halls = cinema.getHalls();

        halls.forEach(h -> {
            h.setCinema(cinCreated);
            hallRepo.save(h);
        });
        log.debug("Cinema {} added", cinema.getName());
        return cinCreated;
    }

    public List<Hall> getHalls(int id) {
        Cinema cinema = cinRepo.findById(id).get();
        return cinema.getHalls();
    }

    public Cinema addHall(int id, Hall hall) {
        Cinema cinema = cinRepo.findById(id).get();
        hall.setCinema(cinema);
        Hall hallCreated = hallRepo.save(hall);
        cinema.addHall(hallCreated);
        log.debug("Hall {} in Cinema {} added", hallCreated.getHallId(), cinema.getName());
        return cinRepo.save(cinema);
    }

    public Cinema addAAddress(Address address, int id) {

        Cinema cinema = cinRepo.findById(id).get();
        address.setCinema(cinema);
        Address acc = addRepo.save(address);
        cinema.setAddress(acc);
        return cinRepo.save(cinema);
    }

    public Address getAddress(int id) {
        Cinema cinema = cinRepo.findById(id).get();
        return cinema.getAddress();
    }

    public Rating getCinemaRating(int id) {
        Cinema cinema = cinRepo.findById(id).get();
        return cinema.getRating();
    }

    public Cinema addCinemaRating(int id, Rating rating) {
        Cinema cinema = cinRepo.findById(id).get();
        rating.setCinema(cinema);
        raitingRepo.save(rating);
        cinema.setRating(rating);
        log.debug("Raiting for cinema {} is added", cinema.getName());
        return cinRepo.save(cinema);
    }

    public Set<Cinema> getCinemaListForMovieAtGivenTime(String name, String dateTime, int pinCode) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        Movie movie = movieRepo.findByName(name).get();
        List<Show> shows = movie.getShows();

        return shows.stream().filter(s ->
                        s.getShowTime().toLocalDate().isEqual(localDateTime.toLocalDate())
                                && s.getShowTime().toLocalTime().compareTo(localDateTime.toLocalTime()) == 0)
                .map(s -> s.getHall().getCinema()).distinct().filter(c -> c.getAddress().getPincode() == pinCode).collect(Collectors.toSet());
    }
}
