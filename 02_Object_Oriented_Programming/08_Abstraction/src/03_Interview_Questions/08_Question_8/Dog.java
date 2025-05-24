package Interview_Questions.Question_8;

public class Dog extends Mammal implements Animal {
    @Override
    void eat() {
        System.out.println("Dog is eating.");
    }
}