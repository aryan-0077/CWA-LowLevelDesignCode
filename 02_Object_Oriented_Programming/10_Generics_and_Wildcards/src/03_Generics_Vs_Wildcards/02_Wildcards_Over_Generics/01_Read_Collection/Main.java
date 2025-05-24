package Generics_Vs_Wildcards.Wildcards_Over_Generics.Read_Collection;

import java.util.List;

public class Main {
    // Generic method that requires a specific type
    public static <T> void printList1(List<T> list) {
        for (T item : list) {
            System.out.println(item); // Can read items of type T
        }
    }
    // Wildcard method that doesn't require a specific type
    public static void printList2(List<?> list) {
        for (Object item : list) {
            System.out.println(item); // Can read all items
        }
    }

    public static void main(String[] args) {
        List<String> strings = List.of("A", "B", "C");
        List<Integer> integers = List.of(1, 2, 3);
        List newList =
                List.of(1, 2, 3, 'a', 'b', 'c', "Code", "With", "Aryan", 1.23, 2.5);
        // Works because the generic type matches the list type
        printList1(strings); // T is inferred as String
        printList1(integers); // T is inferred as Integer
        // printList1(newList); // doesn't work because the type is not specified in
        // the newList
        printList2(strings); // works because wildcards do not care about exact types
        printList2(integers); // works because wildcards do not care about exact types
        printList2(newList); //  works because wildcards do not care about exact types
    }
}

/*

Output :
A
B
C
1
2
3
A
B
C
1
2
3
1
2
3
a
b
c
Code
With
Aryan
1.23
2.5

 */