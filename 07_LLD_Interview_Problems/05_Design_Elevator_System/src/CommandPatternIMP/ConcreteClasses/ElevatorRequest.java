package CommandPatternIMP.ConcreteClasses;

import CommandPatternIMP.ElevatorCommand;
import CommonEnums.Direction;
import UtilityClasses.ElevatorController;

public class ElevatorRequest implements ElevatorCommand {
    private int elevatorId; // ID of the elevator involved in the request
    private int floor; // Floor where the request is made
    private Direction requestDirection; // The direction of the elevator request
    private ElevatorController controller; // Reference to the ElevatorController to handle the request
    private boolean isInternalRequest; // Distinguishes internal vs external requests

    // Constructor to initialize the elevator request
    public ElevatorRequest(int elevatorId, int floor, boolean isInternalRequest,
                           Direction direction) {
        this.elevatorId = elevatorId;
        this.floor = floor;
        this.isInternalRequest = isInternalRequest;
        this.requestDirection = direction;
        this.controller = new ElevatorController();
    }

    // Execute method to process the request via the controller
    @Override
    public void execute() {
        if (isInternalRequest)
            controller.requestFloor(elevatorId, floor);
        else
            controller.requestElevator(elevatorId, floor, requestDirection);
    }

    // Getters and Setters for the ElevatorRequest
    public Direction getDirection() {
        return requestDirection;
    }

    public int getFloor() {
        return floor;
    }

    public boolean checkIsInternalRequest(){
        return isInternalRequest;
    }
}
