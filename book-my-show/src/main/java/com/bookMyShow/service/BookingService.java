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
        cleanupExpiredPendingBookings();

        for (Seat seat : requestedSeats) {
            if (isAlreadyBooked(show.getShowId(), seat)) {
                throw new RuntimeException("Seat already booked: " + seat.getSeatId());
            }
            if (seatLockManager.isSeatLockedByAnyone(show.getShowId(), seat.getSeatId())) {
                throw new RuntimeException("Seat temporarily locked: " + seat.getSeatId());
            }
            if (!seatLockManager.lockSeat(show.getShowId(), seat.getSeatId(), user.getId(), Constants.LOCK_TIMEOUT_MILLIS)) {
                throw new RuntimeException("Failed to acquire lock for seat: " + seat.getSeatId());
            }
        }

        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                user,
                show,
                requestedSeats
        );
        pendingBookings.put(booking.getBookingId(), booking);

        System.out.println("[PENDING] Booking created with ID " + booking.getBookingId() +
                " for user " + user.getName() +
                " on show " + show.getShowId() +
                " with seats " + requestedSeats);

        return booking;
    }

    /**
     * Confirm a booking after successful payment
     */
    public synchronized Booking confirmBooking(String bookingId) {
        cleanupExpiredPendingBookings();

        Booking booking = pendingBookings.get(bookingId);
        if (booking == null) {
            throw new RuntimeException("No pending booking found with id: " + bookingId);
        }

        for (Seat seat : booking.getSeats()) {
            if (!seatLockManager.isSeatLockedByUser(booking.getShow().getShowId(), seat.getSeatId(), booking.getUser().getId())) {
                throw new RuntimeException("Seat lock expired or taken: " + seat.getSeatId());
            }
        }

        booking.confirmBooking();
        confirmedBookings.putIfAbsent(booking.getShow().getShowId(), new ArrayList<>());
        confirmedBookings.get(booking.getShow().getShowId()).add(booking);

        // Release temporary locks
        for (Seat seat : booking.getSeats()) {
            seatLockManager.unlockSeat(booking.getShow().getShowId(), seat.getSeatId(), booking.getUser().getId());
        }

        pendingBookings.remove(bookingId);

        System.out.println("[CONFIRMED] Booking confirmed with ID " + booking.getBookingId() +
                " for user " + booking.getUser().getName() +
                " on show " + booking.getShow().getShowId() +
                " with seats " + booking.getSeats());

        return booking;
    }

    /**
     * Cancel booking (either user cancels or payment fails)
     */
    public synchronized void cancelBooking(String bookingId) {
        cleanupExpiredPendingBookings();

        Booking booking = pendingBookings.get(bookingId);
        if (booking != null) {
            for (Seat seat : booking.getSeats()) {
                seatLockManager.unlockSeat(booking.getShow().getShowId(), seat.getSeatId(), booking.getUser().getId());
            }
            booking.cancelBooking();
            pendingBookings.remove(bookingId);

            System.out.println("[CANCELLED] Booking cancelled with ID " + booking.getBookingId() +
                    " for user " + booking.getUser().getName() +
                    " on show " + booking.getShow().getShowId());
        }
    }

    /**
     * Expire bookings whose locks are no longer valid
     */
    private synchronized void cleanupExpiredPendingBookings() {
        List<String> toRemove = new ArrayList<>();
        for (Map.Entry<String, Booking> entry : pendingBookings.entrySet()) {
            Booking pending = entry.getValue();
            boolean allSeatsStillLockedByUser = true;
            for (Seat seat : pending.getSeats()) {
                if (!seatLockManager.isSeatLockedByUser(pending.getShow().getShowId(), seat.getSeatId(), pending.getUser().getId())) {
                    allSeatsStillLockedByUser = false;
                    break;
                }
            }
            if (!allSeatsStillLockedByUser) {
                pending.expireBooking();
                toRemove.add(entry.getKey());

                System.out.println("[EXPIRED] Booking expired with ID " + pending.getBookingId() +
                        " for user " + pending.getUser().getName() +
                        " on show " + pending.getShow().getShowId());
            }
        }
        for (String bookingId : toRemove) {
            pendingBookings.remove(bookingId);
        }
    }

    private boolean isAlreadyBooked(String showId, Seat seat) {
        List<Booking> bookings = confirmedBookings.getOrDefault(showId, new ArrayList<>());
        return bookings.stream()
                .flatMap(b -> b.getSeats().stream())
                .anyMatch(s -> s.getSeatId().equals(seat.getSeatId()));
    }
}
