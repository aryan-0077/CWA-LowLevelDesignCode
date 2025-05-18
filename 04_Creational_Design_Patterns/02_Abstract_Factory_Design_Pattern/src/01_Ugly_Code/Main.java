package Ugly_Code;

public class Main {
    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        Vehicle vehicle = factory.createVehicle("Honda");
        vehicle.start();
        vehicle.stop();
    }
}

/*

Output :
Honda Car is starting
Honda Car is stopping

*/