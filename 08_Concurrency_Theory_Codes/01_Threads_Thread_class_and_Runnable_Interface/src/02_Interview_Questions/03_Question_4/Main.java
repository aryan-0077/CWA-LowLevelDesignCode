package InterviewQuestions.Question_4;

public class Main {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start(); // Starts a separate thread
        System.out.println("Main thread is running");
    }
}
