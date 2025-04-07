package org.example.bookingsystem;

public class Seat {
    String seatID;
    boolean isReserved;
    boolean isAccessable;
    enum veiwQuality {GOOD, RESTRICTED};


    public boolean isAvailable() {
        return !isReserved;
    }

    void markReserved() {
        isReserved = true;
        // set this in the database
    }

    void markReleased() {
        isReserved = false;
        // set this in the database
    }
}
