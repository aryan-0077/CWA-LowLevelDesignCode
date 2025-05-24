package Achieve_Abstraction.Abstract_Classes.Disadvantages.Complexity.Good_Design;

public class Dog extends Animal implements Swimmable {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }

    @Override
    public void swim() {
        System.out.println("Dog is swimming");
    }
}
