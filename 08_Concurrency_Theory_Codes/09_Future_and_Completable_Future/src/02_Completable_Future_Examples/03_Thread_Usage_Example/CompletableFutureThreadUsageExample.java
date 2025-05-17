package CompletableFutureExamples.ThreadUsageExample;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureThreadUsageExample {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                    System.out.println("supplyAsync running in: " + Thread.currentThread().getName());
                    return "Hello";
                })
                .thenApply(s -> {
                    // thenApply runs in the same thread as supplyAsync if available.
                    System.out.println("thenApply running in: " + Thread.currentThread().getName());
                    return s + " World";
                })
                .thenApplyAsync(s -> {
                    // thenApplyAsync uses a different thread from the default ForkJoinPool.
                    System.out.println("thenApplyAsync running in: " + Thread.currentThread().getName());
                    return s + "! CompletableFuture is awesome.";
                });

        System.out.println("Final result: " + future.join());
    }
}

/*

Output :
supplyAsync running in: ForkJoinPool.commonPool-worker-1
thenApply running in: ForkJoinPool.commonPool-worker-1
thenApplyAsync running in: ForkJoinPool.commonPool-worker-1
Final result: Hello World! CompletableFuture is awesome.

*/