public class Main {
    public static void main(String[] args) {

        // Creating objects
        Car car1 = new Car("Toyota", "Corolla", 2021);
        Car car2 = new Car("Honda", "Civic", 2022);

        // Using objects
        car1.startEngine();  // Output: The 2021 Toyota Corolla's engine has started.ĺ
        car2.startEngine();  // Output: The 2022 Honda Civic's engine has started.
        car1.displayInfo();  // Output: Car Info: Toyota Corolla (2021)
        car2.displayInfo();  // Output: Car Info: Honda Civic (2022)
    }
}

/*

Output :
The 2021 Toyota Corolla's engine has started.
The 2022 Honda Civic's engine has started.
Car Info: Toyota Corolla (2021)
Car Info: Honda Civic (2022)

*/