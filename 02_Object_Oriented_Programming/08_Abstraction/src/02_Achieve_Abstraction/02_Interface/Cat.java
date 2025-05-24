package Achieve_Abstraction.Interface;

public // Specific implementation for Cat
class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
    @Override
    public void sleep() {
        System.out.println("Sleeping...");
    }
}
