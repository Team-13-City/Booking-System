package org.example.bookingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class TicketManager {
    List<Ticket> tickets = new ArrayList<>();

    public Ticket sellTicket(int customerID, String event, int price, int seat_id) {
        /*Ticket ticket = new Ticket();
        //ticket.ticketID = UUID.randomUUID().toString();
        ticket.customer = customer;
        ticket.event = event;
        ticket.seat = seat;
        ticket.price = discount != null ? discount.applyTo(event.venue.getBasePrice()) : event.venue.getBasePrice();
        ticket.status = Ticket.Status.ACTIVE;
        ticket.purchaseDate = LocalDateTime.now();
        seat.markReserved();
        tickets.add(ticket); */
        LocalDateTime purchaseDate = LocalDateTime.now();
        String query = "INSERT INTO tickets (\n" +
                "    event,\n" +
                "    customer_id,\n" +
                "    seat_id,\n" +
                "    price,\n" +
                "    status\n" +
                ") VALUES (" + seat_id + ",\n" + event + ",\n" + seat_id + ",\n" + price + ");";
        try (
                Connection con = DatabaseManager.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Ticket(event, customerID, seat_id, price);
    }

    public boolean cancelTicket(int ticketID) {
        for (Ticket t : tickets) {
            if (t.ticketID==ticketID) {
                String query = "DELETE FROM tickets\n" +
                        "WHERE ticket_id = " + ticketID;

                try (
                        Connection con = DatabaseManager.getConnection();
                        PreparedStatement ps = con.prepareStatement(query);
                        ResultSet rs = ps.executeQuery()
                ) {
                    while (rs.next()) {
                        System.out.println(rs.getString(1));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                tickets.remove(t);
                return true;
            }
        }
        return false;
    }


    public Ticket getTicket(int tid){
        String query = "SELECT * FROM tickets \n" +
                "WHERE ticket_id == " + tid + ";";

        try (
                Connection con = DatabaseManager.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getString("event"),
                        rs.getInt("customer_id"),
                        rs.getInt("seatID"),
                        rs.getInt("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
