package learning.spring.kafka.service;

import learning.spring.kafka.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class CourseConsumerService {

  public static Logger logger = LoggerFactory.getLogger(CourseConsumerService.class);

  @KafkaListener(topics = "#{'${course-topic-name}'}", groupId = "course-consumer")
  //TODO:: Use consumerFactory to customize configuration through code
  public void consumeMessagesFromKafka(@Payload Course record) {
    logger.info(record.toString());
  }

}
