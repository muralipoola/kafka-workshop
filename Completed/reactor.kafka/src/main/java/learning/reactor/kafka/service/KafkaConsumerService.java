package learning.reactor.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

@Service
public class KafkaConsumerService {


  private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

  @Autowired
  private KafkaReceiver<String, String> kafkaReceiver;

  public void consumeMessagesFromKafka() {
    Flux<ReceiverRecord<String, String>> sourceStream = kafkaReceiver.receive();
    sourceStream.doOnNext(o -> {
      log.info(o.toString());
      o.receiverOffset().acknowledge();
    }).subscribe();
  }

}
