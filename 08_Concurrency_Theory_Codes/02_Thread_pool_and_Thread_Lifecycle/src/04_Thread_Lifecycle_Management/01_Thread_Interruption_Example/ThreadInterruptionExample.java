package ThreadLifecycleManagement.Thread_Interruption_Example;

public class ThreadInterruptionExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WorkerThread());
        thread.start();
        Thread.sleep(5000); // Let it run for some time
        thread.interrupt(); // Interrupt the thread
    }
}
