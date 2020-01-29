package learning.apache.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class MessageProducerAsync {

  public void produceMessagesToKafka() {

    Properties producerProperties = getProducerProperties();
    Producer<String, String> producer = new KafkaProducer<>(producerProperties);

    for (int i = 1; i <= 10; i++) {
      producer.send(new ProducerRecord<>(AppConfig.KafkaTopicName, "async message" + i));
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
