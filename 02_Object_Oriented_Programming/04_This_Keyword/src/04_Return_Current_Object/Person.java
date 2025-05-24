package Return_Current_Object;

public class Person {
    private String name;
    Person setName(String name) {
        this.name = name;
        return this; // Enables method chaining
    }
    void display() {
        System.out.println("Name: " + this.name);
    }
}