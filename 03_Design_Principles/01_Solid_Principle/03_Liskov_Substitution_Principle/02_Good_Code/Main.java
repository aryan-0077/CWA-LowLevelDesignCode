package Liskov_Substitution_Principle.Good_Code;

import Liskov_Substitution_Principle.Good_Code.Concrete_Vehicles.Bicycle;
import Liskov_Substitution_Principle.Good_Code.Concrete_Vehicles.Car;

public class Main {
    public static void main(String[] args) {
        // Using EngineVehicle
        EngineVehicle car = new Car();
        car.startEngine();  // Output: Car-specific engine starting logic
        car.move();         // Output: Movement logic


        // Using NonEngineVehicle
        NonEngineVehicle bicycle = new Bicycle();
        bicycle.move();  // Output: Movement logic
    }
}

/*

Output :
Non-Engine Vehicles do not have start engine method

 */