package RestaurantOrderSimulation;

public class ChefThread extends Thread {
    private final Object lock;

    public ChefThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000); // Simulate food preparation time
            synchronized (lock) {
                System.out.println("Chef: Food is ready! Notifying the waiter. 🔔");
                lock.notify(); // Wake up the waiting waiter thread
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
