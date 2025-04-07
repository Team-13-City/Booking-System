package org.example.bookingsystem;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
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
