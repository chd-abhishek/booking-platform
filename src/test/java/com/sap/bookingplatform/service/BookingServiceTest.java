package com.sap.bookingplatform.service;

import com.sap.bookingplatform.data.entities.*;
import com.sap.bookingplatform.data.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BookingService.class})
@ExtendWith(SpringExtension.class)
class BookingServiceTest {
    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @MockBean
    private CinemaRepository cinemaRepository;

    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private PaymentRepository paymentRepository;

    @MockBean
    private ShowService showService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void before() {
        Users user = new Users();
        user.setBookings(new ArrayList<>());
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Payment payment = new Payment();
        when(paymentRepository.findById(anyInt())).thenReturn(Optional.of(payment));
        LocalDateTime dateTime =LocalDateTime.parse("2020-03-01T13:00:00");
        Cinema cinema = new Cinema();
        cinema.setHalls(new ArrayList<>());
        Hall hall = new Hall();
        hall.setShows(new ArrayList<>());
        Movie movie = new Movie();
        Show show = new Show();
        show.setMovie(movie);
        show.setShowTime(dateTime);
        show.setSeatBooked(10);
        show.setTotalSeats(100);
        hall.addShow(show);
        cinema.addHall(hall);
        Seat selectedSeat = new Seat();
        selectedSeat.setSeatNum(25);
        Booking booking = new Booking();
        booking.setId(1);
        when(bookingRepository.save(any())).thenReturn(booking);
        when(cinemaRepository.findById(any())).thenReturn(Optional.of(cinema));
        when(movieRepository.findById(any())).thenReturn(Optional.of(movie));
        when(showService.bookShowSeats(anyInt(),any())).thenReturn(selectedSeat);
    }

    /**
     * Method under test: {@link BookingService#getAllBookings()}
     */
    @Test
    void testGetAllBookings() {
        assertNotNull(bookingService.getAllBookings());
    }

    /**
     * Method under test: {@link BookingService#addBooking(int, int, int, int, String, int)}
     */
    @Test
    void testAddBooking() {
        assertNotNull(bookingService.addBooking(1, 1, 1, 1, "2020-03-01T13:00:00", 10));
    }

    /**
     * Method under test: {@link BookingService#bookSeatInMovie(int, int, LocalDateTime, int, Booking)}
     */
    @Test
    void testBookSeatInMovie() {
        assertNotNull(bookingService.bookSeatInMovie(1, 1, LocalDateTime.parse("2020-03-01T13:00:00"), 10, new Booking()));
    }
}

