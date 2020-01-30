package learning.reactor.kafka.json.controller;

import learning.reactor.kafka.json.config.AppConfig;
import learning.reactor.kafka.json.model.Employee;
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

@RequestMapping("employee")
@RestController
public class EmployeeController {

  private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

  @Autowired
  private KafkaSender<String, Employee> kafkaSender;

  @Autowired
  private AppConfig appConfig;

  @RequestMapping(method = RequestMethod.GET)
  public Metadata publish(@RequestParam int id, @RequestParam String name) {

    String key = String.format("employee-%d", id);

    SenderRecord<String, Employee, String> record = SenderRecord
        .create(new ProducerRecord<>(appConfig.getEmployeeKafkaConfig().getTopicName(), key,
                new Employee(id, name)),
            "correlation");

    Metadata metadata = new Metadata();

    kafkaSender.send(Mono.just(record))
        .doOnNext(employeeSenderResult -> {
          RecordMetadata recordMetadata = employeeSenderResult.recordMetadata();
          log.info("Published employee:{} to topic-partition={}-{} offset={}\n",
              employeeSenderResult.correlationMetadata(),
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
