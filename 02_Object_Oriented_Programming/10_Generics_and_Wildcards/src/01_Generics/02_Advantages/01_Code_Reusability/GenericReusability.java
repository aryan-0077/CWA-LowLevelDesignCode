package Generics.Advantages.Code_Reusability;

public class GenericReusability {
    // A generic method to print elements of any type
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4};
        String[] stringArray = {"Apple", "Banana", "Cherry"};
        printArray(intArray); // Works with Integer
        printArray(stringArray); // Works with String
    }
}