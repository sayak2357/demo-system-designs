package com.lld;

import java.util.ArrayList;
import java.util.List;

// this class assigns requests
public class ElevatorController {
    private final List<Elevator> elevatorList;
    private SchedulingStrategy schedulingStrategy;
    public ElevatorController(int numberOfElevators, SchedulingStrategy schedulingStrategy){
        this.elevatorList = new ArrayList<>();
        for(int i=0;i<numberOfElevators;i++){
            elevatorList.add(new Elevator(i));
        }
        this.schedulingStrategy = schedulingStrategy;
    }

    public void handleRequest(Request request){
        Elevator bestElevator = schedulingStrategy.selectElevator(elevatorList, request);
        if (bestElevator != null) {
            bestElevator.addRequest(request.getFloor());
            System.out.println("Request for floor " + request.getFloor() +
                    " assigned to elevator: " + bestElevator.getId());
        }
    }

    public void stepAllElevators(){
        for(Elevator elevator:elevatorList){
            elevator.step();
        }
    }
}
