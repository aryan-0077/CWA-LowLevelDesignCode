package Achieve_Abstraction.Abstract_Classes.Disadvantages.Complexity.Bad_Design;

public class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Bark");
    }
    @Override
    void fly() {
        throw new UnsupportedOperationException("Dogs can't fly");
    }
    @Override
    void swim() {
        System.out.println("Dog is swimming");
    }
}
