package Disadvantages.Performance_Overhead;

import Disadvantages.*;

public class Main {
    public static void main(String[] args) {
        Vehicle myVehicle;
        long startTime = System.nanoTime();
        // Dynamic method dispatch
        myVehicle = new Car();
        myVehicle.start(); // JVM resolves method implementation dynamically
        long endTime = System.nanoTime();
        System.out.println("Time taken for method dispatch: "
                + (endTime - startTime) + " nanoseconds");
    }
}

/*

Output :
Starting a car
Time taken for method dispatch: 348500 nanoseconds
*/