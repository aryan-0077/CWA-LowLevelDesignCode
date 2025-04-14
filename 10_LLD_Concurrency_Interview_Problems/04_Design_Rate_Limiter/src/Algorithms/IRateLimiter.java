package Algorithms;
import java.util.*;

// Strategy Pattern - Core interface defining the rate limiting contract
public interface IRateLimiter {
    /**
     * Checks if the request should be allowed
     * @param requestIdentifier Optional identifier for the request/user (can be null for global rate limiting)
     * @return true if request is allowed, false if it should be blocked
     */
    boolean giveAccess(String requestIdentifier);

    /**
     * Updates algorithm-specific configuration
     * @param config Configuration parameters specific to the algorithm
     */
    void updateConfiguration(Map<String, Object> config);
}
