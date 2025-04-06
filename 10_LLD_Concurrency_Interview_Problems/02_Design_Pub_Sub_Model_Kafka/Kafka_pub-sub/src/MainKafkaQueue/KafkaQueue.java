package MainKafkaQueue;
import handler.TopicHandler;
import model.Message;
import model.Topic;
import model.TopicSubscriber;
import public_interface.IConsumer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaQueue {

    private final Map<String, TopicHandler> topicProcessors; // Stores topic ID to TopicHandler mapping

    private final AtomicInteger topicIdCounter;              // Atomic counter for generating unique topic IDs


    // Constructor initializes the topic processors map and the ID counter

    public KafkaQueue() {

        this.topicProcessors = new ConcurrentHashMap<>();

        this.topicIdCounter = new AtomicInteger(0);

    }




    // Creates a new topic with a unique ID and registers it with a TopicHandler

    public Topic createTopic(final String topicName) {

        String topicId = String.valueOf(topicIdCounter.incrementAndGet()); // Generate next topic ID

        final Topic topic = new Topic(topicName, topicId);

        TopicHandler topicHandler = new TopicHandler(topic);

        topicProcessors.put(topic.getTopicId(), topicHandler);




        System.out.println("Created topic: " + topic.getTopicName());

        return topic;

    }




    // Adds a subscriber to the specified topic

    public void subscribe(final IConsumer consumer, final Topic topic) {

        topic.addSubscriber(new TopicSubscriber(consumer));

        System.out.println(consumer.getId() + " subscribed to topic: " + topic.getTopicName());

    }




    // Publishes a message to the topic and starts subscriber workers in a new thread

    public void publish(final Topic topic, final Message message) {

        topic.addMessage(message);

        System.out.println(message.getMessage() + " published to topic: " + topic.getTopicName());
        // Notify subscriber workers asynchronously
        new Thread(() -> topicProcessors.get(topic.getTopicId()).publish()).start();
    }

    // Resets the offset of a subscriber and wakes up its worker thread
    public void resetOffset(final Topic topic, final IConsumer consumer, final Integer newOffset) {
        for (TopicSubscriber topicSubscriber : topic.getSubscribers()) {

            if (topicSubscriber.getConsumer().equals(consumer)) {

                topicSubscriber.getOffset().set(newOffset);

                System.out.println(topicSubscriber.getConsumer().getId() + " offset reset to: " + newOffset);




                // Restart worker thread for the subscriber

                new Thread(() -> topicProcessors.get(topic.getTopicId()).startConsumerWorker(topicSubscriber)).start();

                break;

            }

        }

    }

    public Topic getTopicById(String topicId) {
        TopicHandler handler = topicProcessors.get(topicId);
        return handler != null ? handler.getTopic() : null;
    }
}