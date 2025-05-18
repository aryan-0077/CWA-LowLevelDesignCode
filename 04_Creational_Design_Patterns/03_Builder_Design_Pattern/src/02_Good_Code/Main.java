package Good_Code;

public class Main {
    public static void main(String[] args) {
        // Creating a car using the Builder pattern
        Car.CarBuilder builder = new Car.CarBuilder();
        Car car1 = builder.setEngine("V8")
                .setColor("Red")
                .setSeats(5)
                .setSunroof(true)
                .build(); // The build method returns the final product
        System.out.println(car1);

        // Creating another car with different specifications
        Car car2 = builder.setEngine("V6")
                .setColor("Blue")
                .setSeats(4)
                .build(); // Sunroof and Navigation are default
        System.out.println(car2);
    }
}

/*

Output :
Car [engine=V8, wheels=4, seats=5, color=Red, sunroof=true, navigationSystem=false]
Car [engine=V6, wheels=4, seats=4, color=Blue, sunroof=true, navigationSystem=false]

*/