package models;

import java.util.concurrent.TimeUnit;

public class ServiceConfiguration {
    String service;
    String key;
    TimeUnit timeUnit;
    int limit;

    public ServiceConfiguration(String service, String key, TimeUnit timeUnit, int limit) {
        this.service = service;
        this.key = key;
        this.timeUnit = timeUnit;
        this.limit = limit;
    }
}
