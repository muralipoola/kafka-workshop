package com.thoughtworks.kafka;

import com.thoughtworks.kafka.workshop.Order;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDe;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.avro.Schema;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class FraudDetectionApplication {
    private static Logger LOG = LoggerFactory.getLogger(FraudDetectionApplication.class);

    public static void main(String[] args) {
        //Topics:
        // payments -> validated-payments
        // Message Key:
        // String transactionId
        // Message value (order)
        // String userId
        // Integer numberOfItems
        // Float totalAmount

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "fraud-detection-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        //Create a StreamBuilder

        //Write a data pipeline

        //Create Topology

        //Create KafkaStreams

        //Start the Stream

        //Close the stream in a shutdown hook
    }

    private static void printOnError(String transactionId, Order order) {
        LOG.info("\n***********************************************");
        LOG.info("Entering stream with ID ["+transactionId+"] of user ["+ order.getUserId()+"] and total ["+ order.getTotalAmount()+"] and Items ["+ order.getNumberOfItems()+"]");
    }

    private static void printOnExit(String transactionId, Order order) {
        LOG.info("\n***********************************************");
        LOG.info("Exiting stream with ID ["+transactionId+"] of user ["+ order.getUserId()+"] and total ["+ order.getTotalAmount()+"] and Items ["+ order.getNumberOfItems()+"]");
    }
}
