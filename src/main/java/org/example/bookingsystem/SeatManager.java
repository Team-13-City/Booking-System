package org.example.bookingsystem;

import java.util.*;

public class SeatManager {
    Map<String, List<Seat>> eventSeatMap = new HashMap<>();

    public boolean reserveSeat(Event event, String seatID) {
        List<Seat> seats = eventSeatMap.get(event.eventID);
        if (seats != null) {
            for (Seat seat : seats) {
                if (seat.seatID.equals(seatID) && seat.isAvailable()) {
                    seat.markReserved();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean releaseSeat(Event event, String seatID) {
        List<Seat> seats = eventSeatMap.get(event.eventID);
        if (seats != null) {
            for (Seat seat : seats) {
                if (seat.seatID.equals(seatID) && !seat.isAvailable()) {
                    seat.markReleased();
                    return true;
                }
            }
        }
        return false;
    }
}