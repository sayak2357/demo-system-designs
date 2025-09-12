package com.lld;

import java.util.ArrayList;
import java.util.List;

// this class assigns requests
public class ElevatorController {
    private List<Elevator> elevatorList;
    public ElevatorController(int numberOfElevators){
        this.elevatorList = new ArrayList<>();
        for(int i=0;i<numberOfElevators;i++){
            elevatorList.add(new Elevator(i));
        }
    }

    public void handleRequest(Request request){
        int destinationFloor = request.getFloor();
        Elevator bestElevator = null;
        int minDist = Integer.MAX_VALUE;

        for(Elevator elevator:elevatorList){
            int newDist = Math.abs(elevator.getCurrentFloor()-destinationFloor);
            if(newDist<minDist && elevator.isIdle()){
                minDist = newDist;
                bestElevator = elevator;
            }
        }
        // Fallback: assign to first elevator if none idle
        if(bestElevator==null && !elevatorList.isEmpty()){
           bestElevator = elevatorList.get(0);
        }

        if(bestElevator!=null){
            bestElevator.addRequest(destinationFloor);
            System.out.println("request assigned to elevator: "+bestElevator.getId()+" for floor: "+destinationFloor);
        }
    }

    public void stepAllElevators(){
        for(Elevator elevator:elevatorList){
            elevator.step();
        }
    }
}
