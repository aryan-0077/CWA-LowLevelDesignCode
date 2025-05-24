package Advantages.Extensibility;

import Advantages.Vehicle;

public class Bus implements Vehicle {
    @Override
    public void start() {
        System.out.println("Starting the bus");
    }
}