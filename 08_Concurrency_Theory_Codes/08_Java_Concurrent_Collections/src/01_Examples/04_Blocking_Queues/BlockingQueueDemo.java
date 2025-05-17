package Examples.BlockingQueues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        // A new thread will add an element after a 2-second delay.
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                queue.put(99); // put() will add element, unblocking take()
                System.out.println("Producer: Added 99");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        System.out.println("Consumer: Waiting to take an element...");

        // take() blocks until an element is available.
        Integer element = queue.take();
        System.out.println("Consumer: Took element " + element);
    }
}

/*

Output :
Consumer: Waiting to take an element...
Producer: Added 99
Consumer: Took element 99

*/