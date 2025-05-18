package Good_Code.Factorial_Example;

// KISS-Compliant Code: Simple, clear, and efficient
public class FactorialCalculator {
    public static int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i; // Directly calculating factorial in one loop
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