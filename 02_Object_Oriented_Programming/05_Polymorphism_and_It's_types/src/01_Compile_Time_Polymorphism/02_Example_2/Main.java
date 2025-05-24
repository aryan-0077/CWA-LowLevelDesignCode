package Compile_Time_Polymorphism.Example_2;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();

        // Calls method with a string argument
        vehicle.start("Truck");

        // Calls overloaded method with an integer argument
        vehicle.start(101);
    }
}

/*

Output :
Starting a Truck
Starting a vehicle with ID: 101

*/