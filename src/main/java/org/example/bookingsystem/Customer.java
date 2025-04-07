package org.example.bookingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Customer {
    int customerID;
    String name;
    String email;
    Boolean fol; // Friends of Lancaster

    public Customer(int id, String n, Boolean f){
        this.customerID = id;
        this.name = n;
        this.fol = f;
    }
    public Customer(String e ,String n, Boolean f){
        this.email = e;
        this.name = n;
        this.fol = f;
    }
    List<Ticket> bookingHistory = new ArrayList<>();

    public boolean isFol() {
        return fol;
    }

    public void updateBookingHistory(){
        String query = "SELECT * FROM tickets\n" +
                "WHERE customer_id = " + customerID + ";\n";

        try (
                Connection con = DatabaseManager.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {

                int event_id = rs.getInt("event_id");


                Ticket ticket = new Ticket(rs.getString("event"),
                        rs.getInt("customer_id"),
                        rs.getInt("seatID"),
                        rs.getInt("price"));
                bookingHistory.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Ticket> getBookingHistory() {
        return bookingHistory;
    }

}