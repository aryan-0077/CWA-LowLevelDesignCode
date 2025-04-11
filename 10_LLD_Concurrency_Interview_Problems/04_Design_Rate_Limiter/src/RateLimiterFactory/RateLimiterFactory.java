package RateLimiterFactory;

import ConcreteStrategies.TokenBucketAlgorithm;
import Interfaces.IRateLimiter;

import static RateLimiterFactory.RateLimiterType.TOKEN_BUCKET;

public class RateLimiterFactory {
    public static IRateLimiter createLimiter(RateLimiterType type, int capacity, double tokensPerSecond) {
        switch (type) {
            case TOKEN_BUCKET:
                return new TokenBucketAlgorithm(capacity, tokensPerSecond);
            // Add cases for other algorithms when implemented
            default:
                throw new IllegalArgumentException("Unsupported rate limiter type: " + type);
        }
    }
}
