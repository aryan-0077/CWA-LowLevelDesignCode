package Achieve_Abstraction.Abstract_Classes;

public abstract class Animal {
    // Abstract method for unique behaviors
    private String secret = "Sensitive data";
    public abstract void makeSound();
    // Concrete method for shared behaviors
    void sleep() {
        System.out.println("Sleeping...");
    }

    protected String getSecret() {
        return secret; // Controlled access to sensitive data
    }
}
