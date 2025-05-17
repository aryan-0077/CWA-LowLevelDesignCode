package InterviewQuestions.Question_3;

import java.util.concurrent.*;

public class SemaphoreBarrierExecutorDemo {
    // A reusable barrier implemented with semaphores
    static class SemaphoreBarrier {
        private final int parties;
        private int count;
        private final Semaphore mutex = new Semaphore(1);
        private final Semaphore barrier = new Semaphore(0);
        public SemaphoreBarrier(int parties) {
            this.parties = parties;
            this.count = parties;
        }

        public void await() throws InterruptedException {
            mutex.acquire();
            count--;
            if (count == 0) {
                // Last thread arrives: release all waiting threads
                barrier.release(parties - 1);
                // Reset barrier state for reuse
                count = parties;
                mutex.release();
            } else {
                // Release mutex so other threads can update the count
                mutex.release();
                // Wait until the last thread releases this thread
                barrier.acquire();
            }
        }
    }

    public static void main(String[] args) {
        final int numThreads = 5;
        final SemaphoreBarrier barrier = new SemaphoreBarrier(numThreads);
        // Create a fixed thread pool with custom thread names
        ExecutorService executor = Executors.newFixedThreadPool(numThreads, new ThreadFactory() {
            private int counter = 1;
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "Worker-" + counter);
                counter++;
                return t;
            }
        });
        // Submit tasks to the executor
        for (int i = 0; i < numThreads; i++) {
            executor.submit(() -> {
                try {
                    // Phase 1: Some work before reaching the first barrier
                    System.out.println(Thread.currentThread().getName() + " doing phase 1 work");
                    Thread.sleep((long) (Math.random() * 1000)); // Simulate work
                    System.out.println(
                            Thread.currentThread().getName() + " arrived at barrier after phase 1");
                    barrier.await(); // Wait until all threads reach here
                    // Phase 2: This phase begins only after every thread has finished phase 1
                    System.out.println(Thread.currentThread().getName() + " starting phase 2");
                    Thread.sleep((long) (Math.random() * 1000)); // Simulate work
                    System.out.println(Thread.currentThread().getName() + " finished phase 2");
                    barrier.await(); // Synchronize end of phase 2
                    // Phase 3: The final phase starts after all threads have completed phase 2
                    System.out.println(Thread.currentThread().getName() + " starting phase 3");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " was interrupted");
                }
            });
        }
        // Initiate an orderly shutdown
        executor.shutdown();
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                System.out.println("Some tasks did not finish in time");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
            executor.shutdownNow();
        }
        System.out.println("All tasks completed");
    }
}

/*

Output :
Worker-4 doing phase 1 work
Worker-5 doing phase 1 work
Worker-3 doing phase 1 work
Worker-2 doing phase 1 work
Worker-1 doing phase 1 work
Worker-4 arrived at barrier after phase 1
Worker-2 arrived at barrier after phase 1
Worker-5 arrived at barrier after phase 1
Worker-1 arrived at barrier after phase 1
Worker-3 arrived at barrier after phase 1
Worker-3 starting phase 2
Worker-4 starting phase 2
Worker-2 starting phase 2
Worker-1 starting phase 2
Worker-5 starting phase 2
Worker-2 finished phase 2
Worker-1 finished phase 2
Worker-3 finished phase 2
Worker-4 finished phase 2
Worker-5 finished phase 2
Worker-2 starting phase 3
Worker-1 starting phase 3
Worker-4 starting phase 3
Worker-3 starting phase 3
Worker-5 starting phase 3
All tasks completed

*/
