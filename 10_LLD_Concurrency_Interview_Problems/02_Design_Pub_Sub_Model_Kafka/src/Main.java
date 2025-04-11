import Controller.KafkaController;
import model.Topic;
import model.Message;
import ConcreteSubscribers.SimpleSubscriber;
import ConcretePublisher.SimplePublisher;

public class Main {
    public static void main(String[] args) {
        KafkaController kafkaController = new KafkaController();

        // Create topics.
        Topic topic1 = kafkaController.createTopic("Topic1");
        Topic topic2 = kafkaController.createTopic("Topic2");

        // Create subscribers.
        SimpleSubscriber subscriber1 = new SimpleSubscriber("Subscriber1");
        SimpleSubscriber subscriber2 = new SimpleSubscriber("Subscriber2");
        SimpleSubscriber subscriber3 = new SimpleSubscriber("Subscriber3");

        // Subscribe: subscriber1 subscribes to both topics,
        // subscriber2 subscribes to topic1, and subscriber3 subscribes to topic2.
        kafkaController.subscribe(subscriber1, topic1.getTopicId());
        kafkaController.subscribe(subscriber1, topic2.getTopicId());
        kafkaController.subscribe(subscriber2, topic1.getTopicId());
        kafkaController.subscribe(subscriber3, topic2.getTopicId());

        // Create publishers.
        SimplePublisher publisher1 = new SimplePublisher("Publisher1", kafkaController);
        SimplePublisher publisher2 = new SimplePublisher("Publisher2", kafkaController);

        // Publish some messages.
        publisher1.publish(topic1.getTopicId(), new Message("Message m1"));
        publisher1.publish(topic1.getTopicId(), new Message("Message m2"));
        publisher2.publish(topic2.getTopicId(), new Message("Message m3"));

        // Allow time for subscribers to process messages.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        publisher2.publish(topic2.getTopicId(), new Message("Message m4"));
        publisher1.publish(topic1.getTopicId(), new Message("Message m5"));

        // Reset offset for subscriber1 on topic1 (for example, to re-process messages).
        kafkaController.resetOffset(topic1.getTopicId(), subscriber1, 0);

        // Allow some time before shutting down.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        kafkaController.shutdown();
    }
}
