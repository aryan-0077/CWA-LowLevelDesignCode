package request;

public abstract class Request {
    String serviceName;
    String id;

    public String getServiceName() {
        return serviceName;
    }

    public String getId() {
        return id;
    }
}
