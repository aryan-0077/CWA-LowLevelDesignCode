package Executors;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class KeyBasedExecutor {
    private final ExecutorService[] executors;
    private final int numExecutors;

    public KeyBasedExecutor(int numExecutors) {
        this.numExecutors = numExecutors;
        this.executors = new ExecutorService[numExecutors];
        for (int i = 0; i < numExecutors; i++) {
            executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    /**
     * Dispatch a task for a given key so that all tasks for that key run on the same single-thread executor.
     */
    public <T> CompletableFuture<T> submitTask(Object key, Supplier<T> task) {
        int index = getExecutorIndexForKey(key);
        ExecutorService executor = executors[index];
        return CompletableFuture.supplyAsync(task, executor);
    }

    /**
     * Determines the executor index for a key using a basic mod hash.
     */
    public int getExecutorIndexForKey(Object key) {
        return Math.abs(key.hashCode() % numExecutors);
    }

    /**
     * Shuts down all executors.
     */
    public void shutdown() {
        for (ExecutorService executor : executors) {
            executor.shutdown();
        }
    }
}

