package learning.apache.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MessageProducerSync {

  private final static Logger logger = Logger.getLogger(MessageProducerSync.class.getName());

  public void produceMessagesToKafka() throws ExecutionException, InterruptedException {

    Properties producerProperties = Util.getProducerProperties();
    Producer<String, String> producer = new KafkaProducer<>(producerProperties);

    for (int i = 1; i <= 10; i++) {

      String message = "sync message " + i;

      // TODO:: Write to kafka synchronously using the producer created above
      RecordMetadata metadata = null;

      logger.log(Level.INFO,
          String.format("Message - [%s] produced to topic - [%s] with partition [%d] and offset [%d]",
              message, metadata.topic(), metadata.partition(), metadata.offset()));
    }

    producer.flush();
    producer.close();
  }
}
