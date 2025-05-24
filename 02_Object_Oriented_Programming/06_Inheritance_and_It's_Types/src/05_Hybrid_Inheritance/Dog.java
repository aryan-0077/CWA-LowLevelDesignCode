package Hybrid_Inheritance;

import Hybrid_Inheritance.Interfaces.*;

public // Hybrid inheritance using a combination of class and interfaces
class Dog extends Animal implements Mammal, Pet {
    @Override
    void eat() {
        System.out.println("The dog eats food.");
    }
    @Override
    public void walk() {
        System.out.println("The dog walks.");
    }
    @Override
    public void play() {
        System.out.println("The dog plays fetch.");
    }
}