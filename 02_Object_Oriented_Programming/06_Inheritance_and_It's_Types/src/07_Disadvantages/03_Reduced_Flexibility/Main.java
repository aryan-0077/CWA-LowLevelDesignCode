package Disadvantages.Reduced_Flexibility;


public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();

        dog.sleep(); // Animal is sleeping (not customized for Dog)
        cat.sleep(); // Animal is sleeping (not customized for Cat)
    }
}
