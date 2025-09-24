package com.lld;

import java.util.List;
import java.util.Random;

public class OnTheWayElevatorStrategy implements SchedulingStrategy {

    // Penalties to bias selection:
    // - AWAY_PENALTY: when elevator is moving in the opposite direction, discourage assigning
    // - IDLE_PENALTY: idle elevators should be considered, but slightly penalized
    private static final int AWAY_PENALTY = 10000;
    private static final int IDLE_PENALTY = 100;

    private final Random random = new Random();

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        int targetFloor = request.getFloor();   // floor where the request is made
        Elevator best = null;                   // best elevator candidate
        int bestScore = Integer.MAX_VALUE;      // lower score = better elevator

        // Iterate over all elevators to find the best one
        for (Elevator e : elevators) {
            int cur = e.getCurrentFloor();
            Direction dir = e.getDirection();
            int distance = Math.abs(cur - targetFloor);
            int score;

            // CASE 1: Elevator is idle
            // Score = distance + IDLE_PENALTY (so nearby idle elevators are preferred)
            if (e.isIdle()) {
                score = distance + IDLE_PENALTY;

                // CASE 2: Elevator is going UP and the request is above its current floor
                // Good candidate → score = direct distance
            } else if (dir == Direction.UP && targetFloor >= cur) {
                score = targetFloor - cur;

                // CASE 3: Elevator is going DOWN and the request is below its current floor
                // Good candidate → score = direct distance
            } else if (dir == Direction.DOWN && targetFloor <= cur) {
                score = cur - targetFloor;

                // CASE 4: Elevator is moving AWAY from the request
                // Bad candidate → apply huge penalty
            } else {
                score = distance + AWAY_PENALTY;
            }

            // Compare with best candidate so far
            if (best == null) {
                best = e;
                bestScore = score;

            } else if (score < bestScore) {
                // Found a strictly better elevator
                best = e;
                bestScore = score;

            } else if (score == bestScore) {
                // Tie-breaking:
                // 1. Prefer elevator with fewer pending requests (less busy)
                int pendingBest = best.getPendingRequestsCount();
                int pendingE = e.getPendingRequestsCount();

                if (pendingE < pendingBest) {
                    best = e;

                    // 2. If still tied, pick randomly to avoid bias toward elevator with lower ID
                } else if (pendingE == pendingBest) {
                    if (random.nextBoolean()) {
                        best = e;
                    }
                }
            }
        }

        // Return the chosen elevator
        return best;
    }
}
