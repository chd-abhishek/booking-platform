package com.sap.bookingplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.bookingplatform.data.entities.Seat;
import com.sap.bookingplatform.data.entities.Show;
import com.sap.bookingplatform.service.ShowService;

@RestController
@RequestMapping("/platform")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("movie/{movieId}/hall/{hallId}/show")
    public ResponseEntity<Show> addMovieShow(@PathVariable int movieId, @PathVariable int hallId, @RequestBody Show show) {
        return ResponseEntity.status(HttpStatus.CREATED).body(showService.addMovieShow(movieId, hallId, show));
    }

    @GetMapping("/show")
    public List<Show> getShows() {
        return showService.getShows();
    }

    @GetMapping("movie/{id}/show")
    public List<Show> getMovieShows(@PathVariable int id) {
        return showService.getMovieShows(id);
    }

    @GetMapping("show/{id}/seat")
    public List<Seat> getShowSeats(@PathVariable int id) {
        return showService.getShowSeats(id);
    }

    @PostMapping("show/{id}/seat")
    public ResponseEntity<Seat> bookShowSeats(@PathVariable int id, @RequestBody Seat seat) {
        return ResponseEntity.status(HttpStatus.CREATED).body(showService.bookShowSeats(id, seat));
    }

}
