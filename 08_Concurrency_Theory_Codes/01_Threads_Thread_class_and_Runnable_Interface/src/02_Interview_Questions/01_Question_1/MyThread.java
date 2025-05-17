package InterviewQuestions.Question_1;

public class MyThread extends Thread{
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}
