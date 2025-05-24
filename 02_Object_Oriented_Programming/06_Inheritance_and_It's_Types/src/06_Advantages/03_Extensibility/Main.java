package Advantages.Extensibility;

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Animal myDog = new Dog();
        Animal myCat = new Cat();
        myAnimal.sleep(); // Animal is sleeping
        myDog.sleep(); // Dog is sleeping in its kennel
        myCat.sleep(); // Cat sleeps in a tree
    }
}

/*
Output :
Animal is sleeping
Dog is sleeping in its kennel
Cat sleeps in a tree

*/