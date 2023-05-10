package com.sap.bookingplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.sap.bookingplatform.data.entities.Hall;
import com.sap.bookingplatform.data.entities.Movie;
import com.sap.bookingplatform.data.entities.Seat;
import com.sap.bookingplatform.data.entities.Show;
import com.sap.bookingplatform.data.enums.SeatStatus;
import com.sap.bookingplatform.data.repositories.HallRepository;
import com.sap.bookingplatform.data.repositories.MovieRepository;
import com.sap.bookingplatform.data.repositories.SeatRepository;
import com.sap.bookingplatform.data.repositories.ShowRepository;
import com.sap.bookingplatform.exception.SeatAlreadyBookedException;
import com.sap.bookingplatform.exception.ShowAlreadyBookedException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ShowService {

    @Autowired
    HallRepository hallRepo;
    @Autowired
    ShowRepository showRepo;
    @Autowired
    MovieRepository movieRepo;
    @Autowired
    SeatRepository seatRepo;

    public Show addMovieShow(int movieId, int hallId, Show show) {
        Movie movie = movieRepo.findById(movieId).get();
        Hall hall = hallRepo.findById(hallId).get();
        show.setMovie(movie);
        show.setHall(hall);
        show.setTotalSeats(hall.getNumOfSeats());
        show.setSeatBooked(0);
        Show showCreated = showRepo.save(show);
        movie.addShow(show);
        hall.addShow(show);
        movieRepo.save(movie);
        hallRepo.save(hall);
        log.debug("Show for movie {} in hall {} is added", movie.getName(), hall.getHallId());
        return showCreated;
    }

    public List<Show> getShows() {
        return showRepo.findAll();
    }

    public List<Show> getMovieShows(int id) {
        return movieRepo.findById(id).get().getShows();
    }

    public List<Seat> getShowSeats(int id) {
        return showRepo.findById(id).get().getSeats();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public synchronized Seat bookShowSeats(int id, Seat seat) {
        System.out.println("--->>>>> bookShowSeats " + id + " " + seat.getSeatNum());
        Show show = showRepo.findById(id).get();
        validateSeatBooking(seat, show);
        seat.setSeatStatus(SeatStatus.BOOKED);
        seat.setShow(show);
        Seat seatBooked = seatRepo.save(seat);
        show.bookSeat(seatBooked);
        show.setSeatBooked(show.getSeatBooked() + 1);
        showRepo.save(show);
        log.debug("Seat {] in show {} is booked", seat.getSeatNum(), show.getId());
        return seatBooked;
    }

    private void validateSeatBooking(Seat seat, Show show) {
        if (show.getTotalSeats() == show.getSeatBooked()) {
            throw new ShowAlreadyBookedException("Show is full");
        }
        List<Seat> seatList = show.getSeats();
        boolean isSeatBooked = seatList.stream().filter(s -> s.getSeatNum() == seat.getSeatNum() && s.getSeatStatus() == SeatStatus.BOOKED).findFirst().isPresent();
        if (isSeatBooked) {
            throw new SeatAlreadyBookedException("Seat Already Booked");
        }
    }

}
