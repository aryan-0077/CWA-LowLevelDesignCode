package Controller;

import Interfaces.IRateLimiter;
import RateLimiterFactory.RateLimiterFactory;
import RateLimiterFactory.RateLimiterType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterController {
    private static final Map<String, IRateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    public static IRateLimiter getOrCreateLimiter(String limiterId, RateLimiterType type,
                                                  int capacity, double tokensPerSecond) {
        IRateLimiter iRateLimiter = rateLimiterMap.computeIfAbsent(limiterId, k ->
                RateLimiterFactory.createLimiter(type, capacity, tokensPerSecond));
        return iRateLimiter;
    }

    public static IRateLimiter getLimiter(String limiterId) {
        IRateLimiter limiter = rateLimiterMap.get(limiterId);
        if (limiter == null) {
            throw new IllegalArgumentException("No rate limiter exists with ID: " + limiterId);
        }
        return limiter;
    }
}
