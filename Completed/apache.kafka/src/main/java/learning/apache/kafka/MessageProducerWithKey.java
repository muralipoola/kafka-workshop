package learning.apache.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class MessageProducerWithKey {

  private final static Logger logger = Logger.getLogger(MessageProducerWithKey.class.getName());

  public void produceMessagesToKafka() throws ExecutionException, InterruptedException {

    Properties producerProperties = getProducerProperties();
    Producer<String, String> producer = new KafkaProducer<>(producerProperties);

    for (int i = 1; i <= 10; i++) {

      String message = "keyed message " + i;
      RecordMetadata metadata = producer
          .send(new ProducerRecord<>(AppConfig.KafkaTopicName, String.valueOf(i % 3), message)).get();

      logger.log(Level.INFO,
          String.format("Message - [%s] produced to topic - [%s] with partition [%d] and offset [%d]",
              message, metadata.topic(), metadata.partition(), metadata.offset()));
    }

    producer.flush();
    producer.close();
  }

  private Properties getProducerProperties() {
    Properties producerProperties = new Properties();

    producerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.KafkaServers);
    producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    return producerProperties;
  }

}
