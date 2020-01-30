package learning.reactor.kafka.json.service;

import learning.reactor.kafka.json.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

@Service
public class EmployeeConsumerService {


  private static final Logger log = LoggerFactory.getLogger(EmployeeConsumerService.class);

  @Autowired
  private KafkaReceiver<String, Employee> kafkaReceiver;

  public void consumeEmployeeFromKafka() {
    Flux<ReceiverRecord<String, Employee>> sourceStream = kafkaReceiver.receive();
    sourceStream.doOnNext(o -> {
      log.info("Consumed employee: {}", o.toString());
      o.receiverOffset().acknowledge();
    }).subscribe();
  }

}
