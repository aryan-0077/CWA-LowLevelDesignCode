package WaitNotifyExample;

public class WaitNotifyDemo {
    // Lock object used for synchronization
    private final Object lock = new Object();
    // Condition flag that threads check to decide whether to continue
    private boolean conditionMet = false;
    // Method where threads wait until conditionMet is true.
    public void doWait() {
        synchronized (lock) {
            while (!conditionMet) {  // Loop to avoid spurious wakeups
                try {
                    System.out.println(Thread.currentThread().getName() + " is waiting.");
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " was interrupted.");
                }
            }
            System.out.println(Thread.currentThread().getName() + " resumed execution.");
        }
    }

    // Sets the condition to true and calls notify() so that one waiting thread is awakened.
    public void doNotify() {
        synchronized (lock) {
            conditionMet = true;
            System.out.println(Thread.currentThread().getName() + " called notify().");
            lock.notify();  // Wakes up one waiting thread (if any)
        }
    }

    // Sets the condition to true and calls notifyAll() so that all waiting threads are awakened.
    public void doNotifyAll() {
        synchronized (lock) {
            conditionMet = true;
            System.out.println(Thread.currentThread().getName() + " called notifyAll().");
            lock.notifyAll();  // Wakes up all waiting threads
        }
    }
}
