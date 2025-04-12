package Controller;

import Algorithms.IRateLimiter;
import CommonEnums.RateLimiterType;
import factory.RateLimiterFactory;
import java.util.Map;
import java.util.concurrent.*;

public class RateLimiterController {

    private final IRateLimiter rateLimiter;
    private final ExecutorService executor;

    public RateLimiterController(RateLimiterType type, Map<String, Object> config, ExecutorService executorService) {
        this.rateLimiter = RateLimiterFactory.createLimiter(type, config);
        this.executor = executorService;
    }

    public CompletableFuture<Boolean> processRequest(String requestId, int tokens) {
        return CompletableFuture.supplyAsync(() -> {
            boolean allowed = rateLimiter.tryAcquire(tokens);
            if (allowed) {
                System.out.printf("Request %s: ✅ Allowed%n", requestId);
            } else {
                System.out.printf("Request %s: ❌ Blocked%n", requestId);
            }
            return allowed;
        }, executor);
    }

    public void shutdown() {
        executor.shutdown();
    }
}

