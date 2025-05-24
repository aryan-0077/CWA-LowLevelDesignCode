package Generics.Examples.Generic_Method;

public class Test {
    // A Generic method example
    static <T> void genericDisplay(T element) {
        System.out.println(element.getClass().getName() + " = " + element);
    }

    // Driver method
    public static void main(String[] args) {
        // Calling generic method with Integer argument
        genericDisplay(11);

        // Calling generic method with String argument
        genericDisplay("CodeWithAryan");

        // Calling generic method with double argument
        genericDisplay(1.0);
    }
}

/*
Output :
java.lang.Integer = 11
java.lang.String = CodeWithAryan
java.lang.Double = 1.0

*/