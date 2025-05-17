package InterviewQuestions.Question_5.SleepExample;

public class SleepExample {
    public static void main(String[] args) {
        System.out.println("Thread is going to sleep...");
        try {
            Thread.sleep(2000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread woke up after sleeping.");
    }
}

/*

Output :
Thread is going to sleep...
(Thread pauses for 2 seconds)
Thread woke up after sleeping.

*/
