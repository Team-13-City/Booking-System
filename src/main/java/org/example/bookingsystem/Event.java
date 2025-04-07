package org.example.bookingsystem;

import java.time.LocalDateTime;

public class Event {
    String eventID;
    String title;
    LocalDateTime dateTime;
    Venue venue;
    String layoutType;
    boolean isOpenToPublic;

    public boolean isCloseToPublic() {
        return !isOpenToPublic;
    }

    public void updateEventDetails(String newTitle, LocalDateTime newDateTime, Venue newVenue, String newLayout, boolean isOpen) {
        this.title = newTitle;
        this.dateTime = newDateTime;
        this.venue = newVenue;
        this.layoutType = newLayout;
        this.isOpenToPublic = isOpen;
    }
}