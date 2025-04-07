package org.example.bookingsystem;

import java.time.LocalDate;
import java.util.*;

public class GroupBooking {
    String groupID;
    Event event;
    int size;
    List<String> seatPreferences;
    String paymentStatus;

    public boolean isConfirmed() {
        return paymentStatus != null && paymentStatus.equalsIgnoreCase("CONFIRMED");
    }

    public void assignSeats(List<Seat> availableSeats) {
        // Assign preferred seats from seatPreferences if available
        int assigned = 0;
        for (String preferredSeatID : seatPreferences) {
            if (assigned >= size) break;
            Seat seat = event.venue.getSeat(preferredSeatID);
            if (seat != null && seat.isAvailable()) {
                seat.markReserved();
                assigned++;
            }
        }
    }
}