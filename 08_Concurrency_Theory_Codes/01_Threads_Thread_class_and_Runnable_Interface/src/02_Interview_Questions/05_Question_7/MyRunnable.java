package InterviewQuestions.Question_7;

import java.util.concurrent.Callable;

public class MyRunnable implements Runnable{
    private Callable<Integer> callable;
    public MyRunnable(Callable<Integer> callable) {
        this.callable = callable;
    }

    @Override
    public void run() {
        try {
            System.out.println(callable.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
