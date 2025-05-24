package Interview_Questions.Question_8;

public interface Animal {
    default void sound() {
        System.out.println("This is a default animal sound.");
    }
}