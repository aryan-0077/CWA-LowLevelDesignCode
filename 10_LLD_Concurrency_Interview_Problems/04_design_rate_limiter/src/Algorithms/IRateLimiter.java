package Algorithms;

import java.util.Map;

/**
 * Interface for all rate limiting strategies.
 * New strategies (e.g., fixed window, sliding window, leaky bucket, etc.)
 * can easily be added by implementing this interface.
 */
public interface IRateLimiter {
    /**
     * Determines if a request should be allowed based on the rate limiting rules.
     *
     * @param rateLimitKey The key used for rate limiting; if non-null, used to identify a per‑user bucket.
     *                     A null (or empty) key indicates global rate limiting.
     * @return true if the request is allowed; false otherwise.
     */
    boolean giveAccess(String rateLimitKey);

    /**
     * Dynamically updates configuration parameters for the rate limiter.
     *
     * @param config A map of configuration options.
     */
    void updateConfiguration(Map<String, Object> config);

    /**
     * Cleanly shuts down the rate limiter (e.g., stops background tasks).
     */
    void shutdown();
}
