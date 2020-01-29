package learning.spring.kafka.service;

import learning.spring.kafka.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumerService {

  public static Logger logger = LoggerFactory.getLogger(EmployeeConsumerService.class);

  @KafkaListener(topics = "#{'${employee-topic-name}'}", groupId = "employee-consumer")
  //@KafkaListener(topics = "#{'${employee-topic-name}'}", groupId = "employee-consumer", containerFactory = "kafkaListenerContainerFactoryForEmployee")
  public void consumeMessagesFromKafka(@Payload Employee record) {
    logger.info(record.toString());
  }

}
