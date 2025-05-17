package InterviewQuestions.Question_2;

import java.util.concurrent.Semaphore;

public class SemaphoreReleaseExample {
    private final Semaphore semaphore = new Semaphore(2); // Initially allows 2 threads
    public void accessResource() {
        try {
            semaphore.acquire(); // Acquire a permit (there may be up to 3 available after the extra release)
            System.out.println(Thread.currentThread().getName() + " acquired semaphore");
            Thread.sleep(1000); // Simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released semaphore");
            semaphore.release(); // Release the permit
        }
    }

    public static void main(String[] args) {
        SemaphoreReleaseExample example = new SemaphoreReleaseExample();
        // Intentionally release a permit without acquiring one
        // This increases the available permit count from 2 to 3.
        example.semaphore.release();
        System.out.println("Permit count after extra release: " + example.semaphore.availablePermits());
        // Start multiple threads with descriptive names to use the semaphore.
        for (int i = 1; i <= 3; i++) {
            new Thread(example::accessResource, "SemaphoreThread-" + i).start();
        }
    }
}

/*

Output :
Permit count after extra release: 3
SemaphoreThread-1 acquired semaphore
SemaphoreThread-3 acquired semaphore
SemaphoreThread-2 acquired semaphore
SemaphoreThread-2 released semaphore
SemaphoreThread-1 released semaphore
SemaphoreThread-3 released semaphore

*/