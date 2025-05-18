package Good_Code.Concrete_Car_Brands;

import Good_Code.Interfaces.Vehicle;

public class BMW implements Vehicle {
    public void start() {
        System.out.println("BMW Car is starting");
    }
    public void stop() {
        System.out.println("BMW Car is stopping");
    }
}
