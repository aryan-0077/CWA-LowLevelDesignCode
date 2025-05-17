package InterviewQuestions.Question_2;

public class TestThread {
    public void run() {
        System.out.println("Thread is running...");
    }

    private void start() {
    }

    public static void main(String[] args) {
        TestThread t = new TestThread();
        t.start(); // Works fine
        t.start(); // Throws IllegalThreadStateException
    }
}
