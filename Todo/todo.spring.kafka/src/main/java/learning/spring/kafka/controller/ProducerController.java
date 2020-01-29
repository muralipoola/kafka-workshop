package learning.spring.kafka.controller;

import java.util.concurrent.ExecutionException;
import learning.spring.kafka.model.Metadata;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("producer")
@RestController
public class ProducerController {

  @Autowired
  private KafkaTemplate<String, String> template;

  @Value("${spring.kafka.consumer.topic-name}")
  private String topicName;

  @RequestMapping(method = RequestMethod.GET)
  public Metadata publish(@RequestParam String message) throws ExecutionException, InterruptedException {
    SendResult<String, String> result = template.send(topicName, message).get();
    RecordMetadata metadata = result.getRecordMetadata();
    return new Metadata(metadata.topic(), metadata.partition(), metadata.offset());
  }

}
