package Disadvantages.Instance_Context;

class Example {
    private String message = "Hello, World!";
    // Static method
    public static void displayMessage() {
        // Attempting to use 'this' in a static context will cause a compilation
        // error
        System.out.println(this.message); // ERROR: Cannot use 'this' in a static context
    }
    public void displayInstanceMessage() {
        System.out.println(this.message); // Valid: 'this' refers to the current instance
    }
}
