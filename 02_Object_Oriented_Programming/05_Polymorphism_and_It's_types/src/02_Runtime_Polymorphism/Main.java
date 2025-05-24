package Runtime_Polymorphism;

import Runtime_Polymorphism.Concrete_Classes.Bike;
import Runtime_Polymorphism.Concrete_Classes.Car;
import Runtime_Polymorphism.Concrete_Classes.Truck;

public class Main {
    public static void main(String[] args) {
        Vehicle myVehicle;
        // Assign a Car object to the Vehicle reference
        myVehicle = new Car();
        myVehicle.start(); // Output: Starting a car

        // Assign a Bike object to the Vehicle reference
        myVehicle = new Bike();
        myVehicle.start(); // Output: Starting a bike

        // Assign a Truck object to the Vehicle reference
        myVehicle = new Truck();
        myVehicle.start(); // Output: Starting a truck
    }
}

/*
Output :
Starting a car
Starting a bike
Starting a truck
*/