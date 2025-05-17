package ThreadCreation.CallableInterface;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) {
        // Create ExecutorService with a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Create Callable instances
        Callable<String> callable1 = new MyCallable("Task 1");
        Callable<String> callable2 = new MyCallable("Task 2");

        try {
            // Submit Callable tasks to the executor and get Future objects
            Future<String> future1 = executor.submit(callable1);
            Future<String> future2 = executor.submit(callable2);

            // Get results from Future objects
            System.out.println("Result from first task:");
            System.out.println(future1.get()); // Blocks until the task completes

            System.out.println("Result from second task:");
            System.out.println(future2.get()); // Blocks until the task completes

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Task execution interrupted: " + e.getMessage());
        } finally {
            // Shutdown the executor
            executor.shutdown();
        }
    }
}


/*

Output :

Result from first task:
Callable Task 1 is running: 0
Callable Task 1 is running: 1
Callable Task 1 is running: 2
Callable Task 1 is running: 3
Callable Task 1 is running: 4


Result from second task:
Callable Task 2 is running: 0
Callable Task 2 is running: 1
Callable Task 2 is running: 2
Callable Task 2 is running: 3
Callable Task 2 is running: 4

*/