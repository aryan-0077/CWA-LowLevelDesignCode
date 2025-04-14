package Controller.algorithmicController;

import Algorithms.IRateLimiter;
import CommonEnums.RateLimiterType;
import factory.RateLimiterFactory;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class RateLimiterController {
    private final IRateLimiter rateLimiter;
    private final ExecutorService executor;

    public RateLimiterController(RateLimiterType type, Map<String, Object> config, ExecutorService executorService) {
        this.rateLimiter = RateLimiterFactory.createLimiter(type, config);
        this.executor = executorService;
    }

    public CompletableFuture<Boolean> processRequest(String requestId) {
        return CompletableFuture.supplyAsync(() -> {
            boolean allowed = rateLimiter.giveAccess(requestId);
            if (allowed) {
                System.out.printf("Request %s: ✅ Allowed%n", requestId);
            } else {
                System.out.printf("Request %s: ❌ Blocked%n", requestId);
            }
            return allowed;
        }, executor);
    }

    public void updateConfiguration(Map<String, Object> config) {
        rateLimiter.updateConfiguration(config);
    }

    public void shutdown() {
        executor.shutdown();
    }
}