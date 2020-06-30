package com.example.kafka.services;

import com.example.kafka.models.Event;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class EventPublisher {
    @Value("${eventTopic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<Integer, Event> kafkaTemplate;

    public RecordMetadata publish(Event event) throws ExecutionException, InterruptedException {
        SendResult result = kafkaTemplate.send(topicName, event.UserId, event).get();
        return result.getRecordMetadata();
    }
}
