package dao;

import response.ReadResponse;

import java.util.concurrent.CompletableFuture;

public class RateLimiterDAO {
    public CompletableFuture<Void> updateLimit(String serviceName, String key, int count, long timestamp) {
        return null;
    }

    public CompletableFuture<ReadResponse> readLimit(String serviceName) {
        return null;
    }
}
