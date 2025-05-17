package Types_Of_Locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int logValue = 0;

    // Simulate processing work using a dummy computation loop.
    private void simulateWork() {
        long sum = 0;
        for (int i = 0; i < 500000; i++) {
            sum += i;
        }
        // (The computed sum is discarded; its purpose is solely to consume CPU time.)
    }

    // Write operation: exclusively updates the shared logValue.
    public void writeValue(String taskName, int newValue) {
        rwLock.writeLock().lock();
        try {
            System.out.println(taskName + " (write): Acquired write lock.");
            simulateWork();
            logValue = newValue;
            System.out.println(taskName + " (write): Updated logValue to " + logValue);
        } finally {
            System.out.println(taskName + " (write): Released write lock.");
            rwLock.writeLock().unlock();
        }
    }

    // Read operation: reads the shared logValue.
    public void readValue(String taskName) {
        rwLock.readLock().lock();
        try {
            System.out.println(taskName + " (read): Acquired read lock. Reading logValue: " + logValue);
            simulateWork();
            System.out.println(taskName + " (read): Finished reading.");
        } finally {
            System.out.println(taskName + " (read): Released read lock.");
            rwLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample logExample = new ReadWriteLockExample();
        // Create an ExecutorService with a fixed pool of 4 threads.
        ExecutorService executor = Executors.newFixedThreadPool(4);
        /*
         - Schedule tasks to simulate the following sequence:
         - 1. Start with three reader tasks concurrently.
         - 2. Then, a writer task updates the log.
         - 3. Next, two readers read the updated value.
         - 4. Then, a second writer task updates the log.
         - 5. Finally, one more reader reads the new value.
         */
        // Submit three concurrent reader tasks.
        executor.submit(() -> logExample.readValue("Reader-2"));
        executor.submit(() -> logExample.readValue("Reader-3"));

        // Submit a writer task.
        executor.submit(() -> logExample.writeValue("Writer-1", 100));

        // Submit two additional reader tasks.
        executor.submit(() -> logExample.readValue("Reader-4"));
        executor.submit(() -> logExample.readValue("Reader-5"));

        // Submit a second writer task.
        executor.submit(() -> logExample.writeValue("Writer-2", 200));

        // Submit a final reader task.
        executor.submit(() -> logExample.readValue("Reader-6"));

        // Shut down the executor.
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Timeout waiting for tasks to finish.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

/*

Output :
Reader-2 (read): Acquired read lock. Reading logValue: 0
Reader-3 (read): Acquired read lock. Reading logValue: 0
Reader-3 (read): Finished reading.
Reader-2 (read): Finished reading.
Reader-3 (read): Released read lock.
Reader-2 (read): Released read lock.
Writer-1 (write): Acquired write lock.
Writer-1 (write): Updated logValue to 100
Writer-1 (write): Released write lock.
Reader-6 (read): Acquired read lock. Reading logValue: 100
Reader-4 (read): Acquired read lock. Reading logValue: 100
Reader-5 (read): Acquired read lock. Reading logValue: 100
Reader-6 (read): Finished reading.
Reader-6 (read): Released read lock.
Reader-5 (read): Finished reading.
Reader-5 (read): Released read lock.
Reader-4 (read): Finished reading.
Reader-4 (read): Released read lock.
Writer-2 (write): Acquired write lock.
Writer-2 (write): Updated logValue to 200
Writer-2 (write): Released write lock.

*/
