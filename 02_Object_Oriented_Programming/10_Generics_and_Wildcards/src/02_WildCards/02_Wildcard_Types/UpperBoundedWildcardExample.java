package WildCards.Wildcard_Types;

import java.util.List;

public class UpperBoundedWildcardExample {
    public static void printNumbers(List<? extends Number> list) {
        for (Number number : list) {
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3);
        printNumbers(intList);
        List<Double> doubleList = List.of(1.1, 2.2, 3.3);
        printNumbers(doubleList);
        List<String> stringList = List.of("a", "b", "c");
        // printNumbers(stringList);
        // Error: incompatible types: List<String> cannot be converted to List<?
        // extends Number>
    }
}