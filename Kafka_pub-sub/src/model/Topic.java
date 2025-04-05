package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topic {

    // Name of the topic, used for identification/display purposes.
    private final String topicName;
    // Unique identifier for the topic.
    private final String topicId;

    // List to store all messages published to this topic.
    // This list is exposed to the outside using an immutable getter.
    private final List<Message> messages;



    // List of subscribers who have subscribed to this topic.

    // This list is exposed to the outside using an immutable getter.

    private final List<TopicSubscriber> subscribers;

    public Topic(final String topicName, final String topicId) {

        this.topicName = topicName;

        this.topicId = topicId;

        this.messages = new ArrayList<>();

        this.subscribers = new ArrayList<>();

    }

    public synchronized void addMessage(final Message message) {
        messages.add(message);
    }

    public void addSubscriber(final TopicSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public String getTopicName() {
        return topicName;
    }
    public String getTopicId() {
        return topicId;
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }


    public List<TopicSubscriber> getSubscribers() {
        return Collections.unmodifiableList(subscribers);
    }

    // Getters Section End

}