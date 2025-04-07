package org.example.bookingsystem;

import java.util.*;

public class Venue {
    String venueID;
    String name;
    int capacity;
    Map<String, Seat> layout; // seatID -> Seat

    public Seat getSeat(String seatID) {
        return layout.get(seatID);
    }

    public List<Seat> getAllSeats() {
        return new ArrayList<>(layout.values());
    }

    public List<Seat> getAccessibleSeats() {
        List<Seat> accessibleSeats = new ArrayList<>();
        for (Seat seat : layout.values()) {
            if (seat.isAccessable) {
                accessibleSeats.add(seat);
            }
        }
        return accessibleSeats;
    }

    public double getBasePrice() {
        // Placeholder: pricing can be dynamic per venue or hardcoded
        return 15.0;
    }
}