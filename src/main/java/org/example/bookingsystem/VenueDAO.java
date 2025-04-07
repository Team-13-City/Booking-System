package org.example.bookingsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenueDAO {
    public static List<Venue> getAllVenues() {
        List<Venue> venues = new ArrayList<>();
        String sql = "SELECT * FROM venues";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int venueId = rs.getInt("venue_id");
                String name = rs.getString("name");
                int capacity = rs.getInt("capacity");
                venues.add(new Venue(venueId, name, capacity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venues;
    }

    public static void addVenue(Venue venue) {
        String sql = "INSERT INTO venues (name, capacity) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, venue.getName());
            pstmt.setInt(2, venue.getCapacity());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    venue.setVenueId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateVenue(Venue venue) {
        String sql = "UPDATE venues SET name = ?, capacity = ? WHERE venue_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, venue.getName());
            pstmt.setInt(2, venue.getCapacity());
            pstmt.setInt(3, venue.getVenueId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteVenue(int venueId) {
        String sql = "DELETE FROM venues WHERE venue_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, venueId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
