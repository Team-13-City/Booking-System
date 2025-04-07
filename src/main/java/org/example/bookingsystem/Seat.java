package org.example.bookingsystem;

public class Seat {
    String seatID;
    boolean isReserved;
    boolean isAccessable;
    enum ViewQuality { GOOD, RESTRICTED }
    ViewQuality viewQuality;

    public boolean isAvailable() {
        return !isReserved;
    }

    public void markReserved() {
        isReserved = true;
        // Update DB if needed
    }

    public void markReleased() {
        isReserved = false;
        // Update DB if needed
    }
}