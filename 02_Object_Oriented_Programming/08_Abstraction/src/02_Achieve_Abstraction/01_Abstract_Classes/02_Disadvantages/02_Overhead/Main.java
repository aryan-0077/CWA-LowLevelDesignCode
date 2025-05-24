package Achieve_Abstraction.Abstract_Classes.Disadvantages.Overhead;

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.makeSound(); // Outputs: Bark
        Animal cat = new Cat();
        cat.makeSound(); // Outputs: Meow
    }
}
