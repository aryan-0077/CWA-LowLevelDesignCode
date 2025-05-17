package InterviewQuestions.Question_4;

public class MyThread extends Thread{
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
            }
        }
}
