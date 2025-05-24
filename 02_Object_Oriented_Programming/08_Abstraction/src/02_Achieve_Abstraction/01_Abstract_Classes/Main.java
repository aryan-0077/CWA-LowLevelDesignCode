package Achieve_Abstraction.Abstract_Classes;

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog(); // Treating Dog as an Animal
        myDog.makeSound(); // Outputs: Bark
        myDog.sleep(); // Outputs: Sleeping...
        Animal myCat = new Cat(); // Treating Cat as an Animal
        myCat.makeSound(); // Outputs: Meow
        myCat.sleep(); // Outputs: Sleeping...
    }
}
