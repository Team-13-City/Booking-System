package org.example.bookingsystem;

import java.time.LocalDateTime;

public class Ticket {
    static int tickets = 0;
    int ticketID;

    String event;
    int customerID;
    int seatID;
    double price;

    public Ticket(String e, int c, int s, double p) {
        tickets+=1;
        this.ticketID = tickets+1;
        this.event = e;
        this.customerID = c;
        this.seatID = s;
        this.price = p;


    }
    public Ticket(int ticketID, String event, int c, int s, double p) {
        tickets+=1;
        this.ticketID = tickets+1;
        this.event = event;
        this.customerID = c;
        this.seatID = s;
        this.price = p;

    }

    // Optional: Getters & toString() if you need them
}
