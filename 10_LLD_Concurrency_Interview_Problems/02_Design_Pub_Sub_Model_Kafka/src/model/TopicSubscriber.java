package model;

import public_interface.IConsumer;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriber {

    private final AtomicInteger offset; // Tracks the next message offset to be consumed by the subscriber.

    private final IConsumer consumer; // The actual subscriber instance that will process messages.

    public TopicSubscriber(final AtomicInteger offset,final IConsumer consumer){
        this.offset = offset;
        this.consumer = consumer;
    }

    public TopicSubscriber(final IConsumer consumer) {
        this.consumer= consumer;
        this.offset = new AtomicInteger(0); // Initialize the offset to 0, indicating the start of the topic.
    }

    public AtomicInteger getOffset() {
        return offset;
    }

    public IConsumer getConsumer() {
        return consumer;
    }
}