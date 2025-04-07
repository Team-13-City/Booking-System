package org.example.bookingsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {
    public static List<Seat> getAllSeats() {
        List<Seat> seats = new ArrayList<>();
        String sql = "SELECT * FROM seats";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                Seat seat = new Seat();
                seat.seatID = String.valueOf(rs.getInt("seat_id"));
                seat.isReserved = rs.getBoolean("is_reserved");
                seat.isAccessable = rs.getBoolean("is_accessible");
                String viewQuality = rs.getString("view_quality");
                seat.viewQuality = (viewQuality != null && viewQuality.equalsIgnoreCase("GOOD"))
                        ? Seat.ViewQuality.GOOD
                        : Seat.ViewQuality.RESTRICTED;
                seats.add(seat);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }

    public static void updateSeat(Seat seat) {
        String sql = "UPDATE seats SET is_reserved = ? WHERE seat_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, seat.isReserved);
            pstmt.setInt(2, Integer.parseInt(seat.seatID));
            pstmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
