package Achieve_Abstraction.Interface;

// Specific implementation for Dog
public class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
    @Override
    public void sleep() {
        System.out.println("Sleeping...");
    }
}