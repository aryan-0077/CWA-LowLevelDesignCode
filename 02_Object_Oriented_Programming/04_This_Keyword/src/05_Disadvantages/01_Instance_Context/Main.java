package Disadvantages.Instance_Context;


public class Main {
    public static void main(String[] args) {
        // Calling static method
        Example.displayMessage(); // This would cause a compilation error
        // Creating an instance to call an instance method
        Example example = new Example();
        example.displayInstanceMessage(); // Works fine
    }
}
