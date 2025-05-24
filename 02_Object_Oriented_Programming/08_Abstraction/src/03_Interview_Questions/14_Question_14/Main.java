package Interview_Questions.Question_14;

public class Main {
    public static void main(String[] args) {
        // Abstract class functionality
        Dog dog = new Dog("Buddy");
        dog.sound(); // Output: Buddy barks.
        dog.eat(); // Output: Buddy is eating.

        // Interface functionality
        dog.play(); // Output: Playing with the animal.
    }
}
