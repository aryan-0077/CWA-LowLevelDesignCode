package factory;

import Algorithms.IRateLimiter;
import Algorithms.TokenBucketStrategy;
import CommonEnums.RateLimiterType;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Factory class that creates rate limiter instances based on the provided type and configuration.
 * New rate limiting strategies can be added by registering additional factory functions.
 */
public class RateLimiterFactory {
    private static final Map<RateLimiterType, Function<Map<String, Object>, IRateLimiter>> limiterFactories = new HashMap<>();

    static {
        // Factory registration for the Token Bucket rate limiter.
        limiterFactories.put(RateLimiterType.TOKEN_BUCKET, config -> {
            int capacity = (int) config.getOrDefault("capacity", 10);
            int refreshRate;
            if (config.containsKey("refreshRate")) {
                refreshRate = (int) config.get("refreshRate");
            } else {
                double tokensPerSecond = (double) config.getOrDefault("tokensPerSecond", 10.0);
                refreshRate = (int) Math.round(tokensPerSecond);
            }
            return new TokenBucketStrategy(capacity, refreshRate);
        });

        // Additional strategies (FIXED_WINDOW, SLIDING_WINDOW, LEAKY_BUCKET, etc.) can be registered here.
    }

    /**
     * Creates an IRateLimiter instance for the specified type using the provided configuration.
     *
     * @param type   The type of rate limiter.
     * @param config The configuration parameters.
     * @return A new IRateLimiter instance.
     * @throws IllegalArgumentException if the specified type is not supported.
     */
    public static IRateLimiter createLimiter(RateLimiterType type, Map<String, Object> config) {
        Function<Map<String, Object>, IRateLimiter> factory = limiterFactories.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported rate limiter type: " + type);
        }
        return factory.apply(config);
    }

    /**
     * Allows for dynamic registration of new rate limiter factory functions.
     *
     * @param type    The rate limiter type.
     * @param factory The factory function to create the rate limiter.
     */
    public static void registerLimiterFactory(RateLimiterType type, Function<Map<String, Object>, IRateLimiter> factory) {
        limiterFactories.put(type, factory);
    }
}
