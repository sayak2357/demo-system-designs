package com.bookMyShowV2;

import com.bookMyShowV2.dao.MovieTicketBookingDao;

public class Driver {
    public static void main(String[] args) {
        MovieTicketBookingDao movieTicketBookingDao = new MovieTicketBookingDao();
        movieTicketBookingDao.addCity("Kolkata",22.4,88.2);
        movieTicketBookingDao.addCinema(movieTicketBookingDao.getCinemaIdToCinemaMap().get("Kolkata").getCinemaId(),"INOX","Park Street");
        movieTicketBookingDao.addHall(movieTicketBookingDao.getCinemaIdToCinemaMap().get("Kolkata").getCinemaId());
    }
}
