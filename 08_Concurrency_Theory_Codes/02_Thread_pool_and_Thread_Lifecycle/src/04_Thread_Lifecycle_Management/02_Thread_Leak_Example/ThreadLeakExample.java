package ThreadLifecycleManagement.Thread_Leak_Example;

public class ThreadLeakExample {
    public static void main(String[] args) {
        SafeLock safeLock = new SafeLock();
        new Thread(safeLock::waitForSignal, "WorkerThread").start();
    }
}