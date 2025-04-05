import ConcreteConsumers.ConsolePrintConsumer;
import ConcreteConsumers.SleepingConsumer;
import ConcreteProducer.SimpleProducer;
import MainKafkaQueue.KafkaQueue;
import model.Message;
import model.Topic;

public class Main {
    public static void main(String[] args) {
        final KafkaQueue kafkaQueue = new KafkaQueue();



        // Create topics

        final Topic topic1 = kafkaQueue.createTopic("t1");

        final Topic topic2 = kafkaQueue.createTopic("t2");



        // Create and register consumers

        final SleepingConsumer consumer1 = new SleepingConsumer("consumer1", 10000);

        final SleepingConsumer consumer2 = new SleepingConsumer("consumer2", 10000);

        final ConsolePrintConsumer consumer3 = new ConsolePrintConsumer("consumer3", "Hello world");



        kafkaQueue.subscribe(consumer1, topic1);

        kafkaQueue.subscribe(consumer2, topic1);

        kafkaQueue.subscribe(consumer3, topic2);



        // Create producers

        SimpleProducer producer1 = new SimpleProducer("producer1", kafkaQueue);

        SimpleProducer producer2 = new SimpleProducer("producer2", kafkaQueue);



        // Publish messages using producers

        producer1.publish(topic1.getTopicId(), new Message("m1"));

        producer1.publish(topic1.getTopicId(), new Message("m2"));



        producer2.publish(topic2.getTopicId(), new Message("m3"));


        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        producer2.publish(topic2.getTopicId(), new Message("m4"));

        producer1.publish(topic1.getTopicId(), new Message("m5"));



        kafkaQueue.resetOffset(topic1, consumer1, 0);
    }
}