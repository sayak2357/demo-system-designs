package com.lld;


// simulation class
public class ElevatorSystem {
    public static void main(String[] args) throws InterruptedException {
        ElevatorController elevatorController = new ElevatorController(2);

        elevatorController.handleRequest(new Request(2));
        elevatorController.handleRequest(new Request(1));
        elevatorController.handleRequest(new Request(5));
        elevatorController.handleRequest(new Request(1));

        // Simulate movement for 15 time steps
        for (int i = 0; i < 15; i++) {
            elevatorController.stepAllElevators();
            Thread.sleep(1000); // Simulate 1 second per step
        }
    }
}
