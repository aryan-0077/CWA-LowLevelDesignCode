package Interview_Questions.Question_2;

public abstract class Animal implements Pet{
    String name;
    Animal(String name) {
        this.name = name;
    }
    abstract void sound();
    @Override
    public void play() {
        System.out.println(name + " plays.");
    }
}
