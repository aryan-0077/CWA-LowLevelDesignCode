package Interview_Questions.Question_7;

public interface Animal {
    void eat(); // Abstract method
    default void sound() {
        System.out.println("This is a default animal sound.");
    }
}