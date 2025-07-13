package com.bookHotel.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Booking {
    private String bookingId;
    private User user;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BookingStatus bookingStatus;

    public Booking(User user, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.bookingId = UUID.randomUUID().toString();
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.bookingStatus = BookingStatus.NEW;
    }

    public void updateStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public Room getRoom() {
        return room;
    }

    public User getUser() {
        return user;
    }

    public String getBookingId() {
        return bookingId;
    }
}
