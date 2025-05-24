package Interview_Questions.Question_11;

public class AnimalProcessor implements DogBehavior, CatBehavior {
    // Resolving the conflict by overriding the method
    @Override
    public void makeSound() {
        System.out.println(
                "Resolving conflict between DogBehavior and CatBehavior:");

        // Call the default method from DogBehavior
        DogBehavior.super.makeSound();

        // Call the default method from CatBehavior
        CatBehavior.super.makeSound();

        // Adding custom behavior
        System.out.println(
                "Custom behavior: AnimalProcessor decides which sound to make.");
    }
}