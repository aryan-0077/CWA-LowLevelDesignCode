package handler;

import model.Topic;
import model.TopicSubscriber;
import model.Message;

public class ConsumerHandler implements Runnable {

    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public ConsumerHandler( final Topic topic,  final TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        // Acquiring a lock on topicSubscriber to handle thread-safe message consumption per subscriber
        synchronized (topicSubscriber) {
            do {
                int curOffset = topicSubscriber.getOffset().get();

                // Wait if there are no new messages to consume
                while (curOffset >= topic.getMessages().size()) {
                    // Suspends the current thread until it is notified of new messages
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // Fetch the message at the current offset
                Message message = topic.getMessages().get(curOffset);

                // Invoke the subscriber's consume method to process the message
                try {
                    topicSubscriber.getConsumer().consume(message);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // We cannot just increment here since subscriber offset can be reset while it is consuming. So, after
                // consuming we need to increase only if it was previous one.
                // This ensures thread-safe advancement of the offset only if it wasn't reset concurrently
                topicSubscriber.getOffset().compareAndSet(curOffset, curOffset + 1);

            } while (true); // Continuously check for and consume messages

        }

    }




    // Method to notify the subscriber thread when new messages are available

    synchronized public void wakeUpIfNeeded() {

        synchronized (topicSubscriber) {

            // Notify the waiting subscriber thread to resume message consumption

            topicSubscriber.notify();

        }

    }



    // Getters Section Start

    public Topic getTopic(){

        return topic;

    }

    public TopicSubscriber getTopicSubscriber(){
        return topicSubscriber;
    }
    // Getters Section end
}