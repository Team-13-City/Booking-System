package com.example.bookingsystem;
import java.time.LocalDateTime;

public class Event {
    private String eventID;
    private String name;
    private LocalDateTime schedule;

    public Event (String eventID, String name, LocalDateTime schedule ){
        this.name = name;
        this.eventID = eventID;
        this.schedule = schedule;
    }
}
