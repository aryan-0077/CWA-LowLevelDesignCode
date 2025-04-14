package controller;

import dao.RateLimiterDAO;
import request.ReadRequest;
import request.UpdateRequest;
import response.ReadResponse;
import response.UpdateResponse;

import java.util.concurrent.CompletableFuture;

public class RateLimiter {
    private RateLimiterDAO dao;

    public CompletableFuture<UpdateResponse> updateKeyMetrics(UpdateRequest request) {
        return dao.updateLimit(request.getServiceName(), request.getKey(), request.getCount(), request.getTimestamp())
                .thenApply(__ -> new UpdateResponse());
    }

    public CompletableFuture<ReadResponse> readKeyMetrics(ReadRequest request) {
        return dao.readLimit(request.getServiceName());
    }
}
