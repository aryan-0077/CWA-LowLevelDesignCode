package Good_Code.Concrete_Car_Brands;

import Good_Code.Interfaces.Vehicle;

public class Toyota implements Vehicle {
    public void start() {
        System.out.println("Toyota Car is starting");
    }
    public void stop() {
        System.out.println("Toyota Car is stopping");
    }
}