package com.bookMyShow.service;

import com.bookMyShow.model.Booking;
import com.bookMyShow.model.Seat;
import com.bookMyShow.model.Show;
import com.bookMyShow.model.User;

import java.util.*;

public class BookingService {

    private Map<String, List<Booking>> confirmedBookings;
    private SeatLockManager seatLockManager;

    public BookingService(SeatLockManager seatLockManager) {
        this.seatLockManager = seatLockManager;
        this.confirmedBookings = new HashMap<>();
    }

    public synchronized Booking bookSeats(User user, Show show, List<Seat> requestedSeats){
        for(Seat seat:requestedSeats){
            if(isAlreadyBooked(show.getShowId(),seat)){
                throw new RuntimeException("Seat alrady booked: "+seat.getSeatId());
            }
            if(!seatLockManager.lockSeat(show.getShowId(), seat.getSeatId(),30000)){
                throw new RuntimeException("Seat temporarily locked: "+seat.getSeatId());
            }
        }

        Booking booking = new Booking(UUID.randomUUID().toString(),user,show,requestedSeats);
        confirmedBookings.putIfAbsent(show.getShowId(), new ArrayList<>());
        confirmedBookings.get(show.getShowId()).add(booking);
        return booking;
    }

    private boolean isAlreadyBooked(String showId, Seat seat){
        List<Booking> bookings = confirmedBookings.getOrDefault(showId,new ArrayList<>());
        return bookings.stream().flatMap(b->b.getSeats().stream()).anyMatch(s->s.getSeatId().equals(seat.getSeatId()));
    }
}
