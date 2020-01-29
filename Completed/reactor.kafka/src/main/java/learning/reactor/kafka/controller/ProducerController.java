package learning.reactor.kafka.controller;

import learning.reactor.kafka.config.AppConfig;
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
import reactor.kafka.sender.SenderResult;

@RequestMapping("producer")
@RestController
public class ProducerController {

  private static final Logger log = LoggerFactory.getLogger(ProducerController.class);

  @Autowired
  private KafkaSender kafkaSender;

  @RequestMapping(method = RequestMethod.GET)
  public String publish(@RequestParam String message) {
    SenderRecord record = SenderRecord.create(new ProducerRecord<>(AppConfig.KafkaTopicName, message), "correlation");
    Object result = kafkaSender.send(Mono.just(record)).blockLast();
//    SenderResult senderResult = kafkaSender.send(Mono.just(record))
//        .doOnError(e -> log.error("Send failed"))
//        .blockLast();
    return "hello";
  }

}
