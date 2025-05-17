package InterviewQuestions.Question_1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Question_1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> System.out.println("Runnable executed"));
        Future<Integer> future = executor.submit(() -> 42);
        System.out.println("Callable result: " + future.get());
        executor.shutdown();
    }
}

/*

Output :
Runnable executed
Callable result: 42

*/