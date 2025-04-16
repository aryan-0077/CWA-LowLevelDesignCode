package Controller;

import Algorithms.IRateLimiter;
import CommonEnums.RateLimiterType;
import factory.RateLimiterFactory;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Controller that delegates incoming requests to a rate limiter.
 * Processes requests concurrently using an ExecutorService.
 */
public class RateLimiterController {
    private final IRateLimiter rateLimiter;
    private final ExecutorService executor;

    /**
     * Constructs a RateLimiterController by creating a rate limiter using the factory and assigning an ExecutorService.
     *
     * @param type            The rate limiter type.
     * @param config          Configuration for the rate limiter.
     * @param executorService The ExecutorService for concurrent request processing.
     */
    public RateLimiterController(RateLimiterType type, Map<String, Object> config, ExecutorService executorService) {
        this.rateLimiter = RateLimiterFactory.createLimiter(type, config);
        this.executor = executorService;
    }

    /**
     * Processes a request asynchronously.
     * The provided key is used for rate limiting (for per‑user buckets when applicable).
     *
     * @param rateLimitKey The key used for rate limiting; use a consistent key for the same user.
     *                     A null key indicates global rate limiting.
     * @return A CompletableFuture that completes with true if the request is allowed.
     */
    public CompletableFuture<Boolean> processRequest(String rateLimitKey) {
        return CompletableFuture.supplyAsync(() -> {
            boolean allowed = rateLimiter.giveAccess(rateLimitKey);
            if (allowed) {
                System.out.printf("Request with key [%s]: ✅ Allowed%n", rateLimitKey);
            } else {
                System.out.printf("Request with key [%s]: ❌ Blocked%n", rateLimitKey);
            }
            return allowed;
        }, executor);
    }

    /**
     * Updates the configuration of the underlying rate limiter.
     *
     * @param config The configuration options to update.
     */
    public void updateConfiguration(Map<String, Object> config) {
        rateLimiter.updateConfiguration(config);
    }

    /**
     * Shuts down the controller as well as the underlying rate limiter and executor.
     */
    public void shutdown() {
        rateLimiter.shutdown();
        executor.shutdownNow();
    }
}
