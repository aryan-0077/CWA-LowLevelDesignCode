package FutureExamples.BasicExamples;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
    public static void main(String[] args) {
        // Create a single-threaded executor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Submit a task that returns a result in the future
        Future<String> future = executor.submit(() -> {
            Thread.sleep(2000); // Simulate a task that takes 2 seconds
            return "Task completed!";
        });

        try {
            System.out.println("Task submitted, doing other work...");

            // Check if the task is completed (non-blocking)
            System.out.println("Is task done? " + future.isDone());

            // Get the result - this blocks until the task is finished
            String result = future.get(); // Will block until computation is complete
            System.out.println("Result: " + result);

            // Re-check task status after completion
            System.out.println("Is task done? " + future.isDone());
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions that might occur during task execution
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Shut down the executor to release resources
            executor.shutdown();
        }
    }
}

/*

Output :
Task submitted, doing other work...
Is task done? false
Result: Task completed!
Is task done? true

*/