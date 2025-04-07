package org.example.bookingsystem;

import java.util.List;
import java.util.Map;

public class Venue {
    String venueID;
    String name;
    int capacity;
    Map<String, Seat> layout; // seatID -> Seat

    Seat getSeat(String seatID) {
        return layout.get(seatID);
    }

    List<Seat> getAllSeats() {

    }

    List<Seat> getAccessibleSeats() {

    }
}
