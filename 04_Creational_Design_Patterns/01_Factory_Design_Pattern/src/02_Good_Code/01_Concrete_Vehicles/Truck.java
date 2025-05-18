package Good_Code.ConcreteVehicles;


import Good_Code.Vehicle;

// Truck.java - Concrete class for Truck
public class Truck implements Vehicle {
    public void start() {
        System.out.println("Truck is starting...");
    }
    public void stop() {
        System.out.println("Truck is stopping...");
    }
}
