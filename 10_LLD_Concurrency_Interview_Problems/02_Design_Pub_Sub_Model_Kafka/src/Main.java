import Controller.KafkaController;
import model.Topic;
import model.Message;
import Subscriber.ConcreteSubscriber.SimpleSubscriber;
import Publisher.ConcretePublisher.SimplePublisher;

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


/*
Output :

Created topic: Topic1 with id: 1
Created topic: Topic2 with id: 2
Subscriber Subscriber1 subscribed to topic: Topic1
Subscriber Subscriber1 subscribed to topic: Topic2
Subscriber Subscriber2 subscribed to topic: Topic1
Subscriber Subscriber3 subscribed to topic: Topic2
Subscriber Subscriber2 received: Message m1
Subscriber Subscriber1 received: Message m1
Message "Message m1" published to topic: Topic1
Publisher Publisher1 published: Message m1 to topic 1
Message "Message m2" published to topic: Topic1
Publisher Publisher1 published: Message m2 to topic 1
Message "Message m3" published to topic: Topic2
Publisher Publisher2 published: Message m3 to topic 2
Subscriber Subscriber1 received: Message m3
Subscriber Subscriber3 received: Message m3
Subscriber Subscriber1 received: Message m2
Subscriber Subscriber2 received: Message m2
Message "Message m4" published to topic: Topic2
Subscriber Subscriber1 received: Message m4
Subscriber Subscriber3 received: Message m4
Publisher Publisher2 published: Message m4 to topic 2
Message "Message m5" published to topic: Topic1
Publisher Publisher1 published: Message m5 to topic 1
Subscriber Subscriber2 received: Message m5
Subscriber Subscriber1 received: Message m5
Offset for subscriber Subscriber1 on topic Topic1 reset to 0
Subscriber Subscriber1 received: Message m1
Subscriber Subscriber1 received: Message m2
Subscriber Subscriber1 received: Message m5

 */