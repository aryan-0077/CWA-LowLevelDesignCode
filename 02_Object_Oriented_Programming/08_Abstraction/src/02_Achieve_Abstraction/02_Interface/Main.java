package Achieve_Abstraction.Interface;

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.makeSound(); // Outputs: Bark
        myDog.sleep(); // Outputs: Sleeping...
        Animal myCat = new Cat();
        myCat.makeSound(); // Outputs: Meow
        myCat.sleep(); // Outputs: Sleeping...
    }
}
