package Compile_Time_Polymorphism.Example_2;

public class Vehicle {
    // Method to start a vehicle with a string parameter
    void start(String vehicleType) {
        System.out.println("Starting a " + vehicleType);
    }

    // Overloaded method to start a vehicle with an integer parameter
    void start(int vehicleId) {
        System.out.println("Starting a vehicle with ID: " + vehicleId);
    }
}
