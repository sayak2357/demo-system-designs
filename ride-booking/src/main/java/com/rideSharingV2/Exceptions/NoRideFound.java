package com.rideSharingV2.Exceptions;

public class NoRideFound extends RuntimeException{
    public NoRideFound(){
        super("No ride found");
    }
}
