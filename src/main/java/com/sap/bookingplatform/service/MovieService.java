package com.sap.bookingplatform.service;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.sap.bookingplatform.data.entities.Movie;
import com.sap.bookingplatform.data.entities.Rating;
import com.sap.bookingplatform.data.repositories.MovieRepository;
import com.sap.bookingplatform.data.repositories.RaitingRepository;
import com.sap.bookingplatform.data.repositories.ShowRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@Slf4j
public class MovieService {

    @Autowired
    MovieRepository movieRepo;
    @Autowired
    ShowRepository showRepo;
    @Autowired
    RaitingRepository raitingRepo;
    @Value("${bookingPlatform.movieReviewMicroservice.url}")
    private String movieReviewMicroserviceUrl;

    @Cacheable(cacheNames = "movies", key = "#root.methodName")
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @Cacheable(cacheNames = "movie", key = "#name")
    public Movie getMovieByName(String name) {
        return movieRepo.findByName(name).get();
    }

    public Movie addMovie(Movie movie) {
        log.debug("Movie {} added", movie.getName());
        return movieRepo.save(movie);
    }

    public Rating getMovieRating(int id) {
        Movie movie = movieRepo.findById(id).get();
        return movie.getRating();
    }

    public Movie addMovieRating(int id, Rating rating) {
        Movie movie = movieRepo.findById(id).get();
        rating.setMovie(movie);
        raitingRepo.save(rating);
        movie.setRating(rating);
        log.debug("Raiting for movie {} is aded", movie.getName());
        return movieRepo.save(movie);
    }

    @CircuitBreaker(name = "movieService", fallbackMethod = "getMovieReviewFallbackMethod")
    public String getMovieReview(String name) {
        log.info("Calling Movie Review microservice");
        return new RestTemplate().getForObject(movieReviewMicroserviceUrl, String.class);
    }

    public String getMovieReviewFallbackMethod(Exception ex) {
        log.info("Calling Movie Review fallback method");
        return "Not what you are looking for but movie is still good";
    }
}
