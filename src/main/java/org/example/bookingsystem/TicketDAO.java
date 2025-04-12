/**
 * Data Access Object (DAO) for managing ticket data in the database.
 * Provides methods for CRUD operations on tickets.
 */
package org.example.bookingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles all database operations related to tickets.
 * Implements the Data Access Object pattern for ticket management.
 */
public class TicketDAO {
    /**
     * Retrieves all tickets from the database.
     *
     * @return A list of all tickets in the database
     */
    public static List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int ticketId = rs.getInt("ticket_id");
                int eventId = rs.getInt("event_id");
                int customerId = rs.getInt("customer_id");
                int seatId = rs.getInt("seat_id");
                double price = rs.getDouble("price");
                String status = rs.getString("status");
                Timestamp ts = rs.getTimestamp("purchase_date");
                LocalDateTime purchaseDate = ts.toLocalDateTime();
                tickets.add(new Ticket(ticketId, eventId, customerId, seatId, price, status, purchaseDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    /**
     * Adds a new ticket to the database.
     *
     * @param ticket The ticket to be added
     */
    public static void addTicket(Ticket ticket) {
        String sql = "INSERT INTO tickets (event_id, customer_id, seat_id, price, status, purchase_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, ticket.getEventId());
            pstmt.setInt(2, ticket.getCustomerId());
            pstmt.setInt(3, ticket.getSeatId());
            pstmt.setDouble(4, ticket.getPrice());
            pstmt.setString(5, ticket.getStatus());
            pstmt.setTimestamp(6, Timestamp.valueOf(ticket.getPurchaseDate()));
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ticket.setTicketId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing ticket in the database.
     *
     * @param ticket The ticket with updated information
     */
    public static void updateTicket(Ticket ticket) {
        String sql = "UPDATE tickets SET event_id = ?, customer_id = ?, seat_id = ?, price = ?, status = ?, purchase_date = ? WHERE ticket_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ticket.getEventId());
            pstmt.setInt(2, ticket.getCustomerId());
            pstmt.setInt(3, ticket.getSeatId());
            pstmt.setDouble(4, ticket.getPrice());
            pstmt.setString(5, ticket.getStatus());
            pstmt.setTimestamp(6, Timestamp.valueOf(ticket.getPurchaseDate()));
            pstmt.setInt(7, ticket.getTicketId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a ticket from the database.
     *
     * @param ticketId The ID of the ticket to be deleted
     */
    public static void deleteTicket(int ticketId) {
        String sql = "DELETE FROM tickets WHERE ticket_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ticketId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
