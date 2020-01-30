package learning.reactor.kafka.json.controller;

import learning.reactor.kafka.json.config.AppConfig;
import learning.reactor.kafka.json.model.Course;
import learning.reactor.kafka.json.model.Metadata;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@RequestMapping("course")
@RestController
public class CourseController {

  private static final Logger log = LoggerFactory.getLogger(CourseController.class);

  @Autowired
  private KafkaSender<String, Course> kafkaSender;

  @Autowired
  private AppConfig appConfig;

  @RequestMapping(method = RequestMethod.GET)
  public Metadata publish(@RequestParam String name, @RequestParam int duration) {

    String key = String.format("course-%s", name);

    SenderRecord<String, Course, String> record = SenderRecord
        .create(new ProducerRecord<>(appConfig.getCourseKafkaConfig().getTopicName(), key,
                new Course(name, duration)),
            "correlation");

    Metadata metadata = new Metadata();

    kafkaSender.send(Mono.just(record))
        .doOnNext(courseSenderResult -> {
          RecordMetadata recordMetadata = courseSenderResult.recordMetadata();
          log.info("Published course:{} to topic-partition={}-{} offset={}\n",
              courseSenderResult.correlationMetadata(),
              recordMetadata.topic(),
              recordMetadata.partition(),
              recordMetadata.offset());
          metadata.setOffset(recordMetadata.offset());
          metadata.setPartition(recordMetadata.partition());
          metadata.setTopicName(recordMetadata.topic());
        })
        .blockLast();

    //    SenderResult<String> senderResult = kafkaSender.send(Mono.just(record))
    //        .doOnError(e -> log.error("Send failed"))
    //        .blockLast();

    return metadata;

  }
}
