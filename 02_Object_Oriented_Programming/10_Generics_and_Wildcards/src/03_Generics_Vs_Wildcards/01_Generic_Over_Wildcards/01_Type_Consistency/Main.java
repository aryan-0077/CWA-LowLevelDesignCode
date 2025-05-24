package Generics_Vs_Wildcards.Generic_Over_Wildcards.Type_Consistency;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Example with a list of Strings
        List<String> stringList = new ArrayList<>();
        ListUtils.addElementsToList(stringList, "Apple", "Banana");
        System.out.println("Updated String List: " + stringList);

        // Example with a list of Integers
        List<Integer> intList = new ArrayList<>();
        ListUtils.addElementsToList(intList, 10, 20);
        System.out.println("Updated Integer List: " + intList);

        // Example with a list of Doubles
        List<Double> doubleList = new ArrayList<>();
        ListUtils.addElementsToList(doubleList, 1.5, 2.5);
        System.out.println("Updated Double List: " + doubleList);

        // Example with a list of custom objects
        List<Person> personList = new ArrayList<>();
        ListUtils.addElementsToList(personList, new Person("Alice"), new Person("Bob"));
        System.out.println("Updated Person List: " + personList);
    }
}

/*
Output :
Elements added: Apple, Banana
Updated String List: [Apple, Banana]
Elements added: 10, 20
Updated Integer List: [10, 20]
Elements added: 1.5, 2.5
Updated Double List: [1.5, 2.5]
Elements added: Alice, Bob
Updated Person List: [Alice, Bob]

*/