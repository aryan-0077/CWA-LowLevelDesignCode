package Advantages.Code_Reusability;

import Advantages.Dog;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(); // Reuses the eat method from Animal
    }
}

/*
Output :
Animal is eating
*/