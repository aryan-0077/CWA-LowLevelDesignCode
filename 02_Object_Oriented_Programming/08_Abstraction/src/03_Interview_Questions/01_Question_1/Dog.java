package Interview_Questions.Question_1;

public class Dog extends Animal implements Pet{
    Dog(String name) {
        super(name);
    }
    @Override
    void sound() {
        System.out.println(name + " barks.");
    }
    @Override
    public void play() {
        System.out.println(name + " plays fetch.");
    }
}
