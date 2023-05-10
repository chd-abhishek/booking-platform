package com.sap.bookingplatform.service;

import com.sap.bookingplatform.data.entities.Movie;
import com.sap.bookingplatform.data.entities.Rating;
import com.sap.bookingplatform.data.repositories.MovieRepository;
import com.sap.bookingplatform.data.repositories.RaitingRepository;
import com.sap.bookingplatform.data.repositories.ShowRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {MovieService.class})
@ExtendWith(SpringExtension.class)
class MovieServiceTest {
    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @MockBean
    private RaitingRepository raitingRepository;

    @MockBean
    private ShowRepository showRepository;

    /**
     * Method under test: {@link MovieService#getAllMovies()}
     */
    @Test
    void testGetAllMovies() {
          assertNotNull(movieService.getAllMovies());
    }

    /**
     * Method under test: {@link MovieService#getMovieByName(String)}
     */
    @Test
    void testGetMovieByName() {
        Movie movie = new Movie();
        when(movieRepository.findByName(anyString())).thenReturn(Optional.of(movie));
        assertNotNull(movieService.getMovieByName("Name"));
    }

    /**
     * Method under test: {@link MovieService#addMovie(Movie)}
     */
    @Test
    void testAddMovie() {
        Movie movie = new Movie();
        movie.setName("name");
        when(movieRepository.save(movie)).thenReturn(movie);
        assertNotNull(movieService.addMovie(movie));
    }

    /**
     * Method under test: {@link MovieService#getMovieRating(int)}
     */
    @Test
    void testGetMovieRating() {
        Movie movie = new Movie();
        movie.setRating(new Rating());
        when(movieRepository.findById(anyInt())).thenReturn(Optional.of(movie));
        movieService.getMovieRating(1);
    }

    /**
     * Method under test: {@link MovieService#addMovieRating(int, Rating)}
     */
    @Test
    void testAddMovieRating() {
        Movie movie = new Movie();
        movie.setName("name");
        when(movieRepository.findById(anyInt())).thenReturn(Optional.of(movie));
        Rating rating = new Rating();
        when(raitingRepository.save(any())).thenReturn(rating);
        movieService.addMovieRating(1, rating);
    }
}

