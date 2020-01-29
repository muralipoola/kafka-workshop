package learning.spring.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
  public static Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

  @KafkaListener(topics = "#{'${spring.kafka.consumer.topic-name}'}")
  public void consumeMessagesFromKafka(ConsumerRecord<?,?> record){
    logger.info(record.toString());
  }

}
