package com.gfg.parkinglot.exceptions;

import com.gfg.parkinglot.exceptions.ParkingErrorCode;

public class ParkingException extends RuntimeException{
    private ParkingErrorCode parkingErrorCode;

    public ParkingException(ParkingErrorCode parkinErrorCode){
        this.parkingErrorCode = parkinErrorCode;
    }

    public ParkingErrorCode getParkingErrorCode() {
        return parkingErrorCode;
    }
}
