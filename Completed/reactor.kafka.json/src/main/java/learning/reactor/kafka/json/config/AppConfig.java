package learning.reactor.kafka.json.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app-config")
public class AppConfig {

  private KafkaConfig employeeKafkaConfig;
  private KafkaConfig courseKafkaConfig;

  public KafkaConfig getEmployeeKafkaConfig() {
    return employeeKafkaConfig;
  }

  public void setEmployeeKafkaConfig(KafkaConfig employeeKafkaConfig) {
    this.employeeKafkaConfig = employeeKafkaConfig;
  }

  public KafkaConfig getCourseKafkaConfig() {
    return courseKafkaConfig;
  }

  public void setCourseKafkaConfig(KafkaConfig courseKafkaConfig) {
    this.courseKafkaConfig = courseKafkaConfig;
  }

}
