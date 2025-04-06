package ConcreteConsumers;

import model.Message;
import public_interface.IConsumer;

public class SleepingConsumer implements IConsumer {

    private final String id;                   // Unique ID for the consumer

    private final int sleepTimeInMillis;       // Simulated delay for processing each message



    // Constructor to initialize consumer ID and processing delay

    public SleepingConsumer(String id, int sleepTimeInMillis) {

        this.id = id;

        this.sleepTimeInMillis = sleepTimeInMillis;

    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        // Log start of message consumption
        System.out.println("Consumer: " + id + " started consuming: " + message.getMessage());


        // Simulate processing time
        Thread.sleep(sleepTimeInMillis);


        // Log completion of message consumption
        System.out.println("Consumer: " + id + " done consuming: " + message.getMessage());
    }
}
