import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    // Number up to which FizzBuzz needs to be calculated
    private int n;

    // Semaphore to allow only the correct thread to print numbers
    private Semaphore numberSemaphore;

    // Semaphore to allow the "Fizz" thread to print
    private Semaphore fizzSemaphore;

    // Semaphore to allow the "Buzz" thread to print
    private Semaphore buzzSemaphore;

    // Semaphore to allow the "FizzBuzz" thread to print
    private Semaphore fizzBuzzSemaphore;

    // Constructor to initialize semaphores and set the limit
    public FizzBuzz(int n) {
        this.n = n;

        // Initially allow printing of numbers
        numberSemaphore = new Semaphore(1);

        // Block "Fizz", "Buzz", and "FizzBuzz" threads initially
        fizzSemaphore = new Semaphore(0);
        buzzSemaphore = new Semaphore(0);
        fizzBuzzSemaphore = new Semaphore(0);
    }

    // Method to print "Fizz" if the number is divisible by 3 but not by 5
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if(i % 3 == 0 && i % 5 != 0) {
                // Wait for permission to print "Fizz"
                fizzSemaphore.acquire();

                // Execute the provided function to print "Fizz"
                printFizz.run();

                // Signal the next thread to proceed
                numberSemaphore.release();
            }
        }
    }

    // Method to print "Buzz" if the number is divisible by 5 but not by 3
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if(i % 3 != 0 && i % 5 == 0) {
                // Wait for permission to print "Buzz"
                buzzSemaphore.acquire();

                // Execute the provided function to print "Buzz"
                printBuzz.run();

                // Signal the next thread to proceed
                numberSemaphore.release();
            }
        }
    }

    // Method to print "FizzBuzz" if the number is divisible by both 3 and 5
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if(i % 3 == 0 && i % 5 == 0) {
                // Wait for permission to print "FizzBuzz"
                fizzBuzzSemaphore.acquire();

                // Execute the provided function to print "FizzBuzz"
                printFizzBuzz.run();

                // Signal the next thread to proceed
                numberSemaphore.release();
            }
        }
    }

    // Method to print numbers if they are not divisible by 3 or 5
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            // Wait for permission to print a number
            numberSemaphore.acquire();

            // Check divisibility and signal the appropriate thread
            if(i % 3 == 0 && i % 5 == 0) {
                fizzBuzzSemaphore.release();
            } else if(i % 3 == 0) {
                fizzSemaphore.release();
            } else if(i % 5 == 0) {
                buzzSemaphore.release();
            } else {
                // Execute the provided function to print the number
                printNumber.accept(i);
                // Allow the next thread to proceed
                numberSemaphore.release();
            }
        }
    }
}