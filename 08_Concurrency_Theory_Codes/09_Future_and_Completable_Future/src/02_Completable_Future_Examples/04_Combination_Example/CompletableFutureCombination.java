package CompletableFutureExamples.CombinationExample;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureCombination {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "Future 1";
            } catch (InterruptedException e) {
                return "Interrupted";
            }
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                return "Future 2";
            } catch (InterruptedException e) {
                return "Interrupted";
            }
        });

        // Combine results of two futures
        CompletableFuture<String> combined = future1.thenCombine(future2,
                (result1, result2) -> result1 + " + " + result2);
        System.out.println("Combined result: " + combined.join());

        // Wait for all futures to complete
        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);
        allOf.thenRun(() -> System.out.println("Both futures completed!"));

        // Wait for any one future to complete
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2);
        System.out.println("First completed: " + anyOf.join());
    }
}

/*

Output :
Combined result: Future 1 + Future 2
Both futures completed!
First completed: Future 1


 */