package com.sap.bookingplatform.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.bookingplatform.data.entities.Booking;
import com.sap.bookingplatform.data.entities.Cinema;
import com.sap.bookingplatform.data.entities.Hall;
import com.sap.bookingplatform.data.entities.Movie;
import com.sap.bookingplatform.data.entities.Payment;
import com.sap.bookingplatform.data.entities.Seat;
import com.sap.bookingplatform.data.entities.Show;
import com.sap.bookingplatform.data.entities.Users;
import com.sap.bookingplatform.data.enums.BookingStatus;
import com.sap.bookingplatform.data.repositories.BookingRepository;
import com.sap.bookingplatform.data.repositories.CinemaRepository;
import com.sap.bookingplatform.data.repositories.MovieRepository;
import com.sap.bookingplatform.data.repositories.PaymentRepository;
import com.sap.bookingplatform.data.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BookingService {

    @Autowired
    BookingRepository bookRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    CinemaRepository cinRepo;
    @Autowired
    MovieRepository movieRepo;
    @Autowired
    PaymentRepository paymentRepo;
    @Autowired
    ShowService showService;

    public List<Booking> getAllBookings() {
        return bookRepo.findAll();
    }

    public Booking addBooking(Booking booking) {
        booking.setStatus(BookingStatus.CONFIRMED);
        Users user = userRepo.findById(booking.getUser().getId()).get();
        Booking bookingCreated = bookRepo.save(booking);
        user.addBooking(bookingCreated);
        userRepo.save(user);
        log.debug("Booking {} for user {} created", booking.getId(), user.getUserId());
        return bookingCreated;
    }

    public Booking addBooking(int userId, int paymentId, int cinemaId, int movieId, String dateTime, int seatNum) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        Users user = userRepo.findById(userId).get();
        Payment payment = paymentRepo.findById(paymentId).get();
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setPayment(payment);
        booking = bookSeatInMovie(cinemaId, movieId, localDateTime, seatNum, booking);
        booking.setStatus(BookingStatus.CONFIRMED);
        Booking bookingCreated = bookRepo.save(booking);
        payment.setBooking(bookingCreated);
        paymentRepo.save(payment);
        user.addBooking(bookingCreated);
        userRepo.save(user);
        log.debug("Booking {} for user {} created", bookingCreated.getId(), user.getUserId());
        return bookingCreated;
    }

    public Booking bookSeatInMovie(int cinemaId, int movieId, LocalDateTime dateTime, int seatNum, Booking booking) {
        Cinema cinema = cinRepo.findById(cinemaId).get();
        Movie movie = movieRepo.findById(movieId).get();
        List<Hall> halls = cinema.getHalls();
        Show selectedShow = halls.stream().
                flatMap(h -> h.getShows().stream()).
                filter(s -> s.getMovie().equals(movie)
                        && s.getShowTime().isEqual(dateTime)
                        && s.getSeatBooked() < s.getTotalSeats()).
                findFirst().get();
        Seat selectedSeat = new Seat();
        selectedSeat.setSeatNum(seatNum);
        selectedSeat = showService.bookShowSeats(selectedShow.getId(), selectedSeat);
        booking.setSeat(selectedSeat);
        booking.setShow(selectedShow);
        log.debug("Seat {} in show {} booked", selectedSeat.getSeatNum(), selectedShow.getId());
        return booking;
    }
}
