package com.rideSharingV2.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private Gender gender;
    private int age;
    private List<Vehicle> vehicles;
    private List<Ride> sharedRides;
    private List<Ride> consumedRides;

    public User(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.vehicles = new ArrayList<>();
        this.sharedRides = new ArrayList<>();
        this.consumedRides = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle){
        this.vehicles.add(vehicle);
    }

    public boolean checkVehicle(String vehicleNumber){
        boolean flag = false;
        for(Vehicle vehicle:vehicles){
            if(vehicleNumber.equals(vehicle.getVehicleNumber())){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public List<Ride> getSharedRides() {
        return sharedRides;
    }

    public List<Ride> getConsumedRides() {
        return consumedRides;
    }

    public void addConsumedRide(Ride ride){
        consumedRides.add(ride);
    }

    public void addSharedRide(Ride ride){
        sharedRides.add(ride);
    }
}
