package Advantages.Code_Reusability;

import Advantages.Bike;
import Advantages.Car;
import Advantages.Truck;
import Advantages.Vehicle;

public class Main {
    public static void main(String[] args) {
        Vehicle[] vehicles = {new Car(), new Bike(), new Truck()};
        for (Vehicle vehicle : vehicles) {
            vehicle.start(); // Polymorphic behavior
        }
    }
}

/*
Output :
Starting the car
Starting the bike
Starting the truck
 */