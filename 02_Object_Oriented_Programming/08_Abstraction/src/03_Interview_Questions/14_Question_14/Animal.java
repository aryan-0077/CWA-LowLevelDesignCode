package Interview_Questions.Question_14;

public abstract class Animal {
    String name; // Instance variable
    // Constructor to initialize the name
    Animal(String name) {
        this.name = name;
    }
    // Abstract method
    abstract void sound();
    // Non-abstract method to demonstrate additional functionality
    void eat() {
        System.out.println(name + " is eating.");
    }
}
