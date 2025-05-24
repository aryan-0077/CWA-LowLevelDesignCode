package Advantages.Polymorphism;

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Dog();
        myAnimal.sound(); // Dog barks

        myAnimal = new Cat();
        myAnimal.sound(); // Cat meows
    }
}

/*

Output :
Dog barks
Cat meows

*/
