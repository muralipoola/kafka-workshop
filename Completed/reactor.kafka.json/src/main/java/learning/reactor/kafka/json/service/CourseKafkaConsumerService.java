package learning.reactor.kafka.json.service;

import learning.reactor.kafka.json.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

@Service
public class CourseKafkaConsumerService {

  private static final Logger log = LoggerFactory.getLogger(EmployeeConsumerService.class);

  @Autowired
  private KafkaReceiver<String, Course> kafkaReceiver;

  public void consumeCourseFromKafka() {
    Flux<ReceiverRecord<String, Course>> sourceStream = kafkaReceiver.receive();
    sourceStream.doOnNext(o -> {
      log.info("Consumed course: {}", o.toString());
      o.receiverOffset().acknowledge();
    }).subscribe();
  }

}
