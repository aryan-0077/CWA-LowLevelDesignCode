package Generics_Vs_Wildcards.Generic_Over_Wildcards.Specific_Return_Type;

import Generics_Vs_Wildcards.Generic_Over_Wildcards.Type_Consistency.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static <T> T getFirstElement(List<T> list) {
        return list.size() == 0 ? null : list.get(0); // Compiler knows the type of T
    }

    public static void main(String[] args) {
        // Example with a list of Strings
        List<String> stringList = new ArrayList<>();
        ListUtils.addElementsToList(stringList, "Apple", "Banana");
        System.out.println("Updated String List: " + stringList);
        String firstString = getFirstElement(stringList);

        // Example with a list of Integers
        List<Integer> intList = new ArrayList<>();
        ListUtils.addElementsToList(intList, 10, 20);
        System.out.println("Updated Integer List: " + intList);
        Integer firstInteger = getFirstElement(intList);
    }
}

// Output :
/*
Elements added: Apple, Banana
Updated String List: [Apple, Banana]
Elements added: 10, 20
Updated Integer List: [10, 20]

*/