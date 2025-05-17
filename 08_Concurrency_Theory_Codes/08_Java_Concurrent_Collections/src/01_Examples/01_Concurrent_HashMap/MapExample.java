package Examples.ConcurrentHashMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapExample {
    public static void main(String[] args) throws InterruptedException {
        // Create a non-thread-safe HashMap wrapped as a synchronized map.
        // Even though Collections.synchronizedMap provides basic thread safety for individual
        // operations, you still need to manually synchronize when iterating over it.
        final Map<Integer, String> hashMap = Collections.synchronizedMap(new HashMap<>());

        // Create a ConcurrentHashMap which is thread-safe and designed for concurrent access.
        final ConcurrentHashMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();

        // ----- Example 1: Using HashMap with manual locking -----
        Thread hashMapUpdater = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                // No explicit manual lock is needed for put() as synchronizedMap wraps each operation.
                hashMap.put(i, "Value " + i);
                try {
                    Thread.sleep(50); // simulate some processing time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread hashMapIterator = new Thread(() -> {
            // Wait a short while so that some entries are added.
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // When iterating over a synchronizedMap, you must lock the map manually.
            synchronized (hashMap) {
                for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
                    System.out.println(
                            "hashMap Iteration - Key: " + entry.getKey() + ", Value: " + entry.getValue());
                }
            }
        });

        // Start both threads and wait for them to finish.
        hashMapUpdater.start();
        hashMapIterator.start();
        hashMapUpdater.join();
        hashMapIterator.join();
        System.out.println("Final hashMap: " + hashMap);

        // ----- Example 2: Using ConcurrentHashMap -----
        Thread concurrentMapUpdater = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                concurrentMap.put(i, "Value " + i);
                try {
                    Thread.sleep(50); // simulate some processing time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread concurrentMapIterator = new Thread(() -> {
            // Wait a short while so that some entries are added.
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // With ConcurrentHashMap, iteration is safe without any external synchronization.
            for (Map.Entry<Integer, String> entry : concurrentMap.entrySet()) {
                System.out.println(
                        "concurrentMap Iteration - Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
        });

        // Start both threads and wait for them to finish.
        concurrentMapUpdater.start();
        concurrentMapIterator.start();
        concurrentMapUpdater.join();
        concurrentMapIterator.join();
        System.out.println("Final concurrentMap: " + concurrentMap);
    }
}

/*

Output :
hashMap Iteration - Key: 1, Value: Value 1
Final hashMap: {1=Value 1, 2=Value 2, 3=Value 3, 4=Value 4, 5=Value 5}
concurrentMap Iteration - Key: 1, Value: Value 1
Final concurrentMap: {1=Value 1, 2=Value 2, 3=Value 3, 4=Value 4, 5=Value 5}


*/