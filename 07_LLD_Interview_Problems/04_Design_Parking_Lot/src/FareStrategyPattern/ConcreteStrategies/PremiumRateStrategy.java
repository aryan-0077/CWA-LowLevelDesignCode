package FareStrategyPattern.ConcreteStrategies;

import CommonEnum.DurationType;
import FareStrategyPattern.ParkingFeeStrategy;

public class PremiumRateStrategy implements ParkingFeeStrategy {
    @Override
    public double calculateFee(String vehicleType, int duration, DurationType durationType) {
        // Premium rates with higher multipliers
        switch (vehicleType.toLowerCase()) {
            case "car":
                return durationType == DurationType.HOURS
                        ? duration * 15.0   // $15 per hour for cars
                        : duration * 15.0 * 24;  // Daily rate

            case "bike":
                return durationType == DurationType.HOURS
                        ? duration * 8.0    // $8 per hour for bikes
                        : duration * 8.0 * 24;  // Daily rate

            case "auto":
                return durationType == DurationType.HOURS
                        ? duration * 12.0   // $12 per hour for autos
                        : duration * 12.0 * 24;  // Daily rate

            default:
                return durationType == DurationType.HOURS
                        ? duration * 20.0   // $20 per hour for other vehicles
                        : duration * 20.0 * 24;  // Daily rate
        }
    }
}
