package com.lld;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to Elevator System!");
        SchedulingStrategy strategy = new OnTheWayElevatorStrategy();
        ElevatorController controller = new ElevatorController(3, strategy);

        controller.handleRequest(new Request(2));
        controller.handleRequest(new Request(7));
        controller.handleRequest(new Request(1));
        controller.handleRequest(new Request(5));

        for (int i = 0; i < 15; i++) {
            controller.stepAllElevators();
            Thread.sleep(1000);
        }
    }
}