package InterviewQuestions.Question_5.WaitExample;

public class SharedResource {
    synchronized void waitExample() {
        System.out.println(Thread.currentThread().getName() + " is waiting...");
        try {
            wait(); // Releases the lock and waits
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " resumed after notify.");
    }


    synchronized void notifyExample() {
        System.out.println("Notifying a waiting thread...");
        notify(); // Wakes up one waiting thread
    }
}
