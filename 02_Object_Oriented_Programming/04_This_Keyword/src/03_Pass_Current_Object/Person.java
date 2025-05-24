package Pass_Current_Object;

public class Person {
    void greet(Person person) {
        System.out.println("Hello, " + person);
    }
    void introduce() {
        greet(this); // Passes the current object
    }
    @Override
    public String toString() {
        return "I am a Person instance.";
    }
}