package Examples.QueueExample;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueComparison {
    public static void main(String[] args) throws InterruptedException {
        // Demonstrate issues with LinkedList in a multi-threaded environment
        System.out.println("--- LinkedList Example (Not Thread-Safe) ---");

        Queue<Integer> linkedListQueue = new LinkedList<>();

        // Create producer and consumer threads with synchronization
        Thread producerLinkedList = new Thread(() -> {
            synchronized (linkedListQueue) {
                for (int i = 0; i < 1000; i++) {
                    linkedListQueue.offer(i);
                }
                System.out.println(Thread.currentThread().getName() + " finished adding 1000 items to LinkedList");
            }
        }, "LinkedList Producer");

        Thread consumerLinkedList = new Thread(() -> {
            int count = 0;
            while (count < 1000) {
                synchronized (linkedListQueue) {
                    Integer item = linkedListQueue.poll();
                    if (item != null) {
                        count++;
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + " processed 1000 items from LinkedList");
        }, "LinkedList Consumer");

        // Start both threads
        producerLinkedList.start();
        consumerLinkedList.start();

        // Wait for completion
        producerLinkedList.join();
        consumerLinkedList.join();

        System.out.println("LinkedList size after operations (should be 0): " + linkedListQueue.size());
        System.out.println("Note: LinkedList might have unexpected behavior or exceptions in concurrent scenarios ⚠️");
        System.out.println();

        // Demonstrate ConcurrentLinkedQueue in a multi-threaded environment
        System.out.println("--- ConcurrentLinkedQueue Example (Thread-Safe) ---");

        ConcurrentLinkedQueue<Integer> concurrentQueue = new ConcurrentLinkedQueue<>();

        // Multiple producer threads
        Thread producer1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                concurrentQueue.offer(i);
            }
            System.out.println("Producer 1 finished adding 500 items ✅");
        });

        Thread producer2 = new Thread(() -> {
            for (int i = 500; i < 1000; i++) {
                concurrentQueue.offer(i);
            }
            System.out.println("Producer 2 finished adding 500 items ✅");
        });

        // Multiple consumer threads
        Thread consumer1 = new Thread(() -> {
            int count = 0;
            while (count < 500) {
                Integer item = concurrentQueue.poll();
                if (item != null) {
                    count++;
                }
            }
            System.out.println("Consumer 1 processed 500 items ✅");
        });

        Thread consumer2 = new Thread(() -> {
            int count = 0;
            while (count < 500) {
                Integer item = concurrentQueue.poll();
                if (item != null) {
                    count++;
                }
            }
            System.out.println("Consumer 2 processed 500 items ✅");
        });

        // Start all threads
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        // Wait for completion
        producer1.join();
        producer2.join();
        consumer1.join();
        consumer2.join();

        System.out.println("ConcurrentLinkedQueue size after operations (should be 0): " + concurrentQueue.size());
    }
}

/*

Output :
--- LinkedList Example (Not Thread-Safe) ---
LinkedList Producer finished adding 1000 items to LinkedList
LinkedList Consumer processed 1000 items from LinkedList
LinkedList size after operations (should be 0): 0
Note: LinkedList might have unexpected behavior or exceptions in concurrent scenarios ⚠️

--- ConcurrentLinkedQueue Example (Thread-Safe) ---
Producer 2 finished adding 500 items ✅
Consumer 2 processed 500 items ✅
Producer 1 finished adding 500 items ✅
Consumer 1 processed 500 items ✅
ConcurrentLinkedQueue size after operations (should be 0): 0
 */