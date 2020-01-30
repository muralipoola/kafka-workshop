package learning.reactor.kafka.json.starter;

import learning.reactor.kafka.json.service.EmployeeConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmployeeKafkaConsumerStarter implements CommandLineRunner {

  @Autowired
  EmployeeConsumerService kafkaConsumerService;

  @Override
  public void run(String... args) {
    kafkaConsumerService.consumeEmployeeFromKafka();
  }
}
