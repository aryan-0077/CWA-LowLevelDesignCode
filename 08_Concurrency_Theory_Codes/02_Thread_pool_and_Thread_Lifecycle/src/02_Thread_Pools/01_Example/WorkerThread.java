package ThreadPools.Example;

public class WorkerThread implements Runnable {
    private final int taskId;

    public WorkerThread(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is processing task: " + taskId);
        try {
            Thread.sleep(2000); // Simulate task execution time
        } catch (InterruptedException e) {
            System.out.println("Task interrupted: " + e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " finished task: " + taskId);
    }
}
