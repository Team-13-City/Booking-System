/**
 * Represents a customer in the Booking System.
 * This class models a customer with properties for identification, personal information,
 * role, and membership status.
 */
package org.example.bookingsystem;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class representing a customer in the booking system.
 * Uses JavaFX properties for observable values that can be bound to UI elements.
 */
public class Customer {
    /** The unique identifier for the customer */
    private IntegerProperty customerId;
    
    /** The name of the customer */
    private StringProperty name;
    
    /** The email address of the customer */
    private StringProperty email;
    
    /** The role of the customer (e.g., PATRON, STAFF, VIP, MARKETING) */
    private StringProperty role;
    
    /** Whether the customer is a member of Friends of Landcaster */
    private BooleanProperty friends;

    /**
     * Constructs a new Customer with the specified properties.
     *
     * @param customerId The unique identifier for the customer
     * @param name The name of the customer
     * @param email The email address of the customer
     * @param role The role of the customer
     * @param friends Whether the customer is a member of Friends of Landcaster
     */
    public Customer(int customerId, String name, String email, String role, boolean friends) {
        this.customerId = new SimpleIntegerProperty(customerId);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.role = new SimpleStringProperty(role);
        this.friends = new SimpleBooleanProperty(friends);
    }

    /**
     * Gets the customer ID.
     * @return The customer ID
     */
    public int getCustomerId() {
        return customerId.get();
    }

    /**
     * Sets the customer ID.
     * @param customerId The new customer ID
     */
    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    /**
     * Gets the customer ID property for JavaFX binding.
     * @return The customer ID property
     */
    public IntegerProperty customerIdProperty() {
        return customerId;
    }

    /**
     * Gets the customer name.
     * @return The customer name
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the customer name.
     * @param name The new customer name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Gets the name property for JavaFX binding.
     * @return The name property
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Gets the customer email.
     * @return The customer email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * Sets the customer email.
     * @param email The new customer email
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * Gets the email property for JavaFX binding.
     * @return The email property
     */
    public StringProperty emailProperty() {
        return email;
    }

    /**
     * Gets the customer role.
     * @return The customer role
     */
    public String getRole() {
        return role.get();
    }

    /**
     * Sets the customer role.
     * @param role The new customer role
     */
    public void setRole(String role) {
        this.role.set(role);
    }

    /**
     * Gets the role property for JavaFX binding.
     * @return The role property
     */
    public StringProperty roleProperty() {
        return role;
    }

    /**
     * Checks if the customer is a member of Friends of Landcaster.
     * @return true if the customer is a member, false otherwise
     */
    public boolean isFriends() {
        return friends.get();
    }

    /**
     * Sets the Friends of Landcaster membership status.
     * @param friends The new membership status
     */
    public void setFriends(boolean friends) {
        this.friends.set(friends);
    }

    /**
     * Gets the friends property for JavaFX binding.
     * @return The friends property
     */
    public BooleanProperty friendsProperty() {
        return friends;
    }
}
