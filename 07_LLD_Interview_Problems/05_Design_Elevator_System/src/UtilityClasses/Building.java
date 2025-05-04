package UtilityClasses;

public class Building {
    private String name; // Name of the building
    private int numberOfFloors; // Total number of floors in the building
    private ElevatorController
            elevatorController; // Controller to manage all elevators in the building
    // Constructor to initialize the building's details and its elevator system
    public Building(String name, int numberOfFloors, int numberOfElevators) {
        this.name = name; // Assign the building's name
        this.numberOfFloors = numberOfFloors; // Set the total number of floors
        // Initialize the elevator controller with the specified number of elevators
        // and floors
        this.elevatorController =
                new ElevatorController(numberOfElevators, numberOfFloors);
    }

    // Getters and Setters for the Building
    public String getName() {
        return name;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public ElevatorController getElevatorController() {
        return elevatorController;
    }
}
