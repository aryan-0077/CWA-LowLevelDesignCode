package Types_Of_Sempahore;

import java.util.concurrent.Semaphore;

public class CountingSemaphoreExample {
    private static final Semaphore resourcePool = new Semaphore(3); // Semaphore with 3 permits

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            final int threadNum = i;
            Thread t = new Thread(() -> accessResource("Thread-" + threadNum));
            t.start();
        }
    }

    private static void accessResource(String threadName) {
        try {
            System.out.println(threadName + " is attempting to acquire a permit.");
            resourcePool.acquire(); // Acquire a permit
            System.out.println(threadName + " acquired a permit.");
            Thread.sleep(2000); // Simulate resource usage
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            resourcePool.release(); // Release the permit
            System.out.println(threadName + " released the permit.");
        }
    }
}

/*

Output :
Thread-3 is attempting to acquire a permit.
Thread-5 is attempting to acquire a permit.
Thread-5 acquired a permit.
Thread-1 is attempting to acquire a permit.
Thread-1 acquired a permit.
Thread-2 is attempting to acquire a permit.
Thread-3 acquired a permit.
Thread-4 is attempting to acquire a permit.
Thread-4 acquired a permit.
Thread-2 acquired a permit.
Thread-1 released the permit.
Thread-3 released the permit.
Thread-5 released the permit.
Thread-4 released the permit.
Thread-2 released the permit.

*/