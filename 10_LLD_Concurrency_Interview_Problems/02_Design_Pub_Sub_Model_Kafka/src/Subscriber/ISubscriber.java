package Subscriber;
import model.Message;

public interface ISubscriber {
    String getId();
    void onMessage(Message message) throws InterruptedException;
}
