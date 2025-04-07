package org.example.bookingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Customer {
    int userID;
    String name;
    String email;
    Boolean fol; // Friends of Lancaster
    List<Ticket> bookingHistory;
    public Customer(int id, String n, Boolean f, List<Ticket> b){
        this.userID = id;
        this.name = n;
        this.fol = f;
        this.bookingHistory = b;
    }
    public Customer(String e, String n, Boolean f, List<Ticket> b) {
        this.email = e;
        this.name = n;
        this.fol = f;
        this.bookingHistory = b;
    }

    private void updateBookingHistory(){
        String query = "SELECT * FROM tickets\n" +
                "WHERE customer_id = " + userID + ";";
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


        //bookingHistory
    }
    boolean isFol() {
        return fol;
    }

    List<Ticket> getBookingHistory() {
        return bookingHistory;
    }
}
