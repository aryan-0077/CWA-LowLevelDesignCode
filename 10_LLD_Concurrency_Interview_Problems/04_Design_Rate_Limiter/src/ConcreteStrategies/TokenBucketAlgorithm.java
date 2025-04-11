package ConcreteStrategies;

import Interfaces.IRateLimiter;

public class TokenBucketAlgorithm implements IRateLimiter {
    private final int maxCapacity;
    private double availableTokens;
    private long lastRefillTimestampMs;
    private double refillTokensPerSecond;
    private final Object syncLock = new Object();

    public TokenBucketAlgorithm(int maxCapacity, double tokensPerSecond) {
        this.maxCapacity = maxCapacity;
        this.availableTokens = maxCapacity;
        this.refillTokensPerSecond = tokensPerSecond;
        this.lastRefillTimestampMs = System.currentTimeMillis();
    }

    private void refillBucket() {
        long currentTimeMs = System.currentTimeMillis();
        double durationSeconds = (currentTimeMs - lastRefillTimestampMs) / 1000.0;
        double tokensToAdd = durationSeconds * refillTokensPerSecond;

        if (tokensToAdd > 0) {
            availableTokens = Math.min(maxCapacity, availableTokens + tokensToAdd);
            lastRefillTimestampMs = currentTimeMs;
        }
    }

    @Override
    public boolean tryAcquire(int requestedTokens) {
        if (requestedTokens <= 0) {
            throw new IllegalArgumentException("Requested tokens must be positive");
        }

        synchronized (syncLock) {
            refillBucket();
            if (availableTokens >= requestedTokens) {
                availableTokens -= requestedTokens;
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void setRate(double tokensPerSecond) {
        synchronized (syncLock) {
            this.refillTokensPerSecond = tokensPerSecond;
        }
    }
}
