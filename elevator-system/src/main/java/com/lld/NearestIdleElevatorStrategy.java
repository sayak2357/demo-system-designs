package com.lld;

import java.util.List;
/*


Select the idle elevator closest to the request floor.

If multiple idle elevators are at the same distance, pick the one with fewer pending requests, then lower id as a tie-breaker.

If no idle elevators exist, fall back to the elevator with the lowest overall load.
 */
public class NearestIdleElevatorStrategy implements SchedulingStrategy{

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        int targetFloor = request.getFloor();
        Elevator bestElevator = null;
        int bestDistance = Integer.MAX_VALUE;

        // First pass: look for idle elevators
        for (Elevator e : elevators) {
            if (e.isIdle()) {
                int distance = Math.abs(e.getCurrentFloor() - targetFloor);
                if (bestElevator == null ||
                        distance < bestDistance ||
                        (distance == bestDistance && e.getPendingRequestsCount() < bestElevator.getPendingRequestsCount()) ||
                        (distance == bestDistance && e.getPendingRequestsCount() == bestElevator.getPendingRequestsCount()
                                && e.getId() < bestElevator.getId())) {
                    bestElevator = e;
                    bestDistance = distance;
                }
            }
        }

        // If no idle elevators found, fallback: pick the one with least load
        if (bestElevator == null) {
            for (Elevator e : elevators) {
                if (bestElevator == null ||
                        e.getPendingRequestsCount() < bestElevator.getPendingRequestsCount() ||
                        (e.getPendingRequestsCount() == bestElevator.getPendingRequestsCount()
                                && e.getId() < bestElevator.getId())) {
                    bestElevator = e;
                }
            }
        }

        return bestElevator;
    }
}