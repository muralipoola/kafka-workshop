package com.example.kafka.models;

import java.util.Date;

public class Event {
    public String EventId;
    public int UserId;
    public String CreatedBy;
    public Date CreatedAt;

    public Event() {
    }

    public Event(String eventId, int userId, String createdBy, Date createdAt) {
        this.EventId = eventId;
        this.UserId = userId;
        this.CreatedBy = createdBy;
        this.CreatedAt = createdAt;
    }

    @Override
    public String toString() {
        return "Event(" + this.UserId + ", " + this.CreatedBy + ")";
    }
}
