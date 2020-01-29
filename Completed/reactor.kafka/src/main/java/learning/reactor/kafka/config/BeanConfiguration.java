package learning.reactor.kafka.config;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class BeanConfiguration {

  @Bean
  public KafkaSender createKafkaSender(AppConfig appConfig) {
    Properties producerProperties = getProducerProperties(appConfig.getKafkaConfig());
    SenderOptions senderOptions = SenderOptions.create(producerProperties);
    KafkaSender kafkaSender = KafkaSender.create(senderOptions);
    return kafkaSender;
  }

  private Properties getProducerProperties(KafkaConfig config) {
    Properties producerProperties = new Properties();

    producerProperties.setProperty(BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
    producerProperties.setProperty(ACKS_CONFIG, config.getProducerConfig().getAcksConfig());
    producerProperties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, config.getProducerConfig().getKeySerializer());
    producerProperties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, config.getProducerConfig().getValueSerializer());
    return producerProperties;
  }

  @Bean
  public KafkaReceiver createKafkaReceiver(AppConfig appConfig) {
    Properties consumerProperties = getConsumerProperties(appConfig.getKafkaConfig());
    ReceiverOptions receiverOptions = ReceiverOptions.create(consumerProperties);
    return null;
  }

  private Properties getConsumerProperties(KafkaConfig config) {
    Properties producerProperties = new Properties();

    producerProperties.setProperty(BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
    producerProperties.setProperty(ACKS_CONFIG, config.getProducerConfig().getAcksConfig());
    producerProperties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, config.getProducerConfig().getKeySerializer());
    producerProperties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, config.getProducerConfig().getValueSerializer());
    return producerProperties;
  }
}
