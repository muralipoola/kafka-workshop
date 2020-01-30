package learning.apache.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MessageProducerWithKey {

  private final static Logger logger = Logger.getLogger(MessageProducerWithKey.class.getName());

  public void produceMessagesToKafka() throws ExecutionException, InterruptedException {

    Properties producerProperties = Util.getProducerProperties();
    Producer<String, String> producer = new KafkaProducer<>(producerProperties);

    for (int i = 1; i <= 10; i++) {

      String message = "keyed message " + i;
      String key = String.valueOf(i % 3);

      //TODO:: Provide key when sending message to kafka and observe the partition assigned
      RecordMetadata metadata = producer
          .send(new ProducerRecord<>(AppConfig.KafkaTopicName, message)).get();

      logger.log(Level.INFO,
          String.format("Message - [%s] produced to topic - [%s] with partition [%d] and offset [%d]",
              message, metadata.topic(), metadata.partition(), metadata.offset()));
    }

    producer.flush();
    producer.close();
  }

}
