package com.rideSharingV2.Exceptions;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(){
        super("User already exists");
    }
}
