package Runtime_Polymorphism.Concrete_Classes;

import Runtime_Polymorphism.Vehicle;

public class Bike extends Vehicle{
    @Override
    public void start() {
        System.out.println("Starting a bike");
    }
}
