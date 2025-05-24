package Interview_Questions.Question_5;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();
        dog.eat(); // Output: Eating...
        dog.sound(); // Output: Dog barks.
        cat.eat(); // Output: Eating...
        cat.sound(); // Output: Cat meows.
    }
}

/*
Output :
Eating...
Dog barks.
Eating...
Cat meows.

*/