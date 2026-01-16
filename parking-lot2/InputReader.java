package com.gfg.parkinglot;

import java.util.Scanner;

public class InputReader {
    private Scanner scanner;
    public InputReader(){
        this.scanner = new Scanner(System.in);
    }
    public String getLicensePlateId(){
        System.out.println("Please enter license plate id: ");
        String licensePlateid = scanner.next();

        return licensePlateid;
    }

    public VehicleType getVehicleType(){
        System.out.println("Please enter vehicle type: ");
        VehicleType vehicleType = VehicleType.valueOf(scanner.next());

        // add validation and re-attempts here

        return vehicleType;
    }

    public Payment getPaymentDetails(){
        // collect Payment details,
        // like type of payment, card details
        String cardNo = "12345";
        String cvv = "456";
        return new CreditCardPayment(new CardDetails(cardNo,cvv));
    }

    public OperationType getOperation(){
        System.out.println("Enter what do you want to do: ");
        System.out.printf("Press 1 for vehicle entry, 2 for vehicle exit & 3 for exit: ");
        int type = scanner.nextInt();
        return OperationType.fromValue(type);
    }

}
