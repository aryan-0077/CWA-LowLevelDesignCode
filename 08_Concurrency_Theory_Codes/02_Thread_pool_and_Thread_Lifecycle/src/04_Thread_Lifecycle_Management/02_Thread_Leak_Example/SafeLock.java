package ThreadLifecycleManagement.Thread_Leak_Example;

public class SafeLock {
    private final Object lock = new Object();

    void waitForSignal() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting...");
                lock.wait(3000); // Wait with a timeout to prevent leak
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
