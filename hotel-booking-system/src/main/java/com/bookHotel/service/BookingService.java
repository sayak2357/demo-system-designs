package com.bookHotel.service;

import com.bookHotel.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private List<Hotel> hotels;
    private List<Booking> bookings;

    public BookingService(List<Hotel> hotels) {
        this.hotels = hotels;
        this.bookings = new ArrayList<>();
    }

    public List<Room> search(String location, LocalDate in, LocalDate out){
        List<Room> result = new ArrayList<>();
        for(Hotel hotel:hotels){
            if(!hotel.getLocation().equalsIgnoreCase(location))
                continue;
            for(Room room: hotel.getRooms()){
                if(room.isAvailable(in,out)){
                    result.add(room);
                }
            }
        }
        return result;
    }

    public Booking book(User user, Room room, LocalDate in, LocalDate out){
        if(!room.isAvailable(in,out))
            throw new RuntimeException("Room not available");
        room.reserve(in,out);
        Booking booking = new Booking(user,room,in,out);
        booking.updateStatus(BookingStatus.CONFIRMED);
        bookings.add(booking);
        return booking;
    }

    public void cancelBooking(String bookingId){
        for(Booking b:bookings){
            if(b.getBookingId().equals(bookingId)){
                b.getRoom().cancel(b.getCheckIn(),b.getCheckOut());
                b.updateStatus(BookingStatus.CANCELLED);
                break;
            }
        }
    }

}
