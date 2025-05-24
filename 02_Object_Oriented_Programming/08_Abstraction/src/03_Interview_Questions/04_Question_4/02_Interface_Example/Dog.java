package Interview_Questions.Question_4.Interface_Example;

public class Dog implements Animal, Pet {
    @Override
    public void sound() {
        System.out.println("Dog barks.");
    }
    @Override
    public void play() {
        System.out.println("Dog plays fetch.");
    }
    @Override
    public void commonMethod() {
        Animal.super.commonMethod();
        Pet.super.commonMethod();
        System.out.println("Dog's own common method.");
    }
}