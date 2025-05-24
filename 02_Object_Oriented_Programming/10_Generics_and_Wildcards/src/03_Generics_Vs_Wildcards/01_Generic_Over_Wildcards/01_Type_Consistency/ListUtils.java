package Generics_Vs_Wildcards.Generic_Over_Wildcards.Type_Consistency;

import java.util.List;

// Utility class for list operations
public class ListUtils {
    // Generic method to add two elements of the same type to a list
    public static <T> void addElementsToList(List<T> list, T firstElement, T secondElement) {
        list.add(firstElement);
        list.add(secondElement);
        System.out.println(
                "Elements added: " + firstElement + ", " + secondElement);
    }
}