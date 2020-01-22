package learning.spring.kafka.config;

import java.util.HashMap;
import java.util.Map;
import learning.spring.kafka.model.Course;
import learning.spring.kafka.model.Employee;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

//@Configuration
public class ApplicationConfiguration {

  @Value("${bootstrap-servers}")
  private String bootstrapServers;

  public ProducerFactory<String, Employee> employeeProducerFactory() {
    return new DefaultKafkaProducerFactory<String, Employee>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, Employee> kafkaTemplate1() {
    return new KafkaTemplate<>(employeeProducerFactory());
  }

  public ProducerFactory<String, Course> courseProducerFactory() {
    return new DefaultKafkaProducerFactory<String, Course>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, Course> kafkaTemplate2() {
    return new KafkaTemplate<>(courseProducerFactory());
  }

  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return props;
  }

  public ConsumerFactory<String, Employee> employeeConsumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
        new JsonDeserializer<>(Employee.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Employee> kafkaListenerContainerFactoryForEmployee() {
    ConcurrentKafkaListenerContainerFactory<String, Employee> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(employeeConsumerFactory());
    return factory;
  }

  public ConsumerFactory<String, Course> courseConsumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
        new JsonDeserializer<>(Course.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Course> kafkaListenerContainerFactoryForCourse() {
    ConcurrentKafkaListenerContainerFactory<String, Course> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(courseConsumerFactory());
    return factory;
  }

  public Map<String, Object> consumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    return props;
  }

}
