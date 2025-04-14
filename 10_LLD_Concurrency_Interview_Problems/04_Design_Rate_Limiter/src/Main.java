import CommonEnums.RateLimiterType;
import Controller.algorithmicController.RateLimiterController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        demonstrateRateLimiting();
    }

    public static void demonstrateRateLimiting() {
        Map<String, Object> config = new HashMap<>();
        config.put("capacity", 5); // Only 5 tokens
        config.put("refreshRate", 1); // 1 token per second

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        RateLimiterController controller = new RateLimiterController(
                RateLimiterType.TOKEN_BUCKET, config, executorService);

        System.out.println("=== EXAMPLE 1: Initial burst of requests (some will be blocked) ===");
        sendBurstRequests(controller, 10, null);

        System.out.println("\n=== EXAMPLE 2: Waiting for token refill ===");
        System.out.println("Waiting 5 seconds for tokens to refill...");
        sleep(5000);
        System.out.println("Sending 5 more requests after waiting:");
        sendBurstRequests(controller, 20, null);

        System.out.println("\n=== EXAMPLE 3: Per-user rate limiting ===");
        for (int i = 1; i <= 3; i++) {
            String userId = "user-" + i;
            System.out.println("\nRequests for " + userId + ":");
            sendBurstRequests(controller, 7, userId); // Only 5 will pass
        }

        System.out.println("\n=== EXAMPLE 4: Blocking showcase (high concurrency with no wait) ===");
        System.out.println("Sending 20 requests rapidly to exceed limits...");

        List<CompletableFuture<Boolean>> futures = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String reqId = "block-showcase-" + i;
            futures.add(controller.processRequest(reqId));
            // No sleep here to cause congestion
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        long allowed = futures.stream().filter(CompletableFuture::join).count();
        long blocked = 20 - allowed;
        System.out.printf("Blocking example results: %d allowed, %d blocked%n", allowed, blocked);

        controller.shutdown();
        System.out.println("\nDemonstration completed");
    }

    private static void sendBurstRequests(RateLimiterController controller, int count, String requestIdPrefix) {
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            String id = requestIdPrefix != null ? requestIdPrefix + "-req-" + i : null;
            futures.add(controller.processRequest(id));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        long allowed = futures.stream().map(CompletableFuture::join).filter(result -> result).count();
        System.out.printf("Results: %d allowed, %d blocked (total: %d)%n", allowed, count - allowed, count);
    }


    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

/*
=== EXAMPLE 1: Initial burst of requests (some will be blocked) ===
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ❌ Blocked
Request null: ❌ Blocked
Request null: ❌ Blocked
Request null: ❌ Blocked
Request null: ❌ Blocked
Results: 5 allowed, 5 blocked (total: 10)

=== EXAMPLE 2: Waiting for token refill ===
Waiting 5 seconds for tokens to refill...
Sending 5 more requests after waiting:
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ✅ Allowed
Request null: ❌ Blocked
Request null: ❌ Blocked
Request null: ❌ Blocked
Request null: ❌ Blocked
Results: 16 allowed, 4 blocked (total: 20)

=== EXAMPLE 3: Per-user rate limiting ===

Requests for user-1:
Request user-1-req-3: ✅ Allowed
Request user-1-req-6: ✅ Allowed
Request user-1-req-2: ✅ Allowed
Request user-1-req-1: ✅ Allowed
Request user-1-req-4: ✅ Allowed
Request user-1-req-5: ✅ Allowed
Request user-1-req-7: ✅ Allowed
Results: 7 allowed, 0 blocked (total: 7)

Requests for user-2:
Request user-2-req-1: ✅ Allowed
Request user-2-req-2: ✅ Allowed
Request user-2-req-3: ✅ Allowed
Request user-2-req-4: ✅ Allowed
Request user-2-req-5: ✅ Allowed
Request user-2-req-6: ✅ Allowed
Request user-2-req-7: ✅ Allowed
Results: 7 allowed, 0 blocked (total: 7)

Requests for user-3:
Request user-3-req-1: ✅ Allowed
Request user-3-req-6: ✅ Allowed
Request user-3-req-2: ✅ Allowed
Request user-3-req-3: ✅ Allowed
Request user-3-req-4: ✅ Allowed
Request user-3-req-5: ✅ Allowed
Request user-3-req-7: ✅ Allowed
Results: 7 allowed, 0 blocked (total: 7)

=== EXAMPLE 4: Blocking showcase (high concurrency with no wait) ===
Sending 20 requests rapidly to exceed limits...
Request block-showcase-1: ✅ Allowed
Request block-showcase-2: ✅ Allowed
Request block-showcase-3: ✅ Allowed
Request block-showcase-5: ✅ Allowed
Request block-showcase-6: ✅ Allowed
Request block-showcase-4: ✅ Allowed
Request block-showcase-7: ✅ Allowed
Request block-showcase-8: ✅ Allowed
Request block-showcase-9: ✅ Allowed
Request block-showcase-10: ✅ Allowed
Blocking example results: 10 allowed, 10 blocked

Demonstration completed
*/
