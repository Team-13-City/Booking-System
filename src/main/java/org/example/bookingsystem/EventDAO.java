/**
 * Data Access Object (DAO) for managing event data in the database.
 * Provides methods for CRUD operations on events.
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
 * A class that handles all database operations related to events.
 * Implements the Data Access Object pattern for event management.
 */
public class EventDAO {
    /**
     * Retrieves all events from the database.
     *
     * @return A list of all events in the database
     */
    public static List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("event_id");
                String title = rs.getString("title");
                Timestamp ts = rs.getTimestamp("date_time");
                LocalDateTime dateTime = ts.toLocalDateTime();
                int venueId = rs.getInt("venue_id");
                String layoutType = rs.getString("layout_type");
                boolean isPublic = rs.getBoolean("is_public");
                events.add(new Event(id, title, dateTime, venueId, layoutType, isPublic));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    /**
     * Adds a new event to the database.
     *
     * @param event The event to be added
     */
    public static void addEvent(Event event) {
        String sql = "INSERT INTO events (title, date_time, venue_id, layout_type, is_public) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, event.getTitle());
            pstmt.setTimestamp(2, Timestamp.valueOf(event.getDateTime()));
            pstmt.setInt(3, event.getVenueId());
            pstmt.setString(4, event.getLayoutType());
            pstmt.setBoolean(5, event.isPublic());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    event.setEventId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing event in the database.
     *
     * @param event The event with updated information
     */
    public static void updateEvent(Event event) {
        String sql = "UPDATE events SET title = ?, date_time = ?, venue_id = ?, layout_type = ?, is_public = ? WHERE event_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, event.getTitle());
            pstmt.setTimestamp(2, Timestamp.valueOf(event.getDateTime()));
            pstmt.setInt(3, event.getVenueId());
            pstmt.setString(4, event.getLayoutType());
            pstmt.setBoolean(5, event.isPublic());
            pstmt.setInt(6, event.getEventId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an event from the database.
     *
     * @param eventId The ID of the event to be deleted
     */
    public static void deleteEvent(int eventId) {
        String sql = "DELETE FROM events WHERE event_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, eventId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
