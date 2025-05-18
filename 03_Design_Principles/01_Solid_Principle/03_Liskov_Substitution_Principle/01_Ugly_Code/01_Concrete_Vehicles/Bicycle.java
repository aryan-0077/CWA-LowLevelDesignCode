package Liskov_Substitution_Principle.Ugly_Code.Concrete_Vehicles;

import Liskov_Substitution_Principle.Ugly_Code.Vehicle;

public class Bicycle extends Vehicle {
    @Override
    public void startEngine() {
        // Car-specific engine starting logic
        // Problem: Bicycles don't have engines!
        throw new UnsupportedOperationException("Bicycles don't have engines");
    }
}
