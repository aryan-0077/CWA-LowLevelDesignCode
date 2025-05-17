package InterviewQuestions.Question_1;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    // Shared buffer and its capacity
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int CAPACITY = 5;

    // Method for the producer thread that adds items to the buffer.
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                // Wait while the buffer is full.
                while (buffer.size() == CAPACITY) {
                    System.out.println("Buffer is full. Producer is waiting...");
                    wait();
                }
                // Once there is space, produce an item.
                System.out.println("Producer produced: " + value);
                buffer.offer(value++);
                // Notify all waiting threads (consumers) that a new item is available.
                notifyAll();
            }
            // Sleep for a short time to simulate production time.
            Thread.sleep(1000);
        }
    }

    // Method for the consumer thread that takes items from the buffer.
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // Wait while the buffer is empty.
                while (buffer.isEmpty()) {
                    System.out.println("Buffer is empty. Consumer is waiting...");
                    wait();
                }
                // Once there is an item, consume it.
                int value = buffer.poll();
                System.out.println("Consumer consumed: " + value);
                // Notify all waiting threads (producers) that space is available.
                notifyAll();
            }
            // Sleep for a short time to simulate consumption time.
            Thread.sleep(1500);
        }
    }

    // Main method to run the producer-consumer example.
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        // Creating the producer thread.
        Thread producerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Producer thread interrupted.");
                }
            }
        }, "ProducerThread");

        // Creating the consumer thread.
        Thread consumerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Consumer thread interrupted.");
                }
            }
        }, "ConsumerThread");

        // Start both threads.
        producerThread.start();
        consumerThread.start();
    }
}

/*

Output :
Buffer is empty. Consumer is waiting...
Producer produced: 0
Consumer consumed: 0
Producer produced: 1
Consumer consumed: 1
Producer produced: 2
Consumer consumed: 2
Producer produced: 3
Producer produced: 4
Consumer consumed: 3
Producer produced: 5
Producer produced: 6
Consumer consumed: 4
Producer produced: 7
Consumer consumed: 5
Producer produced: 8
Consumer consumed: 6
Producer produced: 9
Producer produced: 10
Consumer consumed: 7
Producer produced: 11
Producer produced: 12
Consumer consumed: 8
Producer produced: 13
Consumer consumed: 9
Producer produced: 14
Consumer consumed: 10
Producer produced: 15
Buffer is full. Producer is waiting...
Consumer consumed: 11
Producer produced: 16
Buffer is full. Producer is waiting...
Consumer consumed: 12
Producer produced: 17
Buffer is full. Producer is waiting...
Consumer consumed: 13
Producer produced: 18
Buffer is full. Producer is waiting...
Consumer consumed: 14
Producer produced: 19
Buffer is full. Producer is waiting...
Consumer consumed: 15
Producer produced: 20
Buffer is full. Producer is waiting...
Consumer consumed: 16
Producer produced: 21
Buffer is full. Producer is waiting...
Consumer consumed: 17
Producer produced: 22
Buffer is full. Producer is waiting...
Consumer consumed: 18
Producer produced: 23
Buffer is full. Producer is waiting...
Consumer consumed: 19
Producer produced: 24
Buffer is full. Producer is waiting...
Consumer consumed: 20
Producer produced: 25
Buffer is full. Producer is waiting...
Consumer consumed: 21
Producer produced: 26
Buffer is full. Producer is waiting...
Consumer consumed: 22
Producer produced: 27
Buffer is full. Producer is waiting...
Consumer consumed: 23
Producer produced: 28
Buffer is full. Producer is waiting...
Consumer consumed: 24
Producer produced: 29
Buffer is full. Producer is waiting...
Consumer consumed: 25
Producer produced: 30
Buffer is full. Producer is waiting...
Consumer consumed: 26
Producer produced: 31
Buffer is full. Producer is waiting...
Consumer consumed: 27
Producer produced: 32
Buffer is full. Producer is waiting...
Consumer consumed: 28
Producer produced: 33
Buffer is full. Producer is waiting...
Consumer consumed: 29
Producer produced: 34
Buffer is full. Producer is waiting...
Consumer consumed: 30
Producer produced: 35
Buffer is full. Producer is waiting...
Consumer consumed: 31
Producer produced: 36
Buffer is full. Producer is waiting...
Consumer consumed: 32
Producer produced: 37
Buffer is full. Producer is waiting...
Consumer consumed: 33
Producer produced: 38
Buffer is full. Producer is waiting...
Consumer consumed: 34
Producer produced: 39
Buffer is full. Producer is waiting...
Consumer consumed: 35
Producer produced: 40
Buffer is full. Producer is waiting...
Consumer consumed: 36
Producer produced: 41
Buffer is full. Producer is waiting...
Consumer consumed: 37
Producer produced: 42
Buffer is full. Producer is waiting...
Consumer consumed: 38
Producer produced: 43
Buffer is full. Producer is waiting...
Consumer consumed: 39
Producer produced: 44
Buffer is full. Producer is waiting...
Consumer consumed: 40
Producer produced: 45
Buffer is full. Producer is waiting...
Consumer consumed: 41
Producer produced: 46

*/