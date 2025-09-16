package com.bookMyShow.model;

public enum BookingStatus {

        PENDING,     // booking created, seats locked, waiting for payment
        CONFIRMED,   // payment successful, booking finalized
        CANCELLED,   // user cancelled or payment failed
        EXPIRED      // lock expired before confirmation

}
