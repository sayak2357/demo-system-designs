package com.bookMyShow.model;

import java.time.LocalDateTime;
import java.util.List;
public class Booking {
    private String bookingId;
    private User user;
    private Show show;
    private List<Seat> seats;
    private LocalDateTime timestamp;
    private BookingStatus status;

    public Booking(String bookingId, User user, Show show, List<Seat> seats) {
        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.timestamp = LocalDateTime.now();
        this.status = BookingStatus.PENDING;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void confirmBooking(){
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancelBooking(){
        this.status = BookingStatus.CANCELLED;
    }

    public void expireBooking(){
        this.status =  BookingStatus.EXPIRED;
    }

    @Override
    public String toString(){
        return "booking id: "+bookingId+" \n booked by: "+user.getName()+"\n show for movie:"+show.getMovie().getName()+" booked seats:"+seats.toString();
    }
}
