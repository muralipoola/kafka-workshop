package com.thoughtworks.kafka;

import com.thoughtworks.kafka.workshop.Order;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import static java.lang.Thread.sleep;

public class PaymentService {
    private static Logger LOG = LoggerFactory.getLogger(PaymentService.class);

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "point-of-sale-system");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", KafkaAvroSerializer.class);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        Producer<String, Order> producer = new KafkaProducer<String, Order>(props);

        String transactionId = UUID.randomUUID().toString();
        Order order = Order.newBuilder().setUserId("user"+ new Random().nextInt()).setNumberOfItems(900).setTotalAmount(1500).build();

        LOG.info("\n***********************************************");
        LOG.info("Sending the Order with details ID ["+transactionId+"] of user ["+ order.getUserId()+"] and total ["+ order.getTotalAmount()+"] and Items ["+ order.getNumberOfItems()+"]");
        LOG.info("\n***********************************************");

        ProducerRecord<String,Order> producerRecord = new ProducerRecord<>("payments",transactionId,order);
        producer.send(producerRecord);
        sleep(1000);
    }
}
