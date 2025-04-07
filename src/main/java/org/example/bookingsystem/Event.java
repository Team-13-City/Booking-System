package org.example.bookingsystem;

import java.time.LocalDateTime;

public class Event {
    String eventID;
    String title;
    LocalDateTime dateTime;
    Venue venue;
    String layoutType;
    boolean isOpenToPublic;

    boolean isCloseToPublic() {
        return isOpenToPublic;
    };

    void updateEventDetails() {

    }
}

