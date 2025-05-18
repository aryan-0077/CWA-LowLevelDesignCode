package Liskov_Substitution_Principle.Good_Code.Concrete_Vehicles;

import Liskov_Substitution_Principle.Good_Code.EngineVehicle;

public class Car extends EngineVehicle {
    @Override
    public void startEngine() {
        // Car-specific engine starting logic
    }
}
