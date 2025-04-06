package handler;

import model.Topic;
import model.TopicSubscriber;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TopicHandler {

    private final Topic topic;
    private final Map<String, ConsumerHandler> consumerHandlers;

    // Constructor initializes the topic and the thread-safe map for managing subscriber workers
    public TopicHandler(final Topic topic) {
        this.topic = topic;
        consumerHandlers = new ConcurrentHashMap<>();
    }

    // Publish method starts a worker thread for each subscriber of this topic
    public void publish() {

        for (TopicSubscriber topicSubscriber : topic.getSubscribers()) {

            startConsumerWorker(topicSubscriber);

        }

    }


    // Starts a dedicated ConsumerHandler thread for a new subscriber if not already running

    public void startConsumerWorker(final TopicSubscriber topicSubscriber) {

        final String subscriberId = topicSubscriber.getConsumer().getId();


        // Check if a consumer handler already exists for this subscriber
        if (!consumerHandlers.containsKey(subscriberId)) {
            final ConsumerHandler consumerHandler = new ConsumerHandler(topic, topicSubscriber);

            // Corrected line: put into the actual class-level map
            consumerHandlers.put(subscriberId, consumerHandler);

            // Start a new thread for this subscriber's message consumption
            new Thread(consumerHandler).start();
        }

        // Wake up the consumer handler in case it is waiting for new messages
        final ConsumerHandler consumerHandler = consumerHandlers.get(subscriberId);
        consumerHandler.wakeUpIfNeeded();
    }

    public Topic getTopic() {
        return topic;
    }

}
