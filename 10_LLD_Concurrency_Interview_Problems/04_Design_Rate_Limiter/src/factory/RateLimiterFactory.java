package factory;

import Algorithms.ConcreteStrategies.TokenBucketAlgorithm;
import Algorithms.IRateLimiter;
import CommonEnums.RateLimiterType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RateLimiterFactory {

    // Map to store factory methods for each rate limiter type
    private static final Map<RateLimiterType, Function<Map<String, Object>, IRateLimiter>>
            RATE_LIMITER_FACTORIES = new HashMap<>();

    // Initialize the factory methods for each rate limiter type
    static {
        // TokenBucket factory method
        RATE_LIMITER_FACTORIES.put(RateLimiterType.TOKEN_BUCKET, config -> {
            int capacity = (int) config.getOrDefault("capacity", 100);
            double tokensPerSecond = (double) config.getOrDefault("tokensPerSecond", 10.0);
            return new TokenBucketAlgorithm(capacity, tokensPerSecond);
        });

//        // FixedWindow factory method
//        RATE_LIMITER_FACTORIES.put(RateLimiterType.FIXED_WINDOW, config -> {
//            int windowSizeInSeconds = (int) config.getOrDefault("windowSizeInSeconds", 60);
//            int maxRequestsPerWindow = (int) config.getOrDefault("maxRequestsPerWindow", 100);
//            return new FixedWindowAlgorithm(windowSizeInSeconds, maxRequestsPerWindow);
//        });
//
//        // SlidingWindow factory method
//        RATE_LIMITER_FACTORIES.put(RateLimiterType.SLIDING_WINDOW, config -> {
//            int windowSizeInSeconds = (int) config.getOrDefault("windowSizeInSeconds", 60);
//            int maxRequestsPerWindow = (int) config.getOrDefault("maxRequestsPerWindow", 100);
//            int precision = (int) config.getOrDefault("precision", 10);
//            return new SlidingWindowAlgorithm(windowSizeInSeconds, maxRequestsPerWindow, precision);
//        });
//
//        // LeakyBucket factory method
//        RATE_LIMITER_FACTORIES.put(RateLimiterType.LEAKY_BUCKET, config -> {
//            int capacity = (int) config.getOrDefault("capacity", 100);
//            double leakRatePerSecond = (double) config.getOrDefault("leakRatePerSecond", 10.0);
//            return new LeakyBucketAlgorithm(capacity, leakRatePerSecond);
//        });
    }

    /**
     * Creates a rate limiter based on the specified type and configuration
     *
     * @param type The type of rate limiter to create
     * @param config Map containing configuration parameters for the rate limiter
     * @return An instance of the specified rate limiter
     * @throws IllegalArgumentException if the specified type is not supported
     */
    public static IRateLimiter createLimiter(RateLimiterType type, Map<String, Object> config) {
        Function<Map<String, Object>, IRateLimiter> factory = RATE_LIMITER_FACTORIES.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported rate limiter type: " + type);
        }
        return factory.apply(config);
    }

    /**
     * Simplified factory method for token bucket (for backward compatibility)
     */
    public static IRateLimiter createLimiter(RateLimiterType type, int capacity, double tokensPerSecond) {
        Map<String, Object> config = new HashMap<>();
        config.put("capacity", capacity);
        config.put("tokensPerSecond", tokensPerSecond);
        return createLimiter(type, config);
    }

    /**
     * Registers a new factory method for a rate limiter type
     *
     * @param type The rate limiter type
     * @param factory The factory function to create the rate limiter
     */
    public static void registerLimiterFactory(
            RateLimiterType type,
            Function<Map<String, Object>, IRateLimiter> factory) {
        RATE_LIMITER_FACTORIES.put(type, factory);
    }
}