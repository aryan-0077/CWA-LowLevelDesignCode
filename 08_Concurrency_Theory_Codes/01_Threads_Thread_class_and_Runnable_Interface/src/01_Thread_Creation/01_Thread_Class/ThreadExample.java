package ThreadCreation.ThreadClass;

public class ThreadExample {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread(); // Create thread instance
        MyThread thread2 = new MyThread(); // Create another thread instance

        thread1.start(); // Start the first thread
        thread2.start(); // Start the second thread
    }
}

/*

Output :
Thread id: 11 is running: 0
Thread id: 12 is running: 0
Thread id: 11 is running: 1
Thread id: 12 is running: 1
Thread id: 11 is running: 2
Thread id: 12 is running: 2
Thread id: 11 is running: 3
Thread id: 12 is running: 3
Thread id: 11 is running: 4
Thread id: 12 is running: 4

*/