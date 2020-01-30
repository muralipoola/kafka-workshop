package learning.apache.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class MessageProducerCallback {

  private final static Logger logger = Logger.getLogger(MessageProducerCallback.class.getName());

  public void produceMessagesToKafka() throws ExecutionException, InterruptedException {

    Properties producerProperties = Util.getProducerProperties();
    Producer<String, String> producer = new KafkaProducer<>(producerProperties);

    for (int i = 1; i <= 10; i++) {

      String message = "callback message " + i;

      //TODO:: Provide callback here to get feedback on metadata
      producer.send(new ProducerRecord<>(AppConfig.KafkaTopicName, message));

    }

    producer.flush();
    producer.close();
  }

  public static class ProducerCallBack implements Callback {

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
      logger.log(Level.INFO,
          String.format("Message - produced to topic - [%s] with partition [%d] and offset [%d]",
              metadata.topic(), metadata.partition(), metadata.offset()));
    }
  }
}
