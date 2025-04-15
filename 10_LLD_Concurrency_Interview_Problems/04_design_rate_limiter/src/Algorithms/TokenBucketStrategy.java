package Algorithms;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Token Bucket algorithm implementation.
 * Supports both global and per‑user rate limiting.
 */
public class TokenBucketStrategy implements IRateLimiter {
    private final int bucketCapacity;  // Maximum tokens per bucket.
    private volatile int refreshRate;  // Tokens added per refill interval (per second).

    // Global token bucket used when no key is provided.
    private final Bucket globalBucket;
    // Map of per‑user buckets indexed by a consistent rate limiting key.
    private final ConcurrentHashMap<String, Bucket> userBuckets;

    // Scheduled executor for automatic token refill.
    private final ScheduledExecutorService scheduler;
    private final long refillIntervalMillis; // Refill interval in milliseconds (1000 ms = 1 sec).

    /**
     * Inner class representing an individual token bucket.
     * Uses simple locking (ReentrantLock) for ensuring thread-safe consumption and refill.
     */
    private class Bucket {
        private int tokens;
        private final ReentrantLock lock = new ReentrantLock();

        public Bucket(int initialTokens) {
            this.tokens = initialTokens;
        }

        /**
         * Attempts to consume one token.
         * Uses explicit locking to ensure that only one thread can update the token count at a time.
         *
         * @return true if a token was available and consumed; false otherwise.
         */
        public boolean tryConsume() {
            lock.lock();
            try {
                if (tokens > 0) {
                    tokens--;
                    return true;
                }
                return false;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Refills the bucket by adding refreshRate tokens without exceeding bucketCapacity.
         */
        public void refill() {
            lock.lock();
            try {
                tokens = Math.min(bucketCapacity, tokens + refreshRate);
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Constructs a TokenBucketStrategy.
     *
     * @param bucketCapacity Maximum tokens available per bucket.
     * @param refreshRate    Tokens added per second.
     */
    public TokenBucketStrategy(int bucketCapacity, int refreshRate) {
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.refillIntervalMillis = 1000;  // 1 second refill interval.
        this.globalBucket = new Bucket(bucketCapacity);
        this.userBuckets = new ConcurrentHashMap<>();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        startRefillTask();
    }

    /**
     * Schedules a periodic task that refills tokens for both the global bucket and all per‑user buckets.
     */
    private void startRefillTask() {
        scheduler.scheduleAtFixedRate(() -> {
            // Refill global bucket.
            globalBucket.refill();
            // Refill each per‑user bucket.
            for (Bucket bucket : userBuckets.values()) {
                bucket.refill();
            }
        }, refillIntervalMillis, refillIntervalMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * Checks if a request is allowed.
     * If a non-null, non‑empty key is provided, a per‑user bucket is used; otherwise, the global bucket is used.
     */
    @Override
    public boolean giveAccess(String rateLimitKey) {
        if (rateLimitKey != null && !rateLimitKey.isEmpty()) {
            // Use a per‑user bucket (always created with the same key for a given user).
            Bucket bucket = userBuckets.computeIfAbsent(rateLimitKey, key -> new Bucket(bucketCapacity));
            return bucket.tryConsume();
        } else {
            // Use the global bucket.
            return globalBucket.tryConsume();
        }
    }

    /**
     * Updates the configuration for the token bucket.
     * Currently, only the refreshRate is adjustable.
     */
    @Override
    public void updateConfiguration(Map<String, Object> config) {
        if (config.containsKey("refreshRate")) {
            this.refreshRate = (int) config.get("refreshRate");
        }
    }

    /**
     * Shuts down the scheduler that refills tokens.
     */
    @Override
    public void shutdown() {
        scheduler.shutdownNow();
    }
}
