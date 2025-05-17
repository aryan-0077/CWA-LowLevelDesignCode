package CompletableFutureExamples.ExceptionHandling;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptionHandling {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                    if (Math.random() > 0.5) {
                        throw new RuntimeException("Something went wrong!");
                    }
                    return "Success";
                })
                // Handle exceptions
                .exceptionally(ex -> {
                    System.out.println("Exception caught: " + ex.getMessage());
                    return "Recovery value";
                });
        System.out.println("Result: " + future.join());
        // Handle both success and failure
        CompletableFuture<String> handled = CompletableFuture.supplyAsync(() -> {
                    if (Math.random() > 0.5) {
                        throw new RuntimeException("Error occurred");
                    }
                    return "Success path";
                })
                .handle((result, ex) -> {
                    if (ex != null) {
                        return "Handled error: " + ex.getMessage();
                    }
                    return "Handled success: " + result;
                });
        System.out.println(handled.join());
    }
}

/*

Output :
Exception caught: java.lang.RuntimeException: Something went wrong!
Result: Recovery value
Handled success: Success path

 */