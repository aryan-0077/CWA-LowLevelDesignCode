package Simulator;

import Client.APIClient;
import Controller.RateLimiterController;
import CommonEnums.RateLimiterType;
import java.util.*;
import java.util.concurrent.*;

public class RateLimiter {

    public void runSimulation() {
        // Configuration
        Map<String, Object> config = new HashMap<>();
        config.put("capacity", 10);
        config.put("tokensPerSecond", 1.0);

        // Thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        RateLimiterController controller = new RateLimiterController(RateLimiterType.TOKEN_BUCKET, config, executorService);
        APIClient client = new APIClient(controller);

        List<CompletableFuture<Boolean>> futures = new ArrayList<>();

        // Simulate 20 requests
        for (int i = 1; i <= 20; i++) {
            String requestId = "req-" + i;
            futures.add(client.sendRequest(requestId, 1));

            try {
                Thread.sleep(new Random().nextInt(300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Wait for all to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        controller.shutdown();
    }
}
