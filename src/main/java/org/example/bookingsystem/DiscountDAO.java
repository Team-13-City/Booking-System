package org.example.bookingsystem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAO {
    public static List<Discount> getAllDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        String sql = "SELECT * FROM discounts";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                Discount discount = new Discount();
                discount.code = rs.getString("discount_code");
                String typeStr = rs.getString("type");
                discount.type = (typeStr.equals("PERCENTAGE")) ? Discount.Type.PERCENTAGE : Discount.Type.FIXED;
                discount.value = rs.getDouble("value");
                discount.validFrom = rs.getDate("valid_from").toLocalDate();
                discount.validUntil = rs.getDate("valid_until").toLocalDate();
                discount.eligibilityCriteria = rs.getString("eligibility");
                discounts.add(discount);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }

    public static void addDiscount(Discount discount) {
        String sql = "INSERT INTO discounts (discount_code, type, value, valid_from, valid_until, eligibility) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, discount.code);
            pstmt.setString(2, discount.type.name());
            pstmt.setDouble(3, discount.value);
            pstmt.setDate(4, Date.valueOf(discount.validFrom));
            pstmt.setDate(5, Date.valueOf(discount.validUntil));
            pstmt.setString(6, discount.eligibilityCriteria);
            pstmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateDiscount(Discount discount) {
        String sql = "UPDATE discounts SET type = ?, value = ?, valid_from = ?, valid_until = ?, eligibility = ? WHERE discount_code = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, discount.type.name());
            pstmt.setDouble(2, discount.value);
            pstmt.setDate(3, Date.valueOf(discount.validFrom));
            pstmt.setDate(4, Date.valueOf(discount.validUntil));
            pstmt.setString(5, discount.eligibilityCriteria);
            pstmt.setString(6, discount.code);
            pstmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDiscount(String code) {
        String sql = "DELETE FROM discounts WHERE discount_code = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);
            pstmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
