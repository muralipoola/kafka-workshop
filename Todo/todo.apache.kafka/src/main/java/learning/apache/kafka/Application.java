package learning.apache.kafka;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

  private final static Logger logger = Logger.getLogger(Application.class.getName());

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    logger.log(Level.INFO, "Producing messages to Kafka Asynchronously::");
    MessageProducerAsync producerAsync = new MessageProducerAsync();
    producerAsync.produceMessagesToKafka();

//    logger.log(Level.INFO, "Producing messages to Kafka Synchronously::");
//    MessageProducerSync producerSync = new MessageProducerSync();
//    producerSync.produceMessagesToKafka();

//    logger.log(Level.INFO, "Producing messages to Kafka with callback::");
//    MessageProducerCallback producerCallback = new MessageProducerCallback();
//    producerCallback.produceMessagesToKafka();

//    logger.log(Level.INFO, "Producing messages to Kafka with key::");
//    MessageProducerWithKey producerWithKey = new MessageProducerWithKey();
//    producerWithKey.produceMessagesToKafka();

//    logger.log(Level.INFO, "Consuming messages from Kafka with auto commit::");
//    MessageConsumer consumer = new MessageConsumer();
//    consumer.consumeMessagesFromKafka();

//    logger.log(Level.INFO, "Consuming messages from Kafka with manual commit::");
//    MessageConsumer consumer = new MessageConsumer();
//    consumer.consumeMessagesFromKafka();
  }

}
