package Controller;

import model.Topic;
import Publisher.IPublisher;
import model.Message;

public class TopicPublisherController {
    private final Topic topic;
    private final IPublisher publisher;

    public TopicPublisherController(Topic topic, IPublisher publisher) {
        this.topic = topic;
        this.publisher = publisher;
    }

    // Synchronized publish method ensures thread-safe publishing for this topic.
    public synchronized void publish(Message message, KafkaController controller) {
        controller.publish(publisher, topic.getTopicId(), message);
        System.out.println("Publisher " + publisher.getId() + " published to topic " + topic.getTopicName());
    }
}
