package WildCards.Wildcard_Types;

import java.util.ArrayList;
import java.util.List;

public class LowerBoundedWildcardExample {
    public static void addNumbers(List<? super Integer> list) {
        list.add(42);
    }
    public static void main(String[] args) {
        List<Number> numberList = new ArrayList<>();
        addNumbers(numberList);
        System.out.println(numberList);
    }
}