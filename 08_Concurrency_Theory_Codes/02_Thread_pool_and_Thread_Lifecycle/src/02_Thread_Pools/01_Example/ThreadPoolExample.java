package ThreadPools.Example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit 5 tasks to the thread pool
        for (int i = 1; i <= 5; i++) {
            executorService.submit(new WorkerThread(i));
        }

        // Shutdown the executor service
        executorService.shutdown();
    }
}

/*

Output :
pool-1-thread-1 is processing task: 1
pool-1-thread-3 is processing task: 3
pool-1-thread-2 is processing task: 2
pool-1-thread-2 finished task: 2
pool-1-thread-1 finished task: 1
pool-1-thread-3 finished task: 3
pool-1-thread-2 is processing task: 4
pool-1-thread-1 is processing task: 5
pool-1-thread-1 finished task: 5
pool-1-thread-2 finished task: 4

*/