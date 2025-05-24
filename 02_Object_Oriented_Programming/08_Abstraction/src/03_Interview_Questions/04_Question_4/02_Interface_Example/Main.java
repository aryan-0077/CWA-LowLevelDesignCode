package Interview_Questions.Question_4.Interface_Example;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound(); // Output: Dog barks.
        dog.play(); // Output: Dog plays fetch.
        dog.commonMethod(); // Output: Animal common method.
        //         Pet common method.
        //         Dog's own common method.
    }
}

/*

Output :
Dog barks.
Dog plays fetch.
Animal common method.
Pet common method.
Dog's own common method.

*/