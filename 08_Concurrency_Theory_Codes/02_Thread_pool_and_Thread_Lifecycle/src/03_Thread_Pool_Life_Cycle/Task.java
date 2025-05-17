package ThreadPoolLifeCycle;

public class Task implements Runnable {
    private final int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - STARTING Task " + taskId);
        try {
            // Simulating different thread states
            Thread.sleep(2000); // Simulates RUNNABLE -> TIMED_WAITING (Sleep)

            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " - WAITING on Task " + taskId);
                // The thread is now RUNNING and enters a synchronized block.
                this.wait(1000); // Simulates WAITING state for 1 second
                // The thread leaves the RUNNING state and enters the WAITING state.
            }
            // After wait() (either by timeout or notify), the thread becomes RUNNABLE again.
            // When the scheduler picks it, it re-enters the RUNNING state.
            System.out.println(Thread.currentThread().getName() + " - Task " + taskId + " COMPLETED");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // If interrupted while RUNNING, it might transition to TERMINATED or handle the interrupt and continue.
            // If interrupted while in TIMED_WAITING or WAITING, it will throw InterruptedException and become RUNNABLE.
        }
        // After the try-catch block, if the pool is still active, the thread will likely go back to the RUNNABLE state,
        // waiting for a new task. If the pool is shutting down, it will eventually move to TERMINATED.
    }
}
