package VehicleFactoryPattern;

import CommonEnums.VehicleEnums.VehicleType;
import VehicleFactoryPattern.ConcreteVehicles.EconomyVehicle;
import VehicleFactoryPattern.ConcreteVehicles.LuxuryVehicle;
import VehicleFactoryPattern.ConcreteVehicles.SUVVehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType vehicleType, String registrationNumber, String model, double baseRentalPrice) {
        switch (vehicleType) {
            case ECONOMY:
                return new EconomyVehicle(registrationNumber, model, vehicleType,  baseRentalPrice);
            case LUXURY:
                return new LuxuryVehicle(registrationNumber, model, vehicleType, baseRentalPrice);
            case SUV:
                return new SUVVehicle(registrationNumber, model,vehicleType,  baseRentalPrice);
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        }
    }
}
