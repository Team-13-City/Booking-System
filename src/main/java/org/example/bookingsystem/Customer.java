package org.example.bookingsystem;

import java.util.List;

public class Customer {
    String userID;
    String name;
    String email;
    Boolean fol; // Friends of Lancaster
    List<Ticket> bookingHistory;

    boolean isFol() {
        return fol;
    }

    List<Ticket> getBookingHistory() {
        return bookingHistory;
    }
}
