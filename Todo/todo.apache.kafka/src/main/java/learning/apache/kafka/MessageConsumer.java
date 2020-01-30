package learning.apache.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MessageConsumer {

  private final static Logger logger = Logger.getLogger(MessageConsumer.class.getName());

  public void consumeMessagesFromKafka() {
    Properties consumerProperties = Util.getConsumerProperties();
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);

    consumer.subscribe(Arrays.asList(AppConfig.KafkaTopicName));

    try {
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
        for (ConsumerRecord<String, String> record : records) {
          logger.log(Level.INFO, record.offset() + ": " + record.value());
        }

        // TODO:: Commit offset manually
      }
    } finally {
      consumer.close();
    }

  }

}
