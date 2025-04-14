package api;

import controller.RateLimiter;
import controller.ServiceConfig;
import models.ServiceConfiguration;
import request.ReadConfigRequest;
import request.ReadRequest;
import request.ServiceConfigurationRequest;
import request.UpdateRequest;
import response.Response;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class APIGateway {
    public RateLimiter rateLimiter;
    public ServiceConfig serviceConfig;
    public ExecutorService executorService = Executors.newFixedThreadPool(10);
    private ResponseWriter writer;

    // Service using Rate Limiter Client
    public void updateKey(String json) {
        CompletableFuture.supplyAsync(() -> rateLimiter.updateKeyMetrics(toObject(json, UpdateRequest.class)), executorService)
                .thenCompose(future -> future.thenAccept(response -> writer.returnResponse(response)));
    }

    // Rate Limiter Server -> Dashboard (Config)
    public void updateServiceConfig(String json) {
        CompletableFuture.supplyAsync(() -> serviceConfig.updateServiceConfig(toObject(json, ServiceConfigurationRequest.class)), executorService)
                .thenCompose(future -> future.thenAccept(response -> writer.returnResponse(response)));
    }

    // Service using Rate Limiter Client
    public void readKey(String json) {
        CompletableFuture.supplyAsync(() -> rateLimiter.readKeyMetrics(toObject(json, ReadRequest.class)), executorService)
                .thenCompose(future -> future.thenAccept(response -> writer.returnResponse(response)));
    }

    // Service using Rate Limiter Client
    public void readServiceConfig(String json) {
        CompletableFuture.supplyAsync(() -> serviceConfig.readServiceConfig(toObject(json, ReadConfigRequest.class)), executorService)
                .thenCompose(future -> future.thenAccept(response -> writer.returnResponse(response)));
    }

    private <T> T toObject(String json, Class<T> clazz) {
        return null;
    }
}

class ResponseWriter {
    public void returnResponse(Response response) {

    }
}