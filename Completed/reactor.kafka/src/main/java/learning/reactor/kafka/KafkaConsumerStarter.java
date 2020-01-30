package learning.reactor.kafka;

import learning.reactor.kafka.service.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerStarter implements CommandLineRunner {

  @Autowired
  KafkaConsumerService kafkaConsumerService;

  @Override
  public void run(String... args) {
    kafkaConsumerService.consumeMessagesFromKafka();
  }
}
