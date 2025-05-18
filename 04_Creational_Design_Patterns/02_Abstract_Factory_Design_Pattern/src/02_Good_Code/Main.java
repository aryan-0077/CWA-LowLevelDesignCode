package Good_Code;

import Good_Code.Concrete_Factories.HondaFactory;
import Good_Code.Concrete_Factories.ToyotaFactory;
import Good_Code.Interfaces.Vehicle;
import Good_Code.Interfaces.VehicleFactory;

public class Main {
    public static void main(String[] args) {
        VehicleFactory hondaFactory = new HondaFactory(); // can be dynamic
        Vehicle honda = hondaFactory.createVehicle();
        honda.start();
        honda.stop();
        VehicleFactory toyotaFactory = new ToyotaFactory();
        Vehicle toyota = toyotaFactory.createVehicle();
        toyota.start();
        toyota.stop();
    }
}

/*

Output :
Honda Car is starting
Honda Car is stopping
Toyota Car is starting
Toyota Car is stopping

*/