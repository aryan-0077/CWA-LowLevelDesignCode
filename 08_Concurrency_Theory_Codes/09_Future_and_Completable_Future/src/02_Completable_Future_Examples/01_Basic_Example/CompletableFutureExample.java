package CompletableFutureExamples.BasicExample;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
        // Start an asynchronous task using CompletableFuture.supplyAsync.
        CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000); // Simulate a delay
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "Result from CompletableFuture";
                })
                // Register a callback that processes the result once it's ready.
                .thenAccept(result -> {
                    System.out.println("CompletableFuture result: " + result);
                    // Additional operation after the result is available.
                    System.out.println("Processing after CompletableFuture result");
                });
        // Optionally do other work here while the asynchronous task is running.
        System.out.println("Main thread is free to do other tasks while waiting...");
        // To prevent the main thread from exiting immediately,
        // we'll wait for the CompletableFuture to complete.
        try {
            Thread.sleep(2000); // Wait enough time for the async task to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

/*

Main thread is free to do other tasks while waiting...
CompletableFuture result: Result from CompletableFuture
Processing after CompletableFuture result
*/