package org.example.bookingsystem;

import java.time.LocalDateTime;
import java.util.*;

public class TicketManager {
    List<Ticket> tickets = new ArrayList<>();

    public Ticket sellTicket(Customer customer, Event event, Seat seat, Discount discount) {
        Ticket ticket = new Ticket();
        ticket.ticketID = UUID.randomUUID().toString();
        ticket.customer = customer;
        ticket.event = event;
        ticket.seat = seat;
        ticket.price = discount != null ? discount.applyTo(event.venue.getBasePrice()) : event.venue.getBasePrice();
        ticket.status = Ticket.Status.ACTIVE;
        ticket.purchaseDate = LocalDateTime.now();
        seat.markReserved();
        tickets.add(ticket);
        return ticket;
    }

    public boolean cancelTicket(String ticketID) {
        for (Ticket t : tickets) {
            if (t.ticketID.equals(ticketID) && t.status == Ticket.Status.ACTIVE) {
                t.status = Ticket.Status.CANCELLED;
                t.seat.markReleased();
                return true;
            }
        }
        return false;
    }

    public boolean refundTicket(String ticketID) {
        for (Ticket t : tickets) {
            if (t.ticketID.equals(ticketID) && t.status == Ticket.Status.CANCELLED) {
                t.status = Ticket.Status.REFUNDED;
                return true;
            }
        }
        return false;
    }

    public List<Ticket> getTicketsByCustomer(Customer customer) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.customer.equals(customer)) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Ticket> getTicketsByEvent(Event event) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.event.equals(event)) {
                result.add(t);
            }
        }
        return result;
    }
}