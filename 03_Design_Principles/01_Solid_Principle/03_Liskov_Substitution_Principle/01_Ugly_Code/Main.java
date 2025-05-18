package Liskov_Substitution_Principle.Ugly_Code;

import Liskov_Substitution_Principle.Ugly_Code.Concrete_Vehicles.Bicycle;
import Liskov_Substitution_Principle.Ugly_Code.Concrete_Vehicles.Car;

public class Main {
    public static void main(String[] args) {
        // Creating objects of different subclasses
        Vehicle car = new Car();
        Vehicle bicycle = new Bicycle();
        // Using polymorphism
        System.out.println("Car:");
        car.startEngine();  // Output: Car engine started.
        System.out.println("nBicycle:");
        try {
            bicycle.startEngine();  // Throws UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*

Output :
Car:
nBicycle:
Error: Bicycles don't have engines

*/