package ThreadSynchronizationMethods.SynchronizedBlock;

public class CounterSyncBlock {
    private int count = 0;
    // Explicit lock object for finer control.
    private final Object lock = new Object();

    public void increment() {
        // Non-critical part: runs without locking.
        System.out.println("Non-Synchronized part (pre-processing): " + Thread.currentThread().getName());
        // Critical section: only this part is synchronized.
        synchronized (lock) {
            System.out.println("Synchronized Block - Start increment: " + Thread.currentThread().getName());
            count++;
            System.out.println("Synchronized Block - Counter value after increment: " + count);
            System.out.println("Synchronized Block - End increment: " + Thread.currentThread().getName());
        }
        // Non-critical part: runs after the synchronized block.
        System.out.println("Non-Synchronized part (post-processing): " + Thread.currentThread().getName());
    }

    public int getCount() {
        return count;
    }

    // Main method to test the synchronized block functionality.
    public static void main(String[] args) {
        CounterSyncBlock counter = new CounterSyncBlock();
        int numberOfThreads = 5;
        Thread[] threads = new Thread[numberOfThreads];
        // Create and start threads that execute the increment method.
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    counter.increment();
                }
            }, "Thread-" + (i + 1));
            threads[i].start();
        }
        // Wait for all threads to finish.
        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Display the final value of the counter.
        System.out.println("Final counter value: " + counter.getCount());
    }
}

/*

Output :
Non-Synchronized part (pre-processing): Thread-2
Non-Synchronized part (pre-processing): Thread-4
Non-Synchronized part (pre-processing): Thread-1
Non-Synchronized part (pre-processing): Thread-5
Non-Synchronized part (pre-processing): Thread-3
Synchronized Block - Start increment: Thread-2
Synchronized Block - Counter value after increment: 1
Synchronized Block - End increment: Thread-2
Synchronized Block - Start increment: Thread-3
Non-Synchronized part (post-processing): Thread-2
Synchronized Block - Counter value after increment: 2
Synchronized Block - End increment: Thread-3
Non-Synchronized part (post-processing): Thread-3
Synchronized Block - Start increment: Thread-5
Synchronized Block - Counter value after increment: 3
Synchronized Block - End increment: Thread-5
Non-Synchronized part (post-processing): Thread-5
Synchronized Block - Start increment: Thread-1
Synchronized Block - Counter value after increment: 4
Synchronized Block - End increment: Thread-1
Non-Synchronized part (post-processing): Thread-1
Synchronized Block - Start increment: Thread-4
Synchronized Block - Counter value after increment: 5
Synchronized Block - End increment: Thread-4
Non-Synchronized part (post-processing): Thread-4
Final counter value: 5

*/