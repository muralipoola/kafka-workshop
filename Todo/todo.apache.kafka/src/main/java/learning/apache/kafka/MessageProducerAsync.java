package learning.apache.kafka;

import java.util.Properties;

public class MessageProducerAsync {

  public void produceMessagesToKafka() {

    Properties producerProperties = Util.getProducerProperties();

    // TODO:: Create KafkaProducer using the producerProperties

    for (int i = 1; i <= 10; i++) {

      String message = "sync message " + i;
      // TODO:: Write to kafka using the producer created above
    }

  }

}
