package Ugly_Code.Factorial_Example;

// Bad Code: Overcomplicated, lacks clarity, and violates KISS
public class FactorialCalculator {
    public static int factorial(int n) {
        if (n == 0)
            return 1; // Base case
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            int temp = 1; // Temporary variable to store intermediate results
            for (int j = 1; j <= i; j++) {
                temp *= j; // Multiplying repeatedly in nested loops
            }
            fact = temp; // Reassigning fact unnecessarily
        }
        return fact;
    }

    public static void main(String[] args) {
        int result = factorial(5);
        System.out.println("Factorial: " + result); // Output: Factorial: 120
    }
}

/*

Output :
Factorial: 120

*/