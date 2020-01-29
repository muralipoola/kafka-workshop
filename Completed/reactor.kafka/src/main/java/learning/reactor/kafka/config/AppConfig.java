package learning.reactor.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app-config")
public class AppConfig {
  public static String KafkaTopicName = "learning.reactor.kafka";

  private KafkaConfig kafkaConfig;

  public KafkaConfig getKafkaConfig() {
    return kafkaConfig;
  }

  public void setKafkaConfig(KafkaConfig kafkaConfig) {
    this.kafkaConfig = kafkaConfig;
  }
}
