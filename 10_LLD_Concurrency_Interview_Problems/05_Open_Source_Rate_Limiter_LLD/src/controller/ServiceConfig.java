package controller;

import dao.RateLimiterDAO;
import dao.ServiceConfigDAO;
import models.ServiceConfiguration;
import request.ReadConfigRequest;
import request.ServiceConfigurationRequest;
import response.ServiceConfigurationResponse;
import response.UpdateConfigResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ServiceConfig {
    private ServiceConfigDAO dao;

    public CompletableFuture<UpdateConfigResponse> updateServiceConfig(ServiceConfigurationRequest request) {
        return dao.updateServiceConfig(new ServiceConfiguration(request.getService(),
                        request.getKey(),
                        TimeUnit.valueOf(request.getTimeUnit()),
                        request.getLimit()))
                .thenApply(__ -> new UpdateConfigResponse());
    }

    public CompletableFuture<ServiceConfigurationResponse> readServiceConfig(ReadConfigRequest readConfigRequest) {
        return dao.readConfig(readConfigRequest.getServiceName())
                .thenApply(config -> new ServiceConfigurationResponse(config));
    }
}
