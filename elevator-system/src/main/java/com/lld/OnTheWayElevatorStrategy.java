package com.lld;

import java.util.List;

public class OnTheWayElevatorStrategy implements SchedulingStrategy {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        int targetFloor = request.getFloor();
        Elevator bestElevator = null;
        int minDist = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int currentFloor = elevator.getCurrentFloor();
            Direction dir = elevator.getDirection();

            // Elevator is idle → can take the request
            if (elevator.isIdle()) {
                int distance = Math.abs(currentFloor - targetFloor);
                if (distance < minDist) {
                    minDist = distance;
                    bestElevator = elevator;
                }
            }

            // Elevator is moving UP and request floor is above current
            else if (dir == Direction.UP && targetFloor >= currentFloor) {
                int distance = targetFloor - currentFloor;
                if (distance < minDist) {
                    minDist = distance;
                    bestElevator = elevator;
                }
            }

            // Elevator is moving DOWN and request floor is below current
            else if (dir == Direction.DOWN && targetFloor <= currentFloor) {
                int distance = currentFloor - targetFloor;
                if (distance < minDist) {
                    minDist = distance;
                    bestElevator = elevator;
                }
            }
        }

        // fallback: pick first elevator if none matched
        if (bestElevator == null && !elevators.isEmpty()) {
            bestElevator = elevators.get(0);
        }

        return bestElevator;
    }
}
