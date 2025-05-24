package Inheritance;

public class InheritanceDemo {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(); // Inherited behavior
        dog.bark(); // Specific behavior
    }
}

/*
Output :
Animal is eating.
Dog barks: Woof Woof!
*/