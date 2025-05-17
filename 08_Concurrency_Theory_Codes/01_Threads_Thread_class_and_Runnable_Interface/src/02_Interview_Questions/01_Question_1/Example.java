package InterviewQuestions.Question_1;

public class Example {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();  // Starts a new thread


        MyThread t2 = new MyThread();
        t2.run();   // Runs in the main thread
    }
}
