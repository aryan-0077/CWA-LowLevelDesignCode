package ThreadLifecycleManagement.Thread_Interruption_Example;

public class WorkerThread implements Runnable{
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Checking for updates...");
                Thread.sleep(2000); // Simulating work
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted, shutting down gracefully.");
        }
    }
}
