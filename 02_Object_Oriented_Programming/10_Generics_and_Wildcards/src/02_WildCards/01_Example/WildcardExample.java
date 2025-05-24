package WildCards.Example;

import java.util.List;

public class WildcardExample {
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        List<String> stringList = List.of("Apple", "Banana", "Cherry");
        printList(stringList);
        List<Integer> intList = List.of(1, 2, 3);
        printList(intList);
    }
}
