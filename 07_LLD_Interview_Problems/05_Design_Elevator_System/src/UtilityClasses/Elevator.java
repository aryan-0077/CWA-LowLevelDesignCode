package UtilityClasses;

import CommandPatternIMP.ConcreteClasses.ElevatorRequest;
import CommonEnums.*;
import ObserverPatternIMP.ElevatorObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Elevator {
    // Unique ID for the elevator
    private int id;
    // Current floor where the elevator is located
    private int currentFloor;
    // Current direction of the elevator (UP, DOWN, or IDLE)
    private Direction direction;
    // Current operational state of the elevator (IDLE, MOVING, etc.)
    private ElevatorState state;
    // List of observers to monitor elevator events
    private List<ElevatorObserver> observers;
    // Queue to manage all requests (both internal and external)
    private Queue<ElevatorRequest> requests;
    // Constructor to initialize the elevator
    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1; // Default initial floor
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.observers = new ArrayList<>();
        this.requests = new LinkedList<>();
    }
    // Add an observer to monitor elevator events
    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    // Remove an observer
    public void removeObserver(ElevatorObserver observer) {
        observers.remove(observer);
    }

    // Notify all observers about a state change
    private void notifyStateChange(ElevatorState state) {
        for (ElevatorObserver observer : observers) {
            observer.onElevatorStateChange(this, state);
        }
    }

    // Notify all observers about a floor change
    private void notifyFloorChange(int floor) {
        for (ElevatorObserver observer : observers) {
            observer.onElevatorFloorChange(this, floor);
        }
    }

    // Set a new state for the elevator and notify observers
    public void setState(ElevatorState newState) {
        this.state = newState;
        notifyStateChange(newState);
    }

    // Set the direction of the elevator
    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    // Add a new floor request to the queue
    public void addRequest(ElevatorRequest elevatorRequest) {
        // Avoid duplicate requests
        if (!requests.contains(elevatorRequest)) {
            requests.add(elevatorRequest);
        }

        int requestedFloor = elevatorRequest.getFloor();
        // If elevator is idle, determine direction and start moving
        if (state == ElevatorState.IDLE && !requests.isEmpty()) {
            if (requestedFloor > currentFloor) {
                direction = Direction.UP;
            } else if (requestedFloor < currentFloor) {
                direction = Direction.DOWN;
            }
            setState(ElevatorState.MOVING);
        }
    }

    // Move the elevator to the next stop as decided by the scheduling strategy
    public void moveToNextStop(int nextStop) {
        // Only move if the elevator is currently in the MOVING state
        if (state != ElevatorState.MOVING)
            return;
        while (currentFloor != nextStop) {
            // Update floor based on direction
            if (direction == Direction.UP) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            // Notify observers about the floor change
            notifyFloorChange(currentFloor);
            // Complete arrival once the target floor is reached
            if (currentFloor == nextStop) {
                completeArrival();
                return;
            }
        }
    }
    // Handle the elevator's arrival at a destination floor
    private void completeArrival() {
        // Stop the elevator and notify observers
        setState(ElevatorState.STOPPED);
        // Remove the current floor from the requests queue
        requests.removeIf(request -> request.getFloor() == currentFloor);
        // If no more requests, set state to IDLE
        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            setState(ElevatorState.IDLE);
        } else {
            // Otherwise, continue moving after a brief stop
            setState(ElevatorState.MOVING);
        }
    }
    // Get the elevator's ID
    public int getId() {
        return id;
    }
    // Get the elevator's current floor
    public int getCurrentFloor() {
        return currentFloor;
    }
    // Get the elevator's current direction
    public Direction getDirection() {
        return direction;
    }
    // Get the elevator's current state
    public ElevatorState getState() {
        return state;
    }
    // Get a copy of the current requests queue to prevent external modification
    public Queue<ElevatorRequest> getRequestsQueue() {
        return new LinkedList<>(requests);
    }
    // Get a list of all destination floors for display purposes
    public List<ElevatorRequest> getDestinationFloors() {
        return new ArrayList<>(requests);
    }
}
