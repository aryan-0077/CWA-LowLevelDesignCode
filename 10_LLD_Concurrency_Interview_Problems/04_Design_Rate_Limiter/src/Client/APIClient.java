package Client;

import Controller.RateLimiterController;
import java.util.concurrent.CompletableFuture;

public class APIClient {

    private final RateLimiterController controller;

    public APIClient(RateLimiterController controller) {
        this.controller = controller;
    }

    public CompletableFuture<Boolean> sendRequest(String requestId, int tokensRequired) {
        return controller.processRequest(requestId, tokensRequired);
    }
}

