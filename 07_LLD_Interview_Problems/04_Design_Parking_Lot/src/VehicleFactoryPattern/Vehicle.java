package VehicleFactoryPattern;

import CommonEnum.DurationType;
import FareStrategyPattern.ParkingFeeStrategy;

public abstract class Vehicle {
    private String licensePlate; // Stores the vehicle's license plate number
    private String vehicleType; // Stores the type of vehicle (e.g., car, bike, truck)
    private ParkingFeeStrategy feeStrategy; // Strategy for calculating parking fees
    // Constructor to initialize a vehicle with its license plate, type, and fee strategy
    public Vehicle(String licensePlate, String vehicleType, ParkingFeeStrategy feeStrategy) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.feeStrategy = feeStrategy;
    }
    // Getter method to retrieve the vehicle type
    public String getVehicleType() {
        return vehicleType;
    }
    // Getter method to retrieve the vehicle's license plate number
    public String getLicensePlate() {
        return licensePlate;
    }
    // Method to calculate parking fee based on duration and duration type
    public double calculateFee(int duration, DurationType durationType) {
        return feeStrategy.calculateFee(vehicleType, duration, durationType);
    }
}
