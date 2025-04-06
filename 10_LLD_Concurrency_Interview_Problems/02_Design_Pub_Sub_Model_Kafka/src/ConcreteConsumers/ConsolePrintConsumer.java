package ConcreteConsumers;

import model.Message;
import public_interface.IConsumer;

public class ConsolePrintConsumer implements IConsumer {

    private final String id;              // Unique ID for the consumer
    private final String message;       // Dummy message for printing to the console

    // Constructor to initialize consumer ID and processing delay
    public ConsolePrintConsumer(String id, String message) {
        this.id = id;
        this.message = message;

    }
    @Override
    public String getId() {
        return id;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        // Log start of message consumption
        System.out.println("Consumer: " + id + " started consuming: " + message.getMessage());

        // Simulate processing time
        System.out.println("This Consumer prints the given message to the Console : ");
        System.out.print(message);

        // Log completion of message consumption
        System.out.println("Consumer: " + id + " done consuming: " + message.getMessage());
    }
}
