package com.example.kafka.services;

import com.example.kafka.models.Event;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EventConsumer {

    @KafkaListener(topics = "event-topic", groupId = "group3")
    public void consume(@Payload Event event) {
        System.out.println(event);
    }
}
