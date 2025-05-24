package Achieve_Abstraction.Abstract_Classes.Disadvantages.Complexity.Bad_Design;

public abstract class Animal {
    abstract void makeSound();
    // Poorly thought-out abstraction: Adding unrelated behaviors
    abstract void fly(); // Not all animals can fly
    abstract void swim(); // Not all animals can swim
}
