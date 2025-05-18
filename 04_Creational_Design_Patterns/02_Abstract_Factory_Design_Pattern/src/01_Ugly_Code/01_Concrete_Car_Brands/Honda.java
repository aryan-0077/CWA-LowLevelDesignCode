package Ugly_Code.Concrete_Car_Brands;

import Ugly_Code.Vehicle;

// Concrete Classes for Car Brands
public class Honda implements Vehicle {
    public void start() {
        System.out.println("Honda Car is starting");
    }
    public void stop() {
        System.out.println("Honda Car is stopping");
    }
}