import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create a BoundedBlockingQueue with a capacity of 5
        BoundedBlockingQueue queue = new BoundedBlockingQueue(5);

        // Create a thread pool for testing
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Producer threads to enqueue elements
        for (int i = 1; i <= 10; i++) {
            final int element = i;
            executor.submit(() -> {
                try {
                    queue.enqueue(element);
                    System.out.println(Thread.currentThread().getName() + " enqueued: " + element);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Delay to allow some enqueues before starting dequeues
        Thread.sleep(500);

        // Consumer threads to dequeue elements
        for (int i = 1; i <= 10; i++) {
            executor.submit(() -> {
                try {
                    int dequeued = queue.dequeue();
                    System.out.println(Thread.currentThread().getName() + " dequeued: " + dequeued);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Allow some time for all tasks to complete
        Thread.sleep(2000);

        // Print the final size of the queue
        System.out.println("Final queue size: " + queue.size());

        // Shutdown the executor service
        executor.shutdown();
    }
}

/*
Output :

pool-1-thread-5 enqueued: 5
pool-1-thread-1 enqueued: 1
pool-1-thread-2 enqueued: 2
pool-1-thread-4 enqueued: 4
pool-1-thread-3 enqueued: 3
pool-1-thread-5 dequeued: 4
pool-1-thread-8 enqueued: 8
pool-1-thread-9 enqueued: 9
pool-1-thread-1 dequeued: 5
pool-1-thread-1 dequeued: 9
pool-1-thread-3 dequeued: 1
pool-1-thread-2 dequeued: 3
pool-1-thread-7 enqueued: 7
pool-1-thread-4 dequeued: 2
pool-1-thread-10 enqueued: 10
pool-1-thread-8 dequeued: 8
pool-1-thread-5 dequeued: 7
pool-1-thread-6 enqueued: 6
pool-1-thread-9 dequeued: 10
pool-1-thread-1 dequeued: 6
Final queue size: 0
*/