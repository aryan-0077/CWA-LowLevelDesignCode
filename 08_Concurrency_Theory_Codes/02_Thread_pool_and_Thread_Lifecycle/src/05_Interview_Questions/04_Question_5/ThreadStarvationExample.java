package InterviewQuestions.Question_5;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadStarvationExample {

    // Counter to track task completion by priority
    private static AtomicInteger[] completedTasks = new AtomicInteger[3];

    // Initialize counters
    static {
        for (int i = 0; i < completedTasks.length; i++) {
            completedTasks[i] = new AtomicInteger(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Example 1: Without Thread Pool (Potential Starvation) --");
        withoutThreadPool();

        // Reset counters
        for (AtomicInteger counter : completedTasks) {
            counter.set(0);
        }

        System.out.println("n--- Example 2: With Thread Pool (Fair Scheduling) ---");
        withThreadPool();
    }

    /**
     - Example 1: Without Thread Pool
     - In this approach, we directly create many threads with different priorities.
     - High-priority threads can potentially monopolize CPU, causing starvation.
     */
    private static void withoutThreadPool() throws InterruptedException {
        // Create a resource that will be shared across threads
        final Object sharedResource = new Object();

        // Create and start a large number of threads with different priorities
        for (int i = 0; i < 30; i++) {
            Thread thread = new Thread(new PriorityTask(i % 3, sharedResource));

            // Set thread priority (highest priority will likely monopolize CPU)
            thread.setPriority(Thread.MIN_PRIORITY + (i % 3) * 2);
            thread.start();
        }

        // Wait to see results
        Thread.sleep(5000);

        // Display results
        System.out.println("Tasks completed by priority:");
        System.out.println("Low priority: " + completedTasks[0].get());
        System.out.println("Medium priority: " + completedTasks[1].get());
        System.out.println("High priority: " + completedTasks[2].get());
    }

    /**
     - Example 2: With Thread Pool
     - Thread pools help prevent starvation by:
     - 1. Limiting the total number of active threads
     - 2. Using queue-based scheduling which can be fair
     - 3. Allowing task execution order to be controlled
     */
    private static void withThreadPool() throws InterruptedException {
        // Create a resource that will be shared across threads
        final Object sharedResource = new Object();

        // Create thread pool with FIFO scheduling using a fair lock
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                4,                          // Core pool size
                4,                          // Maximum pool size
                0, TimeUnit.MILLISECONDS,   // Keep-alive time
                new LinkedBlockingQueue<>(),// Work queue (FIFO)
                new ThreadPoolExecutor.CallerRunsPolicy()  // Rejection policy
        );

        // Set fair scheduling (helps prevent starvation)
        executor.setThreadFactory(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        // Submit tasks (same number as previous example)
        for (int i = 0; i < 30; i++) {
            executor.submit(new PriorityTask(i % 3, sharedResource));
        }

        // Wait to see results
        Thread.sleep(5000);

        // Display results
        System.out.println("Tasks completed by priority:");
        System.out.println("Low priority: " + completedTasks[0].get());
        System.out.println("Medium priority: " + completedTasks[1].get());
        System.out.println("High priority: " + completedTasks[2].get());

        // Shutdown executor
        executor.shutdown();
    }

    public static class PriorityTask implements Runnable{
        private final int priority; // 0=Low, 1=Medium, 2=High
        private final Object sharedResource;

        public PriorityTask(int priority, Object sharedResource) {
            this.priority = priority;
            this.sharedResource = sharedResource;
        }

        @Override
        public void run() {
            try {
                // Run in a loop to simulate ongoing work
                for (int i = 0; i < 10; i++) {
                    // Simulate accessing a shared resource
                    synchronized (sharedResource) {
                        // Higher priority tasks do more work with the resource
                        // This can lead to starvation without proper scheduling
                        Thread.sleep(20 + (10 * priority));

                        // Increment counter for this priority level
                        completedTasks[priority].incrementAndGet();
                    }

                    // Simulate some computation outside critical section
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

/*

Output :
--- Example 1: Without Thread Pool (Potential Starvation) --
Tasks completed by priority:
Low priority: 43
Medium priority: 42
High priority: 46
n--- Example 2: With Thread Pool (Fair Scheduling) ---
Tasks completed by priority:
Low priority: 92
Medium priority: 87
High priority: 90

*/
