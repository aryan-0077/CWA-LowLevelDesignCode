package Types_Of_Locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExecutorExample {
    private int counter = 0;

    // Create a ReentrantLock instance.
    private final ReentrantLock lock = new ReentrantLock();

    // Method to increment the counter using the lock.
    public void increment() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented counter to: " + counter);
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock.");
            lock.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        ReentrantLockExecutorExample example = new ReentrantLockExecutorExample();
        // Create an ExecutorService with a fixed thread pool of 5 threads.
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // Submit 5 tasks to the executor service.
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> { example.increment(); });
        }
        // Shutdown the executor service gracefully.
        executorService.shutdown();
        try {
            // Wait for all tasks to finish; if not completed within 5 seconds, then exit.
            if (executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Final counter value: " + example.getCounter());
            } else {
                System.out.println("Timeout: Not all tasks finished.");
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for tasks to finish.");
            Thread.currentThread().interrupt();
        }
    }
}

/*

Output :
pool-1-thread-3 acquired the lock.
pool-1-thread-3 incremented counter to: 1
pool-1-thread-3 released the lock.
pool-1-thread-4 acquired the lock.
pool-1-thread-4 incremented counter to: 2
pool-1-thread-4 released the lock.
pool-1-thread-1 acquired the lock.
pool-1-thread-1 incremented counter to: 3
pool-1-thread-1 released the lock.
pool-1-thread-2 acquired the lock.
pool-1-thread-2 incremented counter to: 4
pool-1-thread-2 released the lock.
pool-1-thread-5 acquired the lock.
pool-1-thread-5 incremented counter to: 5
pool-1-thread-5 released the lock.
Final counter value: 5

*/
