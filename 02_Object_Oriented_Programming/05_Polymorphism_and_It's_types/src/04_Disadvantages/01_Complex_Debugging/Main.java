package Disadvantages.Complex_Debugging;

import Disadvantages.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // List containing various types of vehicles
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Car());
        vehicleList.add(new Bike());
        vehicleList.add(new Truck());
        vehicleList.add(new Vehicle());
        // Debugging challenge: What type of vehicle is being started?
        for (Vehicle vehicle : vehicleList) {
            vehicle.start(); // Runtime determines which start() method is called
        }
    }
}

/*
Output :
Starting a car
Starting a bike
Starting a truck
Starting a generic vehicle
*/