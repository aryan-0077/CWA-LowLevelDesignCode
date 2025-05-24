package Interview_Questions.Question_4.Interface_Example;

public interface Animal {
    void sound();
    default void commonMethod() {
        System.out.println("Animal common method.");
    }
}