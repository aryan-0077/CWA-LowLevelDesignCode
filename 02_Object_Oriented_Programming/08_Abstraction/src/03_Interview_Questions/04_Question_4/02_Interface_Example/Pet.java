package Interview_Questions.Question_4.Interface_Example;

public interface Pet {
    void play();
    default void commonMethod() {
        System.out.println("Pet common method.");
    }
}
