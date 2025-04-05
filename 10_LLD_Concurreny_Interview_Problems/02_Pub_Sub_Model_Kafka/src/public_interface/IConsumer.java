package public_interface;
import model.Message;

public interface IConsumer {
    String getId();
    void consume(Message message) throws InterruptedException;
}
