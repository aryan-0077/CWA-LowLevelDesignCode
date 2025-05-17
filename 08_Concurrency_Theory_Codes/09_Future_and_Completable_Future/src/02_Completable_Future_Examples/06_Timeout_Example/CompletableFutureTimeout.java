package CompletableFutureExamples.TimeoutExample;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTimeout {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000); // Simulate a long-running task
                return "Task completed";
            } catch (InterruptedException e) {
                return "Task interrupted";
            }
        });

        {/* Apply timeout
        - orTimeout(long timeout, TimeUnit unit) - This adds a timeout to the CompletableFuture
        Arguments:
           - timeout: 2 - The duration of the timeout
           - unit: TimeUnit.SECONDS - The time unit for the timeout value
         What happens after timeout:
           - If the original future doesn't complete within 2 seconds, this CompletableFuture
             will complete exceptionally with a TimeoutException
           - The original future continues running in the background despite the timeout
        */}
        CompletableFuture<String> withTimeout = future.orTimeout(2, TimeUnit.SECONDS);

        try {
            System.out.println(withTimeout.join());
        } catch (CompletionException e) {
            System.out.println("Timeout occurred: " + e.getCause().getClass().getSimpleName());
        }

        {/*Provide a default value on timeout
        completeOnTimeout(T value, long timeout, TimeUnit unit) - This adds a timeout with a default value
        Arguments:
           - value: "Default value" - The value to use if the timeout occurs
           - timeout: 2 - The duration of the timeout
           - unit: TimeUnit.SECONDS - The time unit for the timeout value
        What happens after timeout:
           - If the original future doesn't complete within 2 seconds, this CompletableFuture
        will complete normally with the provided default value
           - The original future continues running in the background despite the timeout
       - If the original future completes before the timeout, its result is used instead
        */}
        CompletableFuture<String> withDefault = future.completeOnTimeout("Default value", 2, TimeUnit.SECONDS);
        System.out.println("With default: " + withDefault.join());
    }
}

/*

Output :

Timeout occurred: TimeoutException
Exception in thread "main" java.util.concurrent.CompletionException: java.util.concurrent.TimeoutException
	at java.base/java.util.concurrent.CompletableFuture.reportJoin(CompletableFuture.java:413)
	at java.base/java.util.concurrent.CompletableFuture.join(CompletableFuture.java:2118)
	at CompletableFutureExamples.TimeoutExample.CompletableFutureTimeout.main(CompletableFutureTimeout.java:49)
Caused by: java.util.concurrent.TimeoutException
	at java.base/java.util.concurrent.CompletableFuture$Timeout.run(CompletableFuture.java:2920)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:577)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:317)
	at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	at java.base/java.lang.Thread.run(Thread.java:1623)


 */