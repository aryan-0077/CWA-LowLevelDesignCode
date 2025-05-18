package Ugly_Code;

import Ugly_Code.Concrete_Car_Brands.*;

public class CarFactory {
    public Vehicle createVehicle(String brand) {
        if (brand.equals("Honda")) {
            return new Honda();
        } else if (brand.equals("Toyota")) {
            return new Toyota();
        } else if (brand.equals("BMW")) {
            return new BMW();
        } else {
            throw new IllegalArgumentException("Unknown car brand");
        }
    }
}
