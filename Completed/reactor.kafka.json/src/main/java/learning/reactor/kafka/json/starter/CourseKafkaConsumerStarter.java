package learning.reactor.kafka.json.starter;

import learning.reactor.kafka.json.service.CourseKafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseKafkaConsumerStarter implements CommandLineRunner {

  @Autowired
  CourseKafkaConsumerService courseKafkaConsumerService;

  @Override
  public void run(String... args) {
    courseKafkaConsumerService.consumeCourseFromKafka();
  }
}
