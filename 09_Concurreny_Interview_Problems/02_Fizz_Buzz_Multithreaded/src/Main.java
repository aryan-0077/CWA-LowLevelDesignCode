import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
            int n = 20; // You can change this to test with different ranges
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        // Create a fixed thread pool with 4 threads for each task
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Create and run the fizz thread
        executorService.submit(() -> {
            try {
                fizzBuzz.fizz(() -> {
                    System.out.println("Fizz (" + Thread.currentThread().getName() + ") ");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Create and run the buzz thread
        executorService.submit(() -> {
            try {
                fizzBuzz.buzz(() -> {
                    System.out.println("Buzz (" + Thread.currentThread().getName() + ") ");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Create and run the fizzbuzz thread
        executorService.submit(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> {
                    System.out.println("FizzBuzz (" + Thread.currentThread().getName() + ") ");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Create and run the number thread
        executorService.submit(() -> {
            try {
                fizzBuzz.number(num -> {
                    System.out.println(num + " (" + Thread.currentThread().getName() + ") ");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Shutdown the executor service after the tasks are completed
        executorService.shutdown();

        }
    }

    /*

    Output :
    1 (pool-1-thread-4)
2 (pool-1-thread-4)
Fizz (pool-1-thread-1)
4 (pool-1-thread-4)
Buzz (pool-1-thread-2)
Fizz (pool-1-thread-1)
7 (pool-1-thread-4)
8 (pool-1-thread-4)
Fizz (pool-1-thread-1)
Buzz (pool-1-thread-2)
11 (pool-1-thread-4)
Fizz (pool-1-thread-1)
13 (pool-1-thread-4)
14 (pool-1-thread-4)
FizzBuzz (pool-1-thread-3)
16 (pool-1-thread-4)
17 (pool-1-thread-4)
Fizz (pool-1-thread-1)
19 (pool-1-thread-4)
Buzz (pool-1-thread-2)

     */