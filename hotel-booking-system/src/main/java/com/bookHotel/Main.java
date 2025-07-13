package com.bookHotel;

import com.bookHotel.entity.*;
import com.bookHotel.service.BookingService;

import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to Hotel Booking Service!");

        Room r1 = new Room("R1", RoomType.SINGLE);
        Room r2 = new Room("R2", RoomType.DOUBLE);

        Hotel hotel = new Hotel("H1","OYO","Kolkata", List.of(r1,r2));

        BookingService bookingService = new BookingService(List.of(hotel));
        User user = new User("U1","Sam");

        LocalDate checkIn = LocalDate.of(2025,7,13);
        LocalDate checkOut = LocalDate.of(2025,7,15);

        List<Room> available = bookingService.search("Kolkata",checkIn,checkOut);
        Booking booking = bookingService.book(user,available.get(0),checkIn,checkOut);

        System.out.println("Booking Id: "+booking.getBookingId());
    }
}