package Algorithms;

import Algorithms.IRateLimiter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucketStrategy implements IRateLimiter {
    private final int bucketCapacity;
    private final AtomicInteger currentCapacity;
    private final AtomicLong lastRefreshTime;
    private int refreshRate; // tokens per second

    // For user-specific rate limiting
    private final Map<String, UserBucket> userBuckets = new ConcurrentHashMap<>();

    private static class UserBucket {
        final AtomicInteger currentCapacity;
        final AtomicLong lastRefreshTime;

        UserBucket(int initialCapacity) {
            this.currentCapacity = new AtomicInteger(initialCapacity);
            this.lastRefreshTime = new AtomicLong(System.currentTimeMillis());
        }
    }

    public TokenBucketStrategy(int bucketCapacity, int refreshRate) {
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.currentCapacity = new AtomicInteger(bucketCapacity);
        this.lastRefreshTime = new AtomicLong(System.currentTimeMillis());
    }

    private void refreshBucket(UserBucket bucket) {
        long currentTime = System.currentTimeMillis();
        long elapsedTimeMs = currentTime - bucket.lastRefreshTime.get();
        int tokensToAdd = (int) (elapsedTimeMs / 1000 * refreshRate);

        if (tokensToAdd > 0) {
            // Atomic operation to update capacity without exceeding bucket capacity
            bucket.currentCapacity.updateAndGet(current ->
                    Math.min(bucketCapacity, current + tokensToAdd));
            bucket.lastRefreshTime.set(currentTime);
        }
    }

    private void refreshGlobalBucket() {
        long currentTime = System.currentTimeMillis();
        long elapsedTimeMs = currentTime - lastRefreshTime.get();
        int tokensToAdd = (int) (elapsedTimeMs / 1000 * refreshRate);

        if (tokensToAdd > 0) {
            // Atomic operation to update capacity without exceeding bucket capacity
            currentCapacity.updateAndGet(current ->
                    Math.min(bucketCapacity, current + tokensToAdd));
            lastRefreshTime.set(currentTime);
        }
    }

    @Override
    public boolean giveAccess(String requestIdentifier) {
        // If request identifier is provided, use per-user bucketing
        if (requestIdentifier != null && !requestIdentifier.isEmpty()) {
            return handleUserRequest(requestIdentifier);
        }

        // Otherwise use global bucket
        refreshGlobalBucket();
        return currentCapacity.getAndUpdate(current ->
                current > 0 ? current - 1 : current) > 0;
    }

    private boolean handleUserRequest(String userId) {
        UserBucket userBucket = userBuckets.computeIfAbsent(userId,
                id -> new UserBucket(bucketCapacity));

        refreshBucket(userBucket);
        return userBucket.currentCapacity.getAndUpdate(current ->
                current > 0 ? current - 1 : current) > 0;
    }

    @Override
    public void updateConfiguration(Map<String, Object> config) {
        if (config.containsKey("refreshRate")) {
            this.refreshRate = (int) config.get("refreshRate");
        }
    }
}