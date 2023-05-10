package com.sap.bookingplatform.controller;

import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.bookingplatform.data.entities.Address;
import com.sap.bookingplatform.data.entities.Cinema;
import com.sap.bookingplatform.data.entities.Hall;
import com.sap.bookingplatform.data.entities.Rating;
import com.sap.bookingplatform.service.TheatreService;

@RestController
@RequestMapping("/platform")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @GetMapping("/cinema")
    public List<Cinema> getAllCinema() {
        return theatreService.getAllCinema();
    }

    @GetMapping("cinema/movie/{name}")
    public Set<Cinema> getCinemaListForMovieAtGivenTime(@PathVariable String name, @RequestParam String dateTime, @RequestParam int pinCode) {
        return theatreService.getCinemaListForMovieAtGivenTime(name, dateTime, pinCode);
    }

    @PostMapping("/cinema")
    public ResponseEntity<Cinema> addCinema(@Valid @RequestBody Cinema cinema) {
        return ResponseEntity.status(HttpStatus.CREATED).body(theatreService.addCinema(cinema));
    }

    @GetMapping("/cinema/{id}/hall")
    public List<Hall> getHalls(@PathVariable int id) {
        return theatreService.getHalls(id);
    }

    @PostMapping("/cinema{id}/hall")
    public ResponseEntity<Cinema> addHall(@PathVariable int id, @RequestBody Hall hall) {
        return ResponseEntity.status(HttpStatus.CREATED).body(theatreService.addHall(id, hall));
    }

    @PostMapping("/cinema/{id}/address")
    public ResponseEntity<Cinema> addAAddress(@RequestBody Address address, @PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(theatreService.addAAddress(address, id));
    }

    @GetMapping("/cinema/{id}/address")
    public Address getAddress(@PathVariable int id) {
        return theatreService.getAddress(id);
    }

    @GetMapping("cinema/{id}/rating")
    public Rating getCinemaRating(@PathVariable int id) {
        return theatreService.getCinemaRating(id);
    }

    @PostMapping("cinema/{id}/rating")
    public ResponseEntity<Cinema> addCinemaRating(@PathVariable int id, @RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(theatreService.addCinemaRating(id, rating));
    }

}
