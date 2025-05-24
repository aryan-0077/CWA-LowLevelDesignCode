package Achieve_Abstraction.Abstract_Classes;

public // Specific implementation for Cat
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }

    public void eat(){
        System.out.println("Dog is eating");
    }
}
