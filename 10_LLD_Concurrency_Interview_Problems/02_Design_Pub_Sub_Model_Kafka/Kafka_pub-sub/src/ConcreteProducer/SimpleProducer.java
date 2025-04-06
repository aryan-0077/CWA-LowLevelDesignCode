package ConcreteProducer;

import MainKafkaQueue.KafkaQueue;
import model.Message;
import model.Topic;
import public_interface.IProducer;

public class SimpleProducer implements IProducer {
    private final String id;
    private final KafkaQueue kafkaQueue;  // Reference to the queue for publishing

    public SimpleProducer(String id, KafkaQueue kafkaQueue) {
        this.id = id;
        this.kafkaQueue = kafkaQueue;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void publish(String topicId, Message message) throws IllegalArgumentException {
        // Find the topic with the given ID
        Topic topic = kafkaQueue.getTopicById(topicId);
        if (topic == null) {
            throw new IllegalArgumentException("Topic with ID " + topicId + " does not exist");
        }

        // Use the KafkaQueue to publish the message
        kafkaQueue.publish(topic, message);
        System.out.println("Producer " + id + " published message: " + message.getMessage() + " to topic: " + topic.getTopicName());
    }
}
