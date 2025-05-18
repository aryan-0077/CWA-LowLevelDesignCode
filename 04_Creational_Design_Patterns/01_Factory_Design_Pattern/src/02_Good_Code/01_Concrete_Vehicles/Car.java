package Good_Code.ConcreteVehicles;

import Good_Code.Vehicle;

// Car.java - Concrete class for Car
public class Car implements Vehicle {
    public void start() {
        System.out.println("Car is starting...");
    }
    public void stop() {
        System.out.println("Car is stopping...");
    }
}

