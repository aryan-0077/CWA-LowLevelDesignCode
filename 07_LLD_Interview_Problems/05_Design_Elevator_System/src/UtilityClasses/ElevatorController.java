package UtilityClasses;

import CommandPatternIMP.ConcreteClasses.ElevatorRequest;
import CommonEnums.Direction;
import SchedulingAlgoStrategyPattern.ConcreteStrategies.ScanSchedulingStrategy;
import SchedulingAlgoStrategyPattern.SchedulingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    // List of all elevators in the system
    private List<Elevator> elevators;
    // List of all floors in the building
    private List<Floor> floors;
    // Strategy to determine the scheduling of elevators
    private SchedulingStrategy schedulingStrategy;
    // ID of the current elevator (used for internal operations)
    private int currentElevatorId;
    // Constructor to initialize elevators and floors

    public ElevatorController(){

    }

    public ElevatorController(int numberOfElevators, int numberOfFloors) {
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.schedulingStrategy = new ScanSchedulingStrategy(); // Default strategy
        // Initialize elevators with unique IDs
        for (int i = 1; i <= numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }
        // Initialize floors
        for (int i = 1; i <= numberOfFloors; i++) {
            floors.add(new Floor(i));
        }
    }

    // Set the scheduling strategy dynamically
    public void setSchedulingStrategy(SchedulingStrategy strategy) {
        this.schedulingStrategy = strategy;
    }

    // Handle external elevator requests from a specific floor
    public void requestElevator(int elevatorId, int floorNumber, Direction direction) {
        System.out.println(
                "External request: Floor " + floorNumber + ", Direction " + direction);
        // Find the elevator by its ID
        Elevator selectedElevator = getElevatorById(elevatorId);
        if (selectedElevator != null) {
            // Add the request to the selected elevator
            selectedElevator.addRequest(
                    new ElevatorRequest(elevatorId, floorNumber, false, direction));
            System.out.println("Assigned elevator " + selectedElevator.getId()
                    + " to floor " + floorNumber);
        } else {
            // If no suitable elevator is found
            System.out.println("No elevator available for floor " + floorNumber);
        }
    }

    // Handle internal elevator requests to a specific floor
    public void requestFloor(int elevatorId, int floorNumber) {
        // Find the elevator by its ID
        Elevator elevator = getElevatorById(elevatorId);
        System.out.println("Internal request: Elevator " + elevator.getId()
                + " to floor " + floorNumber);
        // Determine the direction of the request
        Direction direction = floorNumber > elevator.getCurrentFloor()
                ? Direction.UP
                : Direction.DOWN;
        // Add the request to the elevator
        elevator.addRequest(
                new ElevatorRequest(elevatorId, floorNumber, true, direction));
    }

    // Find an elevator by its ID
    private Elevator getElevatorById(int elevatorId) {
        for (Elevator elevator : elevators) {
            if (elevator.getId() == elevatorId)
                return elevator;
        }
        return null; // Return null if no matching elevator is found
    }

    // Perform a simulation step by moving all elevators
    public void step() {
        // Iterate through all elevators
        for (Elevator elevator : elevators) {
            // Only process elevators with pending requests
            if (!elevator.getRequestsQueue().isEmpty()) {
                // Use the scheduling strategy to find the next stop
                int nextStop = schedulingStrategy.getNextStop(elevator);


                // Move the elevator to the next stop if needed
                if (elevator.getCurrentFloor() != nextStop)
                    elevator.moveToNextStop(nextStop);
            }
        }
    }

    // Get the list of all elevators
    public List<Elevator> getElevators() {
        return elevators;
    }

    // Get the list of all floors
    public List<Floor> getFloors() {
        return floors;
    }

    // Set the ID of the current elevator
    public void setCurrentElevator(int elevatorId) {
        this.currentElevatorId = elevatorId;
    }
}
