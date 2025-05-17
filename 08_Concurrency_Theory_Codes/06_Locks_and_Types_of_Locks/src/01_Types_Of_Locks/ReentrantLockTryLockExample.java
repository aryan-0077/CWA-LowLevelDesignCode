package Types_Of_Locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTryLockExample {
    private final ReentrantLock lock = new ReentrantLock();
    // Task that holds the lock for an extended period.
    public void longTask(String taskName) {
        lock.lock();
        try {
            System.out.println(taskName + " acquired the lock and is performing a long task.");
            // Simulate a long operation (e.g., by sleeping or doing busy work)
            // Here, we sleep to emulate that long operation.
            Thread.sleep(5000);
            System.out.println(taskName + " finished the task and is releasing the lock.");
        } catch (InterruptedException e) {
            System.out.println(taskName + " was interrupted.");
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    // Task that attempts to acquire the lock using tryLock with a timeout.
    public void tryLockTask(String taskName) {
        try {
            // Try to acquire the lock for 2 seconds.
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println(taskName + " acquired the lock using tryLock and is performing its task.");
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(taskName + " could not acquire the lock using tryLock within 2 seconds.");
            }
        } catch (InterruptedException e) {
            System.out.println(taskName + " was interrupted while waiting for the lock.");
            Thread.currentThread().interrupt();
        }
    }
    public static void main(String[] args) {
        ReentrantLockTryLockExample example = new ReentrantLockTryLockExample();
        // Use ExecutorService to manage threads.
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // Submit Task-A to acquire the lock and hold it for a long time.
        executor.submit(() -> example.longTask("Task-A"));
        // Short delay to ensure Task-A acquires the lock first.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Submit Task-B that attempts to acquire the lock using tryLock.
        executor.submit(() -> example.tryLockTask("Task-B"));
        // Shutdown the executor.
        executor.shutdown();
    }
}

/*

Output :
Task-A acquired the lock and is performing a long task.
Task-B could not acquire the lock using tryLock within 2 seconds.
Task-A finished the task and is releasing the lock.


*/