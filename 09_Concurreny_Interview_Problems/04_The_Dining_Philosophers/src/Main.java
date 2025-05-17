import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Create the DiningPhilosophers instance
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();

        // Create a thread pool to run the philosopher threads
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Create and start 5 philosopher threads
        for (int philosopher = 0; philosopher < 5; philosopher++) {
            final int id = philosopher;
            executorService.submit(() -> {
                try {
                    diningPhilosophers.wantsToEat(
                            id,
                            () -> System.out.println("Philosopher " + id + " picked up left fork (" + Thread.currentThread().getName() + ")"),
                            () -> System.out.println("Philosopher " + id + " picked up right fork (" + Thread.currentThread().getName() + ")"),
                            () -> {
                                System.out.println("Philosopher " + id + " is eating (" + Thread.currentThread().getName() + ")");
                                try {
                                    Thread.sleep(1000); // Simulate eating time
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            },
                            () -> System.out.println("Philosopher " + id + " put down left fork (" + Thread.currentThread().getName() + ")"),
                            () -> System.out.println("Philosopher " + id + " put down right fork (" + Thread.currentThread().getName() + ")")
                    );
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Shutdown the executor service after all philosophers are done
        executorService.shutdown();
    }
}

/*

Output :

Philosopher 0 picked up left fork (pool-1-thread-1)
Philosopher 0 picked up right fork (pool-1-thread-1)
Philosopher 0 is eating (pool-1-thread-1)
Philosopher 0 put down left fork (pool-1-thread-1)
Philosopher 4 picked up left fork (pool-1-thread-5)
Philosopher 4 picked up right fork (pool-1-thread-5)
Philosopher 4 is eating (pool-1-thread-5)
Philosopher 0 put down right fork (pool-1-thread-1)
Philosopher 1 picked up left fork (pool-1-thread-2)
Philosopher 1 picked up right fork (pool-1-thread-2)
Philosopher 1 is eating (pool-1-thread-2)
Philosopher 1 put down left fork (pool-1-thread-2)
Philosopher 1 put down right fork (pool-1-thread-2)
Philosopher 4 put down left fork (pool-1-thread-5)
Philosopher 4 put down right fork (pool-1-thread-5)
Philosopher 3 picked up left fork (pool-1-thread-4)
Philosopher 3 picked up right fork (pool-1-thread-4)
Philosopher 3 is eating (pool-1-thread-4)
Philosopher 3 put down left fork (pool-1-thread-4)
Philosopher 2 picked up left fork (pool-1-thread-3)
Philosopher 2 picked up right fork (pool-1-thread-3)
Philosopher 2 is eating (pool-1-thread-3)
Philosopher 3 put down right fork (pool-1-thread-4)
Philosopher 2 put down left fork (pool-1-thread-3)
Philosopher 2 put down right fork (pool-1-thread-3)

*/