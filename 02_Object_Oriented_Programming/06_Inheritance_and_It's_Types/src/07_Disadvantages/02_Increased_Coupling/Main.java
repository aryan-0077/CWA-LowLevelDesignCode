package Disadvantages.Increased_Coupling;

import Disadvantages.Dog;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(); // Reuses the eat method from Animal
    }
}
