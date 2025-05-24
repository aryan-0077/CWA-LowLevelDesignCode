package Advantages.Flexibility;

import Advantages.*;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle;
        // Flexible: Dynamically assign different types of vehicles
        vehicle = new Car();
        vehicle.start(); // Output: Starting the car
        vehicle = new Bike();
        vehicle.start(); // Output: Starting the bike
        vehicle = new Truck();
        vehicle.start(); // Output: Starting the truck
    }
}
