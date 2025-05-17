package InterviewQuestions.Question_2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolQueueExample {
    public static void main(String[] args) {
        // ThreadPoolExecutor with 2 core threads, max 4 threads, and queue size of 2
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.AbortPolicy() // Reject tasks if queue & max threads are full
        );

        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is processing task " + taskId);
                try {
                    Thread.sleep(2000); // Simulating task execution
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executor.shutdown();
    }
}

/*

Output :
EXAMPLE 1: shutdown()
Task 1 started
Task 2 started
nCalling shutdown()...
Is shutdown: true
Is terminated: false
Can submit new tasks? false
Task 1 completed
Task 2 completed
Task 3 started
Task 4 started
Task 4 completed
Task 3 completed
Task 5 started
Task 5 completed
All tasks completed: true
Is terminated now: true
nnEXAMPLE 2: shutdownNow()
Task 1 started
Task 2 started
nCalling shutdownNow()...
Is shutdown: true
Task 2 was interrupted!
Task 1 was interrupted!
Number of pending tasks that never started: 3
Is terminated now: true


*/
