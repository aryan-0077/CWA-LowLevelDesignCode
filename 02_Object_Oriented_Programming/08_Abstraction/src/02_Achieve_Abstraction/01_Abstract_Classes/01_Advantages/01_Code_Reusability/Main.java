package Achieve_Abstraction.Abstract_Classes.Advantages.Code_Reusability;

import Achieve_Abstraction.Abstract_Classes.*;

public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.eat(); // Outputs: Animal is eating...
        Cat myCat = new Cat();
        myCat.eat(); // Outputs: Animal is eating...
    }
}
