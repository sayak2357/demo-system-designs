package com.bookMyShow;

import com.bookMyShow.model.*;
import com.bookMyShow.service.BookingService;
import com.bookMyShow.service.SeatLockManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome to BookMyShow!");
        BookingService bs = new BookingService(new SeatLockManager());
        User curr = new User("1","sam");
        Show show1 = new Show("StarWars1",new Movie("s1", Duration.ofMinutes(90),"Star Wars"), LocalDateTime.now(),
                Arrays.asList(new Seat("1","0",1),new Seat("2","0",2)));
        List<Seat> requested = Arrays.asList(new Seat("1","0",1));
        Booking booking = bs.bookSeats(curr,show1,requested);
        System.out.println("booking details: "+booking.toString());
    }
}