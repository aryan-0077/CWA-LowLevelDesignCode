package Interfaces;

// Strategy Pattern - Core interface defining the rate limiting contract
public interface IRateLimiter {
    boolean tryAcquire(int tokens);
    void setRate(double tokensPerSecond);
}

