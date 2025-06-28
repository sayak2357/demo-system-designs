package com.rideSharingV2.Exceptions;

public class VehicleAlreadyExists extends RuntimeException{
    public VehicleAlreadyExists(){
        super("Vehicle already exists");
    }
}
