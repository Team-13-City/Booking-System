/**
 * Manages database connections for the Booking System.
 * Provides a centralized way to establish connections to the MySQL database.
 */
package org.example.bookingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A utility class for managing database connections.
 * Contains database connection details and provides methods to establish connections.
 */
public class DatabaseManager {

    /** The URL of the MySQL database */
    private static final String DB_URL = "jdbc:mysql://sst-stuproj.city.ac.uk:3306/in2033t13";
    
    /** The username for database authentication */
    private static final String DB_USERNAME = "in2033t13_a";
    
    /** The password for database authentication */
    private static final String DB_PASSWORD = "7WUufYkhtC8";

    /**
     * Establishes a connection to the MySQL database.
     *
     * @return A Connection object representing the database connection
     * @throws SQLException If a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}

