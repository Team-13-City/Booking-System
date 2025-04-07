package org.example.bookingsystem;

import java.util.*;

public class Customer {
    String userID;
    String name;
    String email;
    Boolean fol; // Friends of Lancaster
    List<Ticket> bookingHistory = new ArrayList<>();

    public boolean isFol() {
        return fol;
    }

    public List<Ticket> getBookingHistory() {
        return bookingHistory;
    }
}