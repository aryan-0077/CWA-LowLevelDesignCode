package ThreadSynchronizationMethods.VolatileKeyword;

public class VolatileExample {
    // Declaring the flag as volatile ensures that changes to 'running'
    // in one thread are immediately visible to other threads.
    private volatile boolean running = true;
    // Method executed by the worker thread.
    public void runTask() {
        System.out.println("WorkerThread: Starting execution...");
        int counter = 0;
        // Continuously increment counter until 'running' becomes false.
        while (running) {
            counter++;
        }
        System.out.println("WorkerThread: Detected stop signal. Final counter value: " + counter);
    }
    // Called by the main thread to stop the worker thread.
    public void stopTask() {
        running = false;
    }
    // Main method to run the example.
    public static void main(String[] args) {
        VolatileExample example = new VolatileExample();
        // Create and start the worker thread.
        Thread workerThread = new Thread(new Runnable() {
            public void run() {
                example.runTask();
            }
        }, "WorkerThread");
        workerThread.start();
        // Let the worker thread run for a while.
        try {
            Thread.sleep(2000);  // Main thread sleeps for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MainThread: Stopping the worker thread.");
        example.stopTask(); // Signal the worker thread to stop
        // Wait for the worker thread to finish execution.
        try {
            workerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MainThread: Execution finished.");
    }
}

/*

Output :
WorkerThread: Starting execution...
MainThread: Stopping the worker thread.
WorkerThread: Detected stop signal. Final counter value: -1488944943
MainThread: Execution finished.

*/
