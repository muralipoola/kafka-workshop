package learning.apache.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class MessageConsumer {

  private final static Logger logger = Logger.getLogger(MessageConsumer.class.getName());


  public void consumeMessagesFromKafka() {
    Properties consumerProperties = getConsumerProperties();
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);

    consumer.subscribe(Arrays.asList(AppConfig.KafkaTopicName));

    try {
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
        for (ConsumerRecord<String, String> record : records) {
          logger.log(Level.INFO, record.offset() + ": " + record.value());
        }

//        consumer.commitSync();
      }
    } finally {
      consumer.close();
    }

  }

  private Properties getConsumerProperties() {
    Properties producerProperties = new Properties();

    producerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.KafkaServers);
    producerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    producerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    producerProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    producerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "consumergroup3");
    producerProperties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    return producerProperties;
  }
}
