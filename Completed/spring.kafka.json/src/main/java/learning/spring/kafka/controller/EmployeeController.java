package learning.spring.kafka.controller;

import java.util.concurrent.ExecutionException;
import learning.spring.kafka.model.Employee;
import learning.spring.kafka.model.Metadata;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("employee")
@RestController
public class EmployeeController {

  @Autowired
  private KafkaTemplate<String, Employee> template;

  @Value("${employee-topic-name}")
  private String topicName;

  @RequestMapping(method = RequestMethod.GET)
  public Metadata publish(@RequestParam int id, @RequestParam String name)
      throws ExecutionException, InterruptedException {
    String key = String.format("employee-%d", id);
    SendResult<String, Employee> result = template.send(topicName, key, new Employee(id, name)).get();
    RecordMetadata metadata = result.getRecordMetadata();
    return new Metadata(metadata.topic(), metadata.partition(), metadata.offset());
  }

}
