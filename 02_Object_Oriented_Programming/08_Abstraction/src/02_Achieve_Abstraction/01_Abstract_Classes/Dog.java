package Achieve_Abstraction.Abstract_Classes;

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }

    public void eat(){
        System.out.println("Dog is eating");
    }
}
