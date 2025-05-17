package ThreadSynchronizationMethods.AtomicVariables;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterExample {
    // The AtomicInteger counter provides atomic methods for thread-safe operations.
    private AtomicInteger counter = new AtomicInteger(0);

    // This method atomically increments the counter and prints the updated value.
    public void increment() {
        int newValue = counter.incrementAndGet(); // Atomically increments the value.
        System.out.println(Thread.currentThread().getName() + " incremented counter to " + newValue);
    }

    // Retrieves the current counter value.
    public int getCounter() {
        return counter.get();
    }

    // Main method to run the AtomicCounterExample.
    public static void main(String[] args) {
        final AtomicCounterExample example = new AtomicCounterExample();
        int numberOfThreads = 10;
        // Each thread will perform 100 increments.
        int incrementsPerThread = 10;
        Thread[] threads = new Thread[numberOfThreads];
        // Create and start threads that perform increments on the atomic counter.
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < incrementsPerThread; j++) {
                        example.increment();
                    }
                }
            }, "Thread-" + (i + 1));
            threads[i].start();
        }
        // Wait for all threads to complete execution.
        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Display the final counter value.
        System.out.println("Final counter value: " + example.getCounter());
    }
}

/*
Output :
Thread-10 incremented counter to 8
Thread-10 incremented counter to 11
Thread-10 incremented counter to 12
Thread-10 incremented counter to 13
Thread-10 incremented counter to 14
Thread-10 incremented counter to 15
Thread-10 incremented counter to 16
Thread-10 incremented counter to 17
Thread-4 incremented counter to 4
Thread-5 incremented counter to 5
Thread-1 incremented counter to 1
Thread-8 incremented counter to 10
Thread-8 incremented counter to 22
Thread-8 incremented counter to 23
Thread-8 incremented counter to 24
Thread-3 incremented counter to 3
Thread-2 incremented counter to 2
Thread-7 incremented counter to 9
Thread-6 incremented counter to 6
Thread-6 incremented counter to 29
Thread-9 incremented counter to 7
Thread-10 incremented counter to 18
Thread-4 incremented counter to 19
Thread-5 incremented counter to 20
Thread-1 incremented counter to 21
Thread-8 incremented counter to 25
Thread-8 incremented counter to 36
Thread-3 incremented counter to 26
Thread-3 incremented counter to 38
Thread-2 incremented counter to 27
Thread-7 incremented counter to 28
Thread-6 incremented counter to 30
Thread-9 incremented counter to 31
Thread-9 incremented counter to 43
Thread-9 incremented counter to 44
Thread-10 incremented counter to 32
Thread-4 incremented counter to 33
Thread-5 incremented counter to 34
Thread-1 incremented counter to 35
Thread-1 incremented counter to 48
Thread-8 incremented counter to 37
Thread-3 incremented counter to 39
Thread-2 incremented counter to 40
Thread-7 incremented counter to 41
Thread-6 incremented counter to 42
Thread-9 incremented counter to 45
Thread-9 incremented counter to 55
Thread-4 incremented counter to 46
Thread-5 incremented counter to 47
Thread-1 incremented counter to 49
Thread-8 incremented counter to 50
Thread-3 incremented counter to 51
Thread-2 incremented counter to 52
Thread-7 incremented counter to 53
Thread-6 incremented counter to 54
Thread-9 incremented counter to 56
Thread-4 incremented counter to 57
Thread-5 incremented counter to 58
Thread-1 incremented counter to 59
Thread-8 incremented counter to 60
Thread-3 incremented counter to 61
Thread-2 incremented counter to 62
Thread-7 incremented counter to 63
Thread-6 incremented counter to 64
Thread-9 incremented counter to 65
Thread-4 incremented counter to 66
Thread-5 incremented counter to 67
Thread-1 incremented counter to 68
Thread-8 incremented counter to 69
Thread-3 incremented counter to 70
Thread-2 incremented counter to 71
Thread-7 incremented counter to 72
Thread-6 incremented counter to 73
Thread-6 incremented counter to 81
Thread-6 incremented counter to 82
Thread-9 incremented counter to 74
Thread-4 incremented counter to 75
Thread-5 incremented counter to 76
Thread-1 incremented counter to 77
Thread-1 incremented counter to 87
Thread-3 incremented counter to 78
Thread-2 incremented counter to 79
Thread-7 incremented counter to 80
Thread-6 incremented counter to 83
Thread-9 incremented counter to 84
Thread-4 incremented counter to 85
Thread-4 incremented counter to 92
Thread-4 incremented counter to 93
Thread-5 incremented counter to 86
Thread-1 incremented counter to 88
Thread-3 incremented counter to 89
Thread-2 incremented counter to 90
Thread-7 incremented counter to 91
Thread-5 incremented counter to 94
Thread-3 incremented counter to 95
Thread-2 incremented counter to 96
Thread-7 incremented counter to 97
Thread-5 incremented counter to 98
Thread-2 incremented counter to 99
Thread-7 incremented counter to 100
Final counter value: 100

Process finished with exit code 0

*/