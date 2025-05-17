package CompletableFutureExamples.CompletableFutureCreation;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureCreation {
    public static void main(String[] args) {
        // Create a completed future with a pre-defined result
        CompletableFuture<String> completed = CompletableFuture.completedFuture("Result");
        System.out.println("Completed future result: " + completed.join());

        // Create and run a task asynchronously
        CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // Simulate work
                return "Async result";
            } catch (InterruptedException e) {
                return "Interrupted";
            }
        });

        // Non-blocking check and then blocking get
        System.out.println("Is async done? " + async.isDone());
        System.out.println("Async result: " + async.join()); // Blocks until result is available
    }
}


/*

Output :
Completed future result: Result
Is async done? false
Async result: Async result

*/