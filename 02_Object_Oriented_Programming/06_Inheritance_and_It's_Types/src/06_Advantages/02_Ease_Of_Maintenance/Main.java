package Advantages.Ease_Of_Maintenance;

import Advantages.*;

public class Main {
    public static void main(String[] args) {
        // If we need to fix a bug in eat()
        // or improve it, we only need to do it in Animal
        Animal animal = new Dog();
        animal.eat(); // Animal is eating
        animal = new Cat();
        animal.eat(); // Animal is eating
    }
}

/*
Output :
Animal is eating
Animal is eating

*/