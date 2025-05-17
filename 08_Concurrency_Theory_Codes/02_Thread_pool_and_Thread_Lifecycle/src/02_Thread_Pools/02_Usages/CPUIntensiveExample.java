package ThreadPools.Usages;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CPUIntensiveExample {
    private static final int NUM_CORES = Runtime.getRuntime().availableProcessors(); // Get CPU core count
    public static void main(String[] args) {
        ExecutorService fixedPool = Executors.newFixedThreadPool(NUM_CORES); // Optimize for CPU-intensive tasks
        for (int i = 0; i < 10; i++) {
            fixedPool.execute(() -> {
                int result = performComputation();
                System.out.println(Thread.currentThread().getName() + " computed result: " + result);
            });
        }
        fixedPool.shutdown();
    }

    private static int performComputation() {
        int sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += Math.sqrt(i); // Simulating heavy computation
        }
        return sum;
    }
}
