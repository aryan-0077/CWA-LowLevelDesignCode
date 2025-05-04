import CommonEnums.Direction;
import ObserverPatternIMP.ElevatorDisplay;
import SchedulingAlgoStrategyPattern.ConcreteStrategies.FCFSSchedulingStrategy;
import SchedulingAlgoStrategyPattern.ConcreteStrategies.ScanSchedulingStrategy;
import UtilityClasses.Building;
import UtilityClasses.Elevator;
import UtilityClasses.ElevatorController;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize a building with 10 floors and 3 elevators
        Building building = new Building("Office Tower", 10, 3);
        ElevatorController controller = building.getElevatorController();
        // Create an ElevatorDisplay to observe and display elevator events
        ElevatorDisplay display = new ElevatorDisplay();
        for (Elevator elevator : controller.getElevators()) {
            elevator.addObserver(display); // Add the display as an observer for all elevators
        }
        // Simulate elevator requests using a command-line interface
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        // Display simulation details and options
        System.out.println("Elevator System Simulation");
        System.out.println("Building: " + building.getName());
        System.out.println("Floors: " + building.getNumberOfFloors());
        System.out.println("Elevators: " + controller.getElevators().size());
        // Main loop for user interactions
        while (running) {
            System.out.println("nSelect an option:");
            System.out.println("1. Request elevator (external)");
            System.out.println("2. Request floor (internal)");
            System.out.println("3. Simulate next step");
            System.out.println("4. Change scheduling strategy");
            System.out.println("5. Exit simulation");
            int choice = scanner.nextInt(); // Read user's menu choice
            switch (choice) {
                case 1:
                    // Handle external elevator request
                    // Handle internal elevator floor request
                    System.out.print("Enter elevator ID: ");
                    int externalElevatorId = scanner.nextInt();
                    controller.setCurrentElevator(externalElevatorId); // Set the selected elevator
                    System.out.print("Enter floor number: ");
                    int floorNum = scanner.nextInt();
                    System.out.print("Direction (1 for UP, 2 for DOWN): ");
                    int dirChoice = scanner.nextInt();
                    Direction dir = dirChoice == 1 ? Direction.UP : Direction.DOWN;
                    controller.requestElevator(externalElevatorId, floorNum, dir);
                    break;
                case 2:
                    // Handle internal elevator floor request
                    System.out.print("Enter elevator ID: ");
                    int elevatorId = scanner.nextInt();
                    controller.setCurrentElevator(elevatorId); // Set the selected elevator
                    System.out.print("Enter destination floor: ");
                    int destFloor = scanner.nextInt();
                    controller.requestFloor(elevatorId, destFloor);
                    break;
                case 3:
                    // Simulate the next step in the system
                    System.out.println("Simulating next step...");
                    controller.step(); // Perform the simulation step
                    displayElevatorStatus(
                            controller.getElevators()); // Display elevator statuses
                    break;
                case 4:
                    // Change the scheduling strategy
                    System.out.println("Select strategy:");
                    System.out.println("1. SCAN Algorithm");
                    System.out.println("2. FCFS Algorithm");
                    System.out.println("3. Look Algorithm");
                    int strategyChoice = scanner.nextInt();
                    if (strategyChoice == 1) {
                        controller.setSchedulingStrategy(new ScanSchedulingStrategy());
                        System.out.println("Strategy set to SCAN Algorithm");
                    } else {
                        controller.setSchedulingStrategy(new FCFSSchedulingStrategy());
                        System.out.println("Strategy set to Nearest Elevator First");
                    }
                    break;
                case 5:
                    // Exit the simulation
                    running = false;
                    break;
                default:
                    // Handle invalid choices
                    System.out.println("Invalid choice!");
            }
        }
        scanner.close(); // Close the scanner to release resources
        System.out.println("Simulation ended"); // End of simulation
    }

    // Display the status of all elevators in the system
    private static void displayElevatorStatus(List<Elevator> elevators) {
        System.out.println("nElevator Status:");
        for (Elevator elevator : elevators) {
            // Print details of each elevator, including current floor, direction, and
            // state
            System.out.println("Elevator " + elevator.getId() + ": Floor "
                    + elevator.getCurrentFloor() + ", Direction "
                    + elevator.getDirection() + ", State " + elevator.getState()
                    + ", Destinations " + elevator.getDestinationFloors());
        }
    }
    }

    /*

    Output :

    Elevator System Simulation
Building: Office Tower
Floors: 10
Elevators: 3
nSelect an option:
1. Request elevator (external)
2. Request floor (internal)
3. Simulate next step
4. Change scheduling strategy
5. Exit simulation
1
Enter elevator ID: 1
Enter floor number: 1
Direction (1 for UP, 2 for DOWN): 2
External request: Floor 1, Direction DOWN
Elevator 1 state changed to MOVING
Assigned elevator 1 to floor 1
nSelect an option:
1. Request elevator (external)
2. Request floor (internal)
3. Simulate next step
4. Change scheduling strategy
5. Exit simulation
1
Enter elevator ID: 1
Enter floor number: 12
Direction (1 for UP, 2 for DOWN): 1
External request: Floor 12, Direction UP
Assigned elevator 1 to floor 12
nSelect an option:
1. Request elevator (external)
2. Request floor (internal)
3. Simulate next step
4. Change scheduling strategy
5. Exit simulation
2
Enter elevator ID: 1
Enter destination floor: 2
Internal request: Elevator 1 to floor 2
nSelect an option:
1. Request elevator (external)
2. Request floor (internal)
3. Simulate next step
4. Change scheduling strategy
5. Exit simulation
4
Select strategy:
1. SCAN Algorithm
2. FCFS Algorithm
3. Look Algorithm
2
Strategy set to Nearest Elevator First
nSelect an option:
1. Request elevator (external)
2. Request floor (internal)
3. Simulate next step
4. Change scheduling strategy
5. Exit simulation
1
Enter elevator ID: 1
Enter floor number: 34
Direction (1 for UP, 2 for DOWN): 2
External request: Floor 34, Direction DOWN
Assigned elevator 1 to floor 34
nSelect an option:
1. Request elevator (external)
2. Request floor (internal)
3. Simulate next step
4. Change scheduling strategy
5. Exit simulation
5
Simulation ended

    */