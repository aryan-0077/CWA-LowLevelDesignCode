package FutureExamples.ResultRetrievalExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureResultExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> 10 + 20);
        Integer result = future.get();  // blocks until result is ready
        System.out.println("Result: " + result);  // Result: 30
        executor.shutdown();
    }
}

/*

Output :
Result: 30

 */
