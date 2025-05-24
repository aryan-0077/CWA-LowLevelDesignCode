package Interview_Questions.Question_11;

public interface DogBehavior {
    default void makeSound() {
        System.out.println("Dog barks.");
    }
}

