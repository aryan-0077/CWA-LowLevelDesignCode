package InterviewQuestions.Question_2;

public class ThreadInterruption {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread: Going to sleep...");
                Thread.sleep(5000); // Sleep for 5 seconds
                System.out.println("Thread: Woke up!");
            } catch (InterruptedException e) {
                System.out.println("Thread: Interrupted!");
            }
        });
        thread.start();
        try {
            Thread.sleep(1000); // Main thread sleeps for 1 second
            thread.interrupt(); // Interrupt the sleeping thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*

Output :
Thread: Going to sleep...
Thread: Interrupted!

*/