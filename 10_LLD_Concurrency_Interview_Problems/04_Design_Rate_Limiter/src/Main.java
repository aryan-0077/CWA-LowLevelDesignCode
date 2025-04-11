import Controller.RateLimiterController;
import Interfaces.IRateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Get a singleton rate limiter allowing 5 requests per second
        IRateLimiter apiRateLimiter = RateLimiterController.getOrCreateLimiter("api-service",
                RateLimiterFactory.RateLimiterType.TOKEN_BUCKET, 5, 5);

        // Simulate multiple threads trying to access a resource
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger rejectionCount = new AtomicInteger(0);

        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                // Now using explicit parameter (1 token) instead of parameter-less version
                if (apiRateLimiter.tryAcquire(4)) {
                    System.out.println(Thread.currentThread().getName() + ": Request approved");
                    successCount.incrementAndGet();
                } else {
                    System.out.println(Thread.currentThread().getName() + ": Request throttled");
                    rejectionCount.incrementAndGet();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Approved requests: " + successCount.get());
        System.out.println("Throttled requests: " + rejectionCount.get());
    }
}