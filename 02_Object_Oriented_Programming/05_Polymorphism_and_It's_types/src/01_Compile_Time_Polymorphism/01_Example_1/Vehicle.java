package Compile_Time_Polymorphism.Example_1;

public class Vehicle {
    // Method to start a vehicle with basic information
    void start(String vehicleType) {
        System.out.println("Starting a " + vehicleType);
    }

    // Overloaded method to start a vehicle with extra information
    void start(String vehicleType, int speed) {
        System.out.println(
                "Starting a " + vehicleType + " with speed: " + speed + " km/h");
    }
}
