package Interview_Questions.Question_14;

// Dog class extends abstract class Animal and implements interface Playable
public class Dog extends Animal implements Playable {
    // Constructor calling the abstract class constructor
    Dog(String name) {
        super(name);
    }

    // Overriding the abstract method
    @Override
    void sound() {
        System.out.println(name + " barks.");
    }

    // Uncommenting the following code will cause an error because default methods
    // cannot use super to refer to parent methods

    @Override
    public void play() {
        // super.play(); Error: Cannot use super to refer to a parent method in an interface
    }
}