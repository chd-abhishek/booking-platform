package com.sap.bookingplatform.client;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import com.sap.bookingplatform.data.entities.Booking;
import com.sap.bookingplatform.data.entities.Cinema;
import com.sap.bookingplatform.data.entities.Movie;
import com.sap.bookingplatform.data.entities.Payment;
import com.sap.bookingplatform.data.entities.Seat;
import com.sap.bookingplatform.data.entities.Show;
import com.sap.bookingplatform.data.entities.Users;
import com.sap.bookingplatform.data.enums.PaymentMethod;

public class MovieBookingClient {

    public static void main(String[] arg) {
        makeBooking();
    }

    private static void makeBooking() {
        var restTemplate = new RestTemplate();
        String plainCredentials="user:password";
        String base64Credentials = new String(Base64.encode(plainCredentials.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
//        var headers = new HttpHeaders();
//        headers.setBasicAuth("user", "password");
        var request = new HttpEntity(headers);

        var userResponse = restTemplate.exchange(
                "http://localhost:8080/platform/user/Abhi1",
                HttpMethod.GET,
                request,
                Users.class
        );
        System.out.println(userResponse.getBody());

        var movieResponse = restTemplate.exchange(
                "http://localhost:8080/platform/movie/MOVIE1",
                HttpMethod.GET,
                request,
                Movie.class
        );
        System.out.println(movieResponse.getBody());

        var shows = movieResponse.getBody().getShows();
        var selectedShow = shows.get(0);
        System.out.println(selectedShow);

        var selectedSeat = new Seat();
        selectedSeat.setSeatNum(16);
        selectedSeat = restTemplate.postForObject("http://localhost:8080/platform/show/" + selectedShow.getId() + "/seat",
                new HttpEntity<>(selectedSeat, headers),
                Seat.class);
        System.out.println(selectedSeat);

        var payment = new Payment();
        payment.setAmount(500);
        payment.setMethod(PaymentMethod.CREDIT_CARD);

        var paymentDone = restTemplate.postForObject("http://localhost:8080/platform/payment",
                new HttpEntity<>(payment, headers),
                Payment.class);
        System.out.println(paymentDone);

        var booking = new Booking();
        booking.setPayment(paymentDone);
        booking.setSeat(selectedSeat);
        booking.setShow(selectedShow);
        booking.setUser(userResponse.getBody());

        var bookingDone = restTemplate.postForObject("http://localhost:8080/platform/booking" ,
                new HttpEntity<>(booking, headers),
                Booking.class);
        System.out.println(bookingDone);
    }
}
