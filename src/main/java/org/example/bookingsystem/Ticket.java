package org.example.bookingsystem;

import java.time.LocalDateTime;

public class Ticket {
    String ticketID;
    Event event;
    Customer customer;
    Seat seat;
    double price;
    enum status {ACTIVE, CANCELLED, REFUNDED};
    LocalDateTime purchaseDate;
}
