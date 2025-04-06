package com.example.bookingsystem;

public class Ticket {
    private String ticketId;
    private Guest guest;
    private Seat seat;
    private double price;

    public Ticket(String ticketId, Guest guest, Seat seat, double price) {
        this.ticketId = ticketId;
        this.guest = guest;
        this.seat = seat;
        this.price = price;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Guest getGuest() {
        return guest;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }
}
