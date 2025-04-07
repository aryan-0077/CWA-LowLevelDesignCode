import DataSources.DataStorage;
import DataSources.HashMapDataSource;
import EvictionAlgorithms.ConcreteEvictionAlgorithms.LRUEvictionAlgorithm;
import EvictionAlgorithms.EvictionAlgorithm;
import MainCache.Cache;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize components
            // Create a data storage with capacity of 100 items
            DataStorage<String, String> dataStorage = new HashMapDataSource<>(100);

            // Create an LRU eviction algorithm
            EvictionAlgorithm<String> evictionAlgorithm = new LRUEvictionAlgorithm<>();

            // Create the cache with our selected components
            Cache<String, String> cache = new Cache<>(evictionAlgorithm, dataStorage);

            System.out.println("Cache system initialized successfully.");

            // Demonstrate basic operations
            demonstrateBasicOperations(cache);

            // Demonstrate eviction
            demonstrateEviction(cache);

            // Demonstrate async operations
            demonstrateAsyncOperations(cache);

            // Demonstrate error handling
            demonstrateErrorHandling(cache);

        } catch (Exception e) {
            System.err.println("Error in cache demonstration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void demonstrateBasicOperations(Cache<String, String> cache) throws Exception {
        System.out.println("\n=== Basic Operations ===");

        // Put some data
        cache.put("key1", "value1").get(); // Using .get() to wait for async operation
        cache.put("key2", "value2").get();

        // Get data
        String value1 = cache.get("key1").get();
        System.out.println("Retrieved key1: " + value1);

        // Update data
        cache.put("key1", "updated_value1").get();
        value1 = cache.get("key1").get();
        System.out.println("Updated key1: " + value1);

        // Get non-existent key
        String valueNonExistent = cache.get("nonexistent").get();
        System.out.println("Retrieved nonexistent key: " + valueNonExistent);
    }

    private static void demonstrateEviction(Cache<String, String> cache) throws Exception {
        System.out.println("\n=== Eviction Demonstration ===");

        // Let's assume we're using a small cache for this example
        // We'll fill it with data to trigger eviction

        // First, let's access key1 to make it most recently used
        cache.get("key1").get();

        // Add many items to trigger eviction
        System.out.println("Adding 100 items to trigger eviction...");
        for (int i = 0; i < 100; i++) {
            cache.put("bulk_key" + i, "bulk_value" + i).get();
        }

        // Now try to get the first inserted keys (they might be evicted)
        String key2Value = cache.get("key2").get();
        System.out.println("key2 (should be evicted if using LRU): " + (key2Value == null ? "EVICTED" : key2Value));

        String key1Value = cache.get("key1").get();
        System.out.println("key1 (accessed more recently): " + (key1Value == null ? "EVICTED" : key1Value));
    }

    private static void demonstrateAsyncOperations(Cache<String, String> cache) throws Exception{
        System.out.println("\n=== Async Operations ===");

        try {
            CompletableFuture<Void> future1 = cache.put("async1", "value1");
            CompletableFuture<Void> future2 = cache.put("async2", "value2");
            CompletableFuture<Void> future3 = cache.put("async3", "value3");

            // Combine futures to wait for all operations to complete
            CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);

            // Add a callback when all operations are done
            allFutures.thenRun(() -> {
                System.out.println("All async put operations completed!");

                // Read back the values asynchronously
                try {
                    CompletableFuture<String> getAsync1 = cache.get("async1");
                    CompletableFuture<String> getAsync2 = cache.get("async2");
                    CompletableFuture<String> getAsync3 = cache.get("async3");

                    // Process results when available
                    getAsync1.thenAccept(value -> System.out.println("async1: " + value));
                    getAsync2.thenAccept(value -> System.out.println("async2: " + value));
                    getAsync3.thenAccept(value -> System.out.println("async3: " + value));

                    // Wait for all gets to complete
                    CompletableFuture.allOf(getAsync1, getAsync2, getAsync3).get();

                } catch (Exception e) {
                    System.err.println("Error in async get: " + e.getMessage());
                }
            }).get(); // Wait for this demonstration to complete

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error in async operations: " + e.getMessage());
        }
    }

    private static void demonstrateErrorHandling(Cache<String, String> cache) throws Exception{
        System.out.println("\n=== Error Handling ===");

        try {
            // Try to get a key that doesn't exist
            CompletableFuture<String> getNonExistent = cache.get("definitely_not_there");
            getNonExistent
                    .thenAccept(value -> {
                        if (value == null) {
                            System.out.println("Key not found, as expected");
                        } else {
                            System.out.println("Unexpected value found: " + value);
                        }
                    })
                    .exceptionally(ex -> {
                        System.err.println("Error handled: " + ex.getMessage());
                        return null;
                    })
                    .get();

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error in error handling demonstration: " + e.getMessage());
        }
    }
}