package Reffering_Instance_Object;

public class Person {
    private String name;

    // Constructor
    Person(String name) {
        this.name =
                name; // Resolves conflict between instance variable and parameter
    }
    void display() {
        System.out.println("Name: " + this.name);
    }
}