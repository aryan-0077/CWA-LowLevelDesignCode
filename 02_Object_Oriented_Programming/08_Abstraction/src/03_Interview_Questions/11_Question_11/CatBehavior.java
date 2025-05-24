package Interview_Questions.Question_11;

public interface CatBehavior {
    default void makeSound() {
        System.out.println("Cat meows.");
    }
}

