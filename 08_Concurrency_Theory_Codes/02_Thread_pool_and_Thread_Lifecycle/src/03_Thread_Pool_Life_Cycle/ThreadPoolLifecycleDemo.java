package ThreadPoolLifeCycle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolLifecycleDemo {
    public static void main(String[] args) {
        // Step 1: Create a Thread Pool with 3 core threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("Thread Pool Created 🏊‍♂️");
        // Step 2: Submit 5 tasks to the pool
        for (int i = 1; i <= 5; i++) {
            executor.execute(new Task(i)); // Threads pick tasks and move to RUNNABLE
        }
        // Step 3: Initiate shutdown after all tasks are submitted
        executor.shutdown();
        System.out.println("Thread Pool Shutdown Initiated 🚦");

        try {
            // Wait for all threads to terminate
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                System.out.println("Forcing Shutdown! 🚧");
                // If shutdownNow is called, threads currently in RUNNING state will be interrupted.
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("All Threads Terminated ✅");

// Once shutdown is complete, all threads that were processing tasks (RUNNING, BLOCKED, WAITING, TIMED_WAITING)
// will have completed their work or been interrupted and will eventually reach the TERMINATED state
    }
}

/*

Output :
Thread Pool Created 🏊‍♂️
Thread Pool Shutdown Initiated 🚦
pool-1-thread-2 - STARTING Task 2
pool-1-thread-3 - STARTING Task 3
pool-1-thread-1 - STARTING Task 1
pool-1-thread-2 - WAITING on Task 2
pool-1-thread-1 - WAITING on Task 1
pool-1-thread-3 - WAITING on Task 3
pool-1-thread-2 - Task 2 COMPLETED
pool-1-thread-3 - Task 3 COMPLETED
pool-1-thread-2 - STARTING Task 4
pool-1-thread-3 - STARTING Task 5
pool-1-thread-1 - Task 1 COMPLETED
pool-1-thread-3 - WAITING on Task 5
pool-1-thread-2 - WAITING on Task 4
pool-1-thread-3 - Task 5 COMPLETED
pool-1-thread-2 - Task 4 COMPLETED
All Threads Terminated ✅

*/