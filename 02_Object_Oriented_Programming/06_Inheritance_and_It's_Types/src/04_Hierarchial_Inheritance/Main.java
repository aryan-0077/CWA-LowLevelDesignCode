package Hierarchial_Inheritance;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
        dog.bark();
        Cat cat = new Cat();
        cat.eat();
        cat.meow();
    }
}

/*
Output :
This animal eats food.
The dog barks.
This animal eats food.
The cat meows.
*/