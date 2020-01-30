package learning.reactor.kafka.controller;

import learning.reactor.kafka.config.AppConfig;
import learning.reactor.kafka.model.Metadata;
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

@RequestMapping("producer")
@RestController
public class ProducerController {

  private static final Logger log = LoggerFactory.getLogger(ProducerController.class);

  @Autowired
  private KafkaSender<String, String> kafkaSender;

  @RequestMapping(method = RequestMethod.GET)
  public Metadata publish(@RequestParam String message) {
    SenderRecord<String, String, String> record = SenderRecord
        .create(new ProducerRecord<>(AppConfig.KafkaTopicName, "key", message), "correlation");

    Metadata metadata = new Metadata();

    kafkaSender.send(Mono.just(record))
        .doOnNext(stringSenderResult -> {
          RecordMetadata recordMetadata = stringSenderResult.recordMetadata();
          log.info("Message {} sent successfully, topic-partition={}-{} offset={}\n",
              stringSenderResult.correlationMetadata(),
              recordMetadata.topic(),
              recordMetadata.partition(),
              recordMetadata.offset());
          metadata.setOffset(recordMetadata.offset());
          metadata.setPartition(recordMetadata.partition());
          metadata.setTopicName(recordMetadata.topic());
        }).blockLast();

    //    SenderResult<String> senderResult = kafkaSender.send(Mono.just(record))
    //        .doOnError(e -> log.error("Send failed"))
    //        .blockLast();

    return metadata;

  }

}
