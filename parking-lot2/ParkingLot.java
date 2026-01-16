package com.gfg.parkinglot;

import com.gfg.parkinglot.exceptions.ParkingErrorCode;
import com.gfg.parkinglot.exceptions.ParkingException;

import java.util.*;

public class ParkingLot {

    private Map<SlotType, Queue<ParkingSlot>> emptySlots;
    private Map<String, ParkingTicket> activeTickets;
    private InputReader inputReader;

    public ParkingLot(InputReader inputReader){
        this.emptySlots = new HashMap<>();
        this.activeTickets = new HashMap<>();
        this.inputReader = inputReader;
    }
    public void vehicleEntry()
    {
        String licensePlateId = inputReader.getLicensePlateId();
        VehicleType vehicleType = inputReader.getVehicleType();
        if(activeTickets.get(licensePlateId)!=null){
            throw new ParkingException(ParkingErrorCode.DUPLICATE_VEHICLE);
        }
        Vehicle vehicle = new Vehicle(licensePlateId, vehicleType);
        ParkingSlot parkingSlot = findParkingSlot(vehicle);
        if(parkingSlot!=null){
            ParkingTicket parkingTicket = ParkingTicket.builder()
                    .withVehicle(vehicle)
                    .withParkingSlot(parkingSlot)
                    .withEntryTime(System.currentTimeMillis())
                    .build();

            parkingSlot.assignVehicle(vehicle);
            this.activeTickets.put(licensePlateId, parkingTicket);
            System.out.println("Parking ticket created with ID:" +parkingTicket.getTicketId());
            System.out.println("Vehicle assigned to slotType:" +parkingSlot.getSlotType());
        }
        else{
            throw new ParkingException(ParkingErrorCode.NO_SLOTS_AVAILABLE);
        }

    }
    public void vehicleExit(){
        String licensePlateId = inputReader.getLicensePlateId();
        ParkingTicket parkingTicket = this.activeTickets.get(licensePlateId);
        if(parkingTicket == null){
            throw new ParkingException(ParkingErrorCode.NO_PARKING_TICKET);
        }
        Payment payment = inputReader.getPaymentDetails();
        TicketStatus ticketStatus = parkingTicket.attemptPayment(payment);
        if(ticketStatus == TicketStatus.PAID){
            this.activeTickets.remove(licensePlateId);
            ParkingSlot currentSlot = parkingTicket.getParkingSlot();
            currentSlot.removeVehicle();
            this.emptySlots.get(currentSlot.getSlotType()).add(currentSlot);
            System.out.println("Vehicle can exit now!");
        }
        else{
            throw new ParkingException(ParkingErrorCode.PAYMENT_FAILED);
        }
    }
    private ParkingSlot findParkingSlot(Vehicle vehicle){
        List<SlotType> slotPriority = Arrays.asList(SlotType.SMALL,SlotType.STANDARD,SlotType.LARGE);
        for(SlotType slotType:slotPriority){
            System.out.println("Checking slotType: "+slotType);
            System.out.println("slot width="+slotType.getWidth()+"\ncar width="+vehicle.getVehicleType().getWidth());
            System.out.println("slot length="+slotType.getLength()+"\ncar length="+vehicle.getVehicleType().getLength());
            if(slotType.getWidth()>=vehicle.getVehicleType().getWidth()
                && slotType.getLength()>= vehicle.getVehicleType().getLength() && emptySlots.containsKey(slotType) && !emptySlots.get(slotType).isEmpty()){
                return emptySlots.get(slotType).poll();
            }
        }
        return null;
    }
    public void addEmptySlot(ParkingSlot parkingSlot){
        if(this.emptySlots.get(parkingSlot.getSlotType()) == null){
            this.emptySlots.put(parkingSlot.getSlotType(), new LinkedList<>());
        }
        this.emptySlots.get(parkingSlot.getSlotType()).add(parkingSlot);
    }
    public void printStatus(){
        for(SlotType slotType:this.emptySlots.keySet()){
            System.out.println("SlotType: "+slotType.toString()+", Empty slots: "+this.emptySlots.get(slotType).size());
        }
        System.out.println("No of vehicles in parking slot: "+this.activeTickets.size());
    }
}
