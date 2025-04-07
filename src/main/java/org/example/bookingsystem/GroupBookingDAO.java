package org.example.bookingsystem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GroupBookingDAO {
    public static List<GroupBooking> getAllGroupBookings() {
        List<GroupBooking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM group_bookings";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int groupId = rs.getInt("group_id");
                int eventId = rs.getInt("event_id");
                int size = rs.getInt("size");
                String seatPreferences = rs.getString("seat_preferences");
                String paymentStatus = rs.getString("payment_status");
                Date deadlineDate = rs.getDate("deadline");
                LocalDate deadline = deadlineDate.toLocalDate();
                bookings.add(new GroupBooking(groupId, eventId, size, seatPreferences, paymentStatus, deadline));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public static void addGroupBooking(GroupBooking booking) {
        String sql = "INSERT INTO group_bookings (event_id, size, seat_preferences, payment_status, deadline) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, booking.getEventId());
            pstmt.setInt(2, booking.getSize());
            pstmt.setString(3, booking.getSeatPreferences());
            pstmt.setString(4, booking.getPaymentStatus());
            pstmt.setDate(5, Date.valueOf(booking.getDeadline()));
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    booking.setGroupId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateGroupBooking(GroupBooking booking) {
        String sql = "UPDATE group_bookings SET event_id = ?, size = ?, seat_preferences = ?, payment_status = ?, deadline = ? WHERE group_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, booking.getEventId());
            pstmt.setInt(2, booking.getSize());
            pstmt.setString(3, booking.getSeatPreferences());
            pstmt.setString(4, booking.getPaymentStatus());
            pstmt.setDate(5, Date.valueOf(booking.getDeadline()));
            pstmt.setInt(6, booking.getGroupId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGroupBooking(int groupId) {
        String sql = "DELETE FROM group_bookings WHERE group_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, groupId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
