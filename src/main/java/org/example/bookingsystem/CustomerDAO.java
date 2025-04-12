/**
 * Data Access Object (DAO) for managing customer data in the database.
 * Provides methods for CRUD operations on customers.
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
 * A class that handles all database operations related to customers.
 * Implements the Data Access Object pattern for customer management.
 */
public class CustomerDAO {

    /**
     * Retrieves all customers from the database.
     *
     * @return A list of all customers in the database
     */
    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("customer_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String role = rs.getString("role");
                boolean friends = rs.getBoolean("friends_of_landcaster");
                customers.add(new Customer(id, name, email, role, friends));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Adds a new customer to the database.
     *
     * @param customer The customer to be added
     */
    public static void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (name, email, role, friends_of_landcaster) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getRole());
            pstmt.setBoolean(4, customer.isFriends());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setCustomerId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing customer in the database.
     *
     * @param customer The customer with updated information
     */
    public static void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET name = ?, email = ?, role = ?, friends_of_landcaster = ? WHERE customer_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getRole());
            pstmt.setBoolean(4, customer.isFriends());
            pstmt.setInt(5, customer.getCustomerId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a customer from the database.
     *
     * @param customerId The ID of the customer to be deleted
     */
    public static void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
