package org.example.bookingsystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class EventManager {
    List<Event> events = new ArrayList<>();
    Map<LocalDate, List<Event>> calendar = new HashMap<>();

    public Event createEvent(String title, LocalDateTime dateTime, Venue venue) {
        Event event = new Event();
        event.eventID = UUID.randomUUID().toString();
        event.title = title;
        event.dateTime = dateTime;
        event.venue = venue;
        event.isOpenToPublic = true;
        events.add(event);

        LocalDate date = dateTime.toLocalDate();
        calendar.computeIfAbsent(date, k -> new ArrayList<>()).add(event);
        return event;
    }

    public boolean cancelEvent(String eventId) {
        return events.removeIf(e -> e.eventID.equals(eventId));
    }

    public boolean cancelEvent(Event event) {
        return events.remove(event);
    }

    public List<Event> getAllEvents() {
        return events;
    }
}