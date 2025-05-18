package Ugly_Code;

import Ugly_Code.ConcreteVehicles.Bike;
import Ugly_Code.ConcreteVehicles.Car;
import Ugly_Code.ConcreteVehicles.Truck;

public class Main {
    public static void main(String[] args) {
        String vehicleType = "Truck"; // Imagine this value is dynamic
        Vehicle vehicle;
        if (vehicleType.equals("Car")) {
            vehicle = new Car();
        } else if (vehicleType.equals("Truck")) {
            vehicle = new Truck();
        } else if (vehicleType.equals("Bike")) {
            vehicle = new Bike();
        } else {
            throw new IllegalArgumentException("Unknown vehicle type");
        }
        vehicle.start();
        vehicle.stop();
    }
}

/*

Output :
Truck is starting...
Truck is stopping...

*/