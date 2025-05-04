package SchedulingAlgoStrategyPattern.ConcreteStrategies;

import CommandPatternIMP.ConcreteClasses.ElevatorRequest;
import CommonEnums.Direction;
import SchedulingAlgoStrategyPattern.SchedulingStrategy;
import UtilityClasses.Elevator;

import java.util.Queue;

public class LookSchedulingStrategy implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        int currentFloor = elevator.getCurrentFloor();
        Queue<ElevatorRequest> requests = elevator.getRequestsQueue();
        // If there are no pending requests, remain on the current floor.
        if (requests == null || requests.isEmpty()) {
            return currentFloor;
        }
        // Determine the primary target from the first request in the queue.
        ElevatorRequest primaryRequest = requests.peek();
        int primaryFloor = primaryRequest.getFloor();
        // Determine the travel direction based on the primary target.
        Direction travelDirection;
        if (primaryFloor > currentFloor) {
            travelDirection = Direction.UP;
        } else if (primaryFloor < currentFloor) {
            travelDirection = Direction.DOWN;
        } else {
            return currentFloor; // Already at the requested floor.
        }
        // Look for any request along the journey from currentFloor to primaryFloor.
        // For upward movement, we need the smallest floor greater than currentFloor and <=
        // primaryFloor. For downward movement, we need the largest floor less than currentFloor and >=
        // primaryFloor.
        Integer candidate = null;

        for (ElevatorRequest req : requests) {
            int reqFloor = req.getFloor();
            // Check if the request is within the range between currentFloor and primaryFloor.
            if (travelDirection == Direction.UP && reqFloor > currentFloor && reqFloor <= primaryFloor) {
                // For internal requests we always consider; for external requests, only if they are going
                // UP.
                if (req.checkIsInternalRequest()
                        || (!req.checkIsInternalRequest() && req.getDirection() == Direction.UP)) {
                    // Choose the candidate that is closest to the current floor (i.e. the smallest floor
                    // greater than currentFloor).
                    if (candidate == null || reqFloor < candidate) {
                        candidate = reqFloor;
                    }
                }
            } else if (travelDirection == Direction.DOWN && reqFloor < currentFloor
                    && reqFloor >= primaryFloor) {
                // For downward movement, consider the request if internal or if external with direction
                // DOWN.
                if (req.checkIsInternalRequest()
                        || (!req.checkIsInternalRequest() && req.getDirection() == Direction.DOWN)) {
                    // For a downward journey, we choose the candidate that is closest to the current floor
                    // (i.e. the largest floor less than currentFloor).
                    if (candidate == null || reqFloor > candidate) {
                        candidate = reqFloor;
                    }
                }
            }
        }
        // If a candidate was found in the path, return that as the next stop;
        // otherwise, fall back to the primary target.
        return (candidate != null) ? candidate : primaryFloor;
    }
}
