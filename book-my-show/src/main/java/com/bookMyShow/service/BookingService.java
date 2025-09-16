package com.bookMyShow.service;

import com.bookMyShow.constants.Constants;
import com.bookMyShow.model.Booking;
import com.bookMyShow.model.Seat;
import com.bookMyShow.model.Show;
import com.bookMyShow.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BookingService {

    private final Map<String, List<Booking>> confirmedBookings; // showId -> confirmed bookings
    private final Map<String, Booking> pendingBookings;          // bookingId -> pending booking
    private final SeatLockManager seatLockManager;

    public BookingService(SeatLockManager seatLockManager) {
        this.seatLockManager = seatLockManager;
        this.confirmedBookings = new ConcurrentHashMap<>();
        this.pendingBookings = new ConcurrentHashMap<>();
    }
    /**
     * Try to create a pending booking by locking seats for a user
     */
    public synchronized Booking createPendingBooking(User user, Show show, List<Seat> requestedSeats) {
        // Check if already booked
        for (Seat seat : requestedSeats) {
            if (isAlreadyBooked(show.getShowId(), seat)) {
                throw new RuntimeException("Seat already booked: " + seat.getSeatId());
            }
            if (!seatLockManager.lockSeat(show.getShowId(), seat.getSeatId(), user.getId(), Constants.LOCK_TIMEOUT_MILLIS)) {
                throw new RuntimeException("Seat temporarily locked: " + seat.getSeatId());
            }
        }

        Booking booking = new Booking(UUID.randomUUID().toString(), user, show, requestedSeats);
        pendingBookings.put(booking.getBookingId(), booking);
        return booking;
    }

    /**
     * Confirm a booking after successful payment
     */
    public synchronized Booking confirmBooking(String bookingId) {
        Booking booking = pendingBookings.get(bookingId);
        if (booking == null) {
            throw new RuntimeException("No pending booking found with id: " + bookingId);
        }

        // Verify locks are still valid
        for (Seat seat : booking.getSeats()) {
            if (!seatLockManager.isSeatLocked(booking.getShow().getShowId(), seat.getSeatId(), booking.getUser().getId())) {
                throw new RuntimeException("Seat lock expired: " + seat.getSeatId());
            }
        }

        booking.confirmBooking();
        confirmedBookings.putIfAbsent(booking.getShow().getShowId(), new ArrayList<>());
        confirmedBookings.get(booking.getShow().getShowId()).add(booking);

        // cleanup pending map
        pendingBookings.remove(bookingId);

        return booking;
    }


    /**
     * Cancel booking (either user cancels or payment fails)
     */
    public synchronized void cancelBooking(String bookingId) {
        Booking booking = pendingBookings.get(bookingId);
        if (booking != null) {
            for (Seat seat : booking.getSeats()) {
                seatLockManager.unlockSeat(booking.getShow().getShowId(), seat.getSeatId(), booking.getUser().getId());
            }
            pendingBookings.remove(bookingId);
        }
    }
    private boolean isAlreadyBooked(String showId, Seat seat){
        List<Booking> bookings = confirmedBookings.getOrDefault(showId,new ArrayList<>());
        return bookings.stream().flatMap(b->b.getSeats().stream()).anyMatch(s->s.getSeatId().equals(seat.getSeatId()));
    }
}
