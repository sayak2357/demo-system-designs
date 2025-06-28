package com.rideSharingV2.model;

public class Vehicle {
    private String userName;
    private String model;
    private String vehicleNumber;

    public Vehicle(String userName, String model, String vehicleNumber) {
        this.userName = userName;
        this.model = model;
        this.vehicleNumber = vehicleNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getModel() {
        return model;
    }



    public String getVehicleNumber() {
        return vehicleNumber;
    }

}
