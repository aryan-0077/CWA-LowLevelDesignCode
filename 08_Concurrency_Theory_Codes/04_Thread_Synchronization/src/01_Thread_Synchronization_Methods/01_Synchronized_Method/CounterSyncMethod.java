package ThreadSynchronizationMethods.SynchronizedMethod;

public class CounterSyncMethod {
    private int count = 0;
    // The entire method is synchronized.
    public synchronized void increment() {
        System.out.println("Synchronized Method - Start increment: " + Thread.currentThread().getName());
        // Critical section: updating the shared counter
        count++;
        System.out.println("Synchronized Method - Counter value after increment: " + count);
        System.out.println("Synchronized Method - End increment: " + Thread.currentThread().getName());
    }

    public int getCount() {
        return count;
    }

    // Main method to test the synchronized method
    public static void main(String[] args) {
        CounterSyncMethod counter = new CounterSyncMethod();
        int numberOfThreads = 5;
        Thread[] threads = new Thread[numberOfThreads];
        // Create and start threads that call the synchronized increment method.
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    counter.increment();
                }
            }, "Thread-" + (i + 1));


            threads[i].start();
        }
        // Wait for all threads to complete.
        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Display the final counter value.
        System.out.println("Final counter value: " + counter.getCount());
    }
}

/*

Output :
Synchronized Method - Start increment: Thread-3
Synchronized Method - Counter value after increment: 1
Synchronized Method - End increment: Thread-3
Synchronized Method - Start increment: Thread-4
Synchronized Method - Counter value after increment: 2
Synchronized Method - End increment: Thread-4
Synchronized Method - Start increment: Thread-1
Synchronized Method - Counter value after increment: 3
Synchronized Method - End increment: Thread-1
Synchronized Method - Start increment: Thread-2
Synchronized Method - Counter value after increment: 4
Synchronized Method - End increment: Thread-2
Synchronized Method - Start increment: Thread-5
Synchronized Method - Counter value after increment: 5
Synchronized Method - End increment: Thread-5
Final counter value: 5

*/