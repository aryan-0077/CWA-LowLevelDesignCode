package VehicleFactoryPattern.ConcreteVehicles;

import FareStrategyPattern.ParkingFeeStrategy;
import VehicleFactoryPattern.Vehicle;

public class BikeVehicle extends Vehicle {
    public BikeVehicle(String licensePlate, String vehicleType, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, vehicleType, feeStrategy);
    }
}
