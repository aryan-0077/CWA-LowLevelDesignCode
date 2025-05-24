package Compile_Time_Polymorphism.Example_1;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();

        // Calls method with one argument
        vehicle.start("Car");

        // Calls overloaded method with two arguments
        vehicle.start("Bike", 60);
    }
}

/* Output :
Starting a Car
Starting a Bike with speed: 60 km/h
*/