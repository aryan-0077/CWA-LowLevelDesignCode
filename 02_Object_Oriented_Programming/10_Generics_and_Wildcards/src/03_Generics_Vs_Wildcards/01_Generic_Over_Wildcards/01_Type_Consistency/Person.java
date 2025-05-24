package Generics_Vs_Wildcards.Generic_Over_Wildcards.Type_Consistency;

public class Person {
    private String name;
    public Person(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}