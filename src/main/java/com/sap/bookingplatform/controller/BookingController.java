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

import com.sap.bookingplatform.data.entities.Booking;
import com.sap.bookingplatform.service.BookingService;


@RestController
@RequestMapping("/platform")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/booking")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/booking")
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.addBooking(booking));
    }

    @PostMapping("/booking/user/{userId}/movie/{movieId}/cinema/{cinemaId}/datetime/{dateTime}/payment/{paymentId}/seat/{seatNum}")
    public ResponseEntity<Booking> addBooking(@PathVariable int userId,
                                              @PathVariable int paymentId,
                                              @PathVariable int cinemaId,
                                              @PathVariable int movieId,
                                              @PathVariable String dateTime,
                                              @PathVariable int seatNum) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(bookingService.addBooking(userId, paymentId, cinemaId, movieId, dateTime, seatNum));
    }
}
