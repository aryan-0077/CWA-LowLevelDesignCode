package Multilevel_Inheritance;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(); // Inherited from Animal
        dog.walk(); // Inherited from Mammal
        dog.bark();
    }
}

/*
Output :
This animal eats food.
This mammal walks.
The dog barks.
*/