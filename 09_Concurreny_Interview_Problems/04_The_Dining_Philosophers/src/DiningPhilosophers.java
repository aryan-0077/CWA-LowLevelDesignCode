import java.util.concurrent.Semaphore;

class DiningPhilosophers {
    // Semaphore to limit the number of philosophers trying to eat simultaneously
    private Semaphore semaphore;


    // Array of semaphores to control access to each fork
    private Semaphore[] forkSemaphore;


    public DiningPhilosophers() {
        // Allow at most 4 philosophers to try to eat at the same time
        semaphore = new Semaphore(4);


        // Initialize semaphores for each fork
        forkSemaphore = new Semaphore[5];
        for (int i = 0; i < 5; i++) forkSemaphore[i] = new Semaphore(1);
    }


    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork,
                           Runnable eat, Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
        // Limit the number of philosophers who can try to eat simultaneously
        semaphore.acquire();


        // Identify the forks needed by this philosopher
        int left = philosopher;
        int right = (philosopher + 1) % 5;


        // Get semaphores for the left and right forks
        Semaphore leftForkSemaphore = forkSemaphore[left];
        Semaphore rightForkSemaphore = forkSemaphore[right];


        // Acquire both forks (resources)
        leftForkSemaphore.acquire();
        rightForkSemaphore.acquire();


        // Perform the eating process
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();


        // Put down the forks
        putLeftFork.run();
        leftForkSemaphore.release();
        putRightFork.run();
        rightForkSemaphore.release();


        // Allow another philosopher to try to eat
        semaphore.release();
    }
}
