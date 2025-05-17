package InterviewQuestions.Question_5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Question_5 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            throw new RuntimeException("Boom!");
        });
        try {
            future.get(); // Will throw ExecutionException wrapping the original
        } catch (ExecutionException e) {
            System.out.println("Caught: " + e.getCause()); // prints: Boom!
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

/*

Output :
Caught: java.lang.RuntimeException: Boom!

*/
