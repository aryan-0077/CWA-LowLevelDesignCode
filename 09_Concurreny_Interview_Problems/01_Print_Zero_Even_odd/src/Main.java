import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) {
        // Set the limit for the sequence
        int n = 10;

        // Create the ZeroEvenOdd object
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);

        // Create a thread pool for zero, even, and odd printing
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Runnable to print numbers with thread names
        IntConsumer printNumber = number ->
                System.out.println(Thread.currentThread().getName() + " printed: " + number);

        // Submit the zero, even, and odd printing tasks
        executor.submit(() -> {
            Thread.currentThread().setName("Zero-Thread");
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.submit(() -> {
            Thread.currentThread().setName("Even-Thread");
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.submit(() -> {
            Thread.currentThread().setName("Odd-Thread");
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Shutdown the executor service after completion
        executor.shutdown();
    }
}

/*

Output :

Zero-Thread printed: 0
Odd-Thread printed: 1
Zero-Thread printed: 0
Even-Thread printed: 2
Zero-Thread printed: 0
Odd-Thread printed: 3
Zero-Thread printed: 0
Even-Thread printed: 4
Zero-Thread printed: 0
Odd-Thread printed: 5
Zero-Thread printed: 0
Even-Thread printed: 6
Zero-Thread printed: 0
Odd-Thread printed: 7
Zero-Thread printed: 0
Even-Thread printed: 8
Zero-Thread printed: 0
Odd-Thread printed: 9
Zero-Thread printed: 0
Even-Thread printed: 10

*/