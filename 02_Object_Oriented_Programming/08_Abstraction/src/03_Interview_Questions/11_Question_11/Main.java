package Interview_Questions.Question_11;

public class Main {
    public static void main(String[] args) {
        AnimalProcessor processor = new AnimalProcessor();
        processor.makeSound();
    }
}

/*

Output :
Resolving conflict between DogBehavior and CatBehavior:
Dog barks.
Cat meows.
Custom behavior: AnimalProcessor decides which sound to make.

*/