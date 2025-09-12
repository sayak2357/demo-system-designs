package com.lld;

import java.util.Collections;
import java.util.TreeSet;

public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private TreeSet<Integer> upQueue;
    private TreeSet<Integer> downQueue;

    public Elevator(int id){
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.upQueue = new TreeSet<>();
        this.downQueue = new TreeSet<>(Collections.reverseOrder());
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isIdle(){
        return this.upQueue.isEmpty() && this.downQueue.isEmpty();
    }

    public void addRequest(int floor){
        if(floor>currentFloor){
            this.upQueue.add(floor);
        }
        else if(floor<currentFloor){
            this.downQueue.add(floor);
        }
        else{
            System.out.println("elevator already in floor: "+floor);
        }
        updateDirection();
    }

    public void updateDirection(){
        if(this.direction==Direction.IDLE){
            if(!this.upQueue.isEmpty()){
                this.direction = Direction.UP;
            }else if(!this.downQueue.isEmpty()){
                this.direction = Direction.DOWN;
            }
        }
    }

    public void step(){
        switch(direction){
            case UP -> moveUp();
            case DOWN -> moveDown();
            case IDLE -> System.out.println("Elevator is idle at floor: "+currentFloor);
        }
    }

    private void moveUp(){
        this.currentFloor++;
        System.out.println("Elevator with id: "+id+" moved up to floor: "+currentFloor);
        if(this.upQueue.contains(currentFloor)){
            this.upQueue.remove(currentFloor);
            System.out.println("Elevator id: "+id+" stopped at floor: "+currentFloor);
        }

        if(this.upQueue.isEmpty()){
            if(!this.downQueue.isEmpty()){
                this.direction=Direction.DOWN;
            }
            else{
                this.direction = Direction.UP;
            }
        }
    }

    private void moveDown(){
        this.currentFloor--;
        System.out.println("Elevator with id: "+id+" moved down to floor: "+currentFloor);
        if(this.downQueue.contains(currentFloor)){
            this.downQueue.remove(currentFloor);
            System.out.println("Elevator id: "+id+" stopped at floor: "+currentFloor);
        }

        if(this.downQueue.isEmpty()){
            if(!this.upQueue.isEmpty()){
                this.direction=Direction.UP;
            }
            else{
                this.direction = Direction.DOWN;
            }
        }
    }

}
