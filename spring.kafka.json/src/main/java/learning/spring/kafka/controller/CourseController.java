package learning.spring.kafka.controller;

import java.util.concurrent.ExecutionException;
import learning.spring.kafka.model.Metadata;
import learning.spring.kafka.model.Course;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("course")
@RestController
public class CourseController {

  @Autowired
  private KafkaTemplate<String, Course> template;

  @Value("${course-topic-name}")
  private String topicName;

  @RequestMapping(method = RequestMethod.GET)
  public Metadata publish(@RequestParam String name, @RequestParam int duration)
      throws ExecutionException, InterruptedException {
    String key = String.format("course-%s", name);
    SendResult<String, Course> result = template.send(topicName, key, new Course(name, duration)).get();
    RecordMetadata metadata = result.getRecordMetadata();
    return new Metadata(metadata.topic(), metadata.partition(), metadata.offset());
  }

}
