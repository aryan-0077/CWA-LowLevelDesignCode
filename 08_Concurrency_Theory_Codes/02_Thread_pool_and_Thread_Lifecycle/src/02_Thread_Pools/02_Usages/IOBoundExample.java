package ThreadPools.Usages;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IOBoundExample {
    public static void main(String[] args) {
        ExecutorService cachedPool = Executors.newCachedThreadPool(); // Best for I/O-bound short tasks
        for (int i = 0; i < 10; i++) {
            cachedPool.execute(() -> {
                simulateWebRequest();
                System.out.println(Thread.currentThread().getName() + " completed I/O task.");
            });
        }
        cachedPool.shutdown();
    }

    private static void simulateWebRequest() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting for response...");
            TimeUnit.MILLISECONDS.sleep(500); // Simulating network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
