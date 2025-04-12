/**
 * Data Access Object (DAO) for managing venue data in the database.
 * Provides methods for CRUD operations on venues.
 */
package org.example.bookingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles all database operations related to venues.
 * Implements the Data Access Object pattern for venue management.
 */
public class VenueDAO {
    /**
     * Retrieves all venues from the database.
     *
     * @return A list of all venues in the database
     */
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

    /**
     * Adds a new venue to the database.
     *
     * @param venue The venue to be added
     */
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

    /**
     * Updates an existing venue in the database.
     *
     * @param venue The venue with updated information
     */
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

    /**
     * Deletes a venue from the database.
     *
     * @param venueId The ID of the venue to be deleted
     */
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
