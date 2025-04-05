package public_interface;
import model.Message;

public interface IProducer {
    String getId();

    void publish(String topicId, Message message) throws IllegalArgumentException;
}
