package com.rideSharingV2.Exceptions;

public class InvalidUser extends RuntimeException{
    public InvalidUser(){
        super("Invalid User");
    }
}
