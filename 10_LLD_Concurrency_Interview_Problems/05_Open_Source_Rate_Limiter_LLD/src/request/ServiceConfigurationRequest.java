package request;

import java.util.concurrent.TimeUnit;

public class ServiceConfigurationRequest {
    String service;
    String key;
    String timeUnit;
    int limit;

    public String getService() {
        return service;
    }

    public String getKey() {
        return key;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public int getLimit() {
        return limit;
    }
}
