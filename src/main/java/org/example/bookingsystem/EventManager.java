package org.example.bookingsystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class EventManager {
    List<Event> events;
    Map<LocalDate, List<Event>> calendar;

    Event createEvent(String title, LocalDateTime dateTime, Venue venue) {

    }

    boolean cancelEvent(String eventId) {

    }

    boolean cancelEvent(Event event) {

    }

    List<Event> getAllEvents() {
        return events;
    }
}
