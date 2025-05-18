package Good_Code.Concrete_Car_Brands;

import Good_Code.Interfaces.Vehicle;

// Concrete Classes for Car Brands
public class Honda implements Vehicle {
    public void start() {
        System.out.println("Honda Car is starting");
    }
    public void stop() {
        System.out.println("Honda Car is stopping");
    }
}