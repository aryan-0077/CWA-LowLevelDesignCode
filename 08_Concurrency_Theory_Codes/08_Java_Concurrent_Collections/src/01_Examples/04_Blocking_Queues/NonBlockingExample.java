package Examples.BlockingQueues;

import java.util.concurrent.ConcurrentLinkedQueue;

public class NonBlockingExample {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        // Non-blocking poll: returns immediately with null because the queue is empty.
        Integer element = queue.poll();
        System.out.println("Polled element: " + element); // Expected output: Polled element: null

        // Add an element; this operation is also non-blocking.
        queue.add(42);
        System.out.println("After adding: " + queue.poll()); // Expected output: After adding: 42
    }
}

/*

Polled element: null
After adding: 42

*/