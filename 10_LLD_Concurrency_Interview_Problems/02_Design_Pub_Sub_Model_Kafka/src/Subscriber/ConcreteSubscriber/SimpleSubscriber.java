package Subscriber.ConcreteSubscriber;

import Subscriber.ISubscriber;
import model.Message;

public class SimpleSubscriber implements ISubscriber {
    private final String id;
    public SimpleSubscriber(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void onMessage(Message message) throws InterruptedException {
        // Processing the received message.
        System.out.println("Subscriber " + id + " received: " + message.getMessage());
        // Simulate processing delay if desired
        Thread.sleep(500);
    }
}
