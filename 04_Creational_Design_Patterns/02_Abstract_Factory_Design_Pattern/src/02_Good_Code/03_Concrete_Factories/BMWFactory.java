package Good_Code.Concrete_Factories;

import Good_Code.Concrete_Car_Brands.BMW;
import Good_Code.Interfaces.Vehicle;
import Good_Code.Interfaces.VehicleFactory;

public class BMWFactory implements VehicleFactory {
    public Vehicle createVehicle() {
        return new BMW();
    }
}