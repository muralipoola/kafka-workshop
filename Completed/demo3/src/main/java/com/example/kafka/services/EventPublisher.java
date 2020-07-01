package com.example.kafka.services;

import com.example.kafka.models.Event;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.ExecutionException;

@Service
public class EventPublisher {
    @Value("${eventTopic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<Integer, Event> kafkaTemplate;

    public void publish(Event event) throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<Integer, Event>> resultFuture = kafkaTemplate.send(topicName, event.UserId, event);
        resultFuture.addCallback(new SuccessCallback<SendResult<Integer, Event>>() {
            @Override
            public void onSuccess(SendResult<Integer, Event> result) {
                System.out.println("********** success ***********");
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("failure");
            }
        });
    }
}
