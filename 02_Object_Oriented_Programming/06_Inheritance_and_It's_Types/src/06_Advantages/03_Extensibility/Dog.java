package Advantages.Extensibility;

public class Dog extends Animal {
    @Override
    public void sleep() {
        System.out.println("Dog is sleeping in its kennel");
    }
}
