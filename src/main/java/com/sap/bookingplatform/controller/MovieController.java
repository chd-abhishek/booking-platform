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

import com.sap.bookingplatform.data.entities.Movie;
import com.sap.bookingplatform.data.entities.Rating;
import com.sap.bookingplatform.service.MovieService;

@RestController
@RequestMapping("/platform")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping("/movie")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/{name}")
    public Movie getMovieByName(@PathVariable String name) {
        return movieService.getMovieByName(name);
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.addMovie(movie));
    }

    @GetMapping("movie/{id}/rating")
    public Rating getMovieRating(@PathVariable int id) {
        return movieService.getMovieRating(id);
    }

    @PostMapping("movie/{id}/rating")
    public ResponseEntity<Movie> addMovieRating(@PathVariable int id, @RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.addMovieRating(id, rating));
    }

    @GetMapping("movie/{name}/review")
    public String getMovieReview(@PathVariable String name) {
        return movieService.getMovieReview(name);
    }

}
