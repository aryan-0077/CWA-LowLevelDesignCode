package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topic {

    private final String topicName; // Name of the topic, used for identification/display purposes.
    private final String topicId; // Unique identifier for the topic.

    // List to store all messages published to this topic.
    // This list is exposed to the outside using an immutable getter.
    private final List<Message> messages;

    public Topic(final String topicName, final String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    // Synchronized so that add and wait/notify work properly.
    public synchronized void addMessage(Message message) {
        messages.add(message);
        // Notify all waiting subscriber threads that a new message is available.
        notifyAll();
    }

    public synchronized List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }
    // Getters Section End
}