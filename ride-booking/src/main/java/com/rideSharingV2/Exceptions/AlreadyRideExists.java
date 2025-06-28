package com.rideSharingV2.Exceptions;

public class AlreadyRideExists extends RuntimeException{
    public AlreadyRideExists(){
        super("Ride already exists");
    }
}
