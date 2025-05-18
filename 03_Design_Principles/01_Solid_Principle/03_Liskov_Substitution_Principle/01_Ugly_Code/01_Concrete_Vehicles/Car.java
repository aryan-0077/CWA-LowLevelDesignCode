package Liskov_Substitution_Principle.Ugly_Code.Concrete_Vehicles;

import Liskov_Substitution_Principle.Ugly_Code.Vehicle;

public class Car extends Vehicle {
    @Override
    public void startEngine() {
        // Car-specific engine starting logic
    }
}
