package com.lld;

import java.util.List;

public interface SchedulingStrategy {
    Elevator selectElevator(List<Elevator> elevators, Request request);
}