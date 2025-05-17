package ThreadCreation.RunnableInterface;

public class RunnableExample {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable(); // Create runnable instance

        Thread thread1 = new Thread(runnable); // Create thread with runnable
        Thread thread2 = new Thread(runnable); // Create another thread with same runnable

        thread1.start(); // Start the first thread
        thread2.start(); // Start the second thread
    }
}

/*

Output :

Runnable id: 11 is running: 0
Runnable id: 12 is running: 0
Runnable id: 11 is running: 1
Runnable id: 12 is running: 1
Runnable id: 11 is running: 2
Runnable id: 12 is running: 2
Runnable id: 11 is running: 3
Runnable id: 12 is running: 3
Runnable id: 11 is running: 4
Runnable id: 12 is running: 4

*/
