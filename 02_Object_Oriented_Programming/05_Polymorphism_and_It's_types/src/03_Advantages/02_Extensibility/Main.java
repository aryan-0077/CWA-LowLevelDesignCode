package Advantages.Extensibility;

import Advantages.*;

public class Main {
    public static void main(String[] args) {
        // Extensible: Add new vehicle types without changing existing code
        Vehicle[] vehicles = {new Car(), new Bike(), new Truck(), new Bus()};
        for (Vehicle vehicle : vehicles) {
            vehicle.start(); // Polymorphic behavior handles the new type seamlessly
        }
    }
}

/*
Output :
Starting the car
Starting the bike
Starting the truck
Starting the bus
 */