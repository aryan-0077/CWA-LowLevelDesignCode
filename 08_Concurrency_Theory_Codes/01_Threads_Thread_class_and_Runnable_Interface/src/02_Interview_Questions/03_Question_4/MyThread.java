package InterviewQuestions.Question_4;

public class MyThread extends Thread {
    public void run() {
        try {
            throw new RuntimeException("Exception in thread");
        } catch (Exception e) {
            System.out.println("Caught exception in thread: " + e.getMessage());
        }
    }
}
