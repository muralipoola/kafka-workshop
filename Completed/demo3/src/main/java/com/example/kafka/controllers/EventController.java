package com.example.kafka.controllers;

import com.example.kafka.models.Event;
import com.example.kafka.services.EventPublisher;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventPublisher eventPublisher;

    @RequestMapping(method = RequestMethod.GET)
    public RecordMetadata publishEvent(@RequestParam int user, @RequestParam String createdBy) throws ExecutionException, InterruptedException {
        Event event = new Event(UUID.randomUUID().toString(), user, createdBy, new Date());
        return eventPublisher.publish(event);
    }
}
