/**
 * Represents a ticket in the Booking System.
 * This class models a ticket with properties for identification, event association,
 * customer ownership, seat assignment, pricing, status, and purchase date.
 */
package org.example.bookingsystem;

import java.time.LocalDateTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class representing a ticket in the booking system.
 * Uses JavaFX properties for observable values that can be bound to UI elements.
 */
public class Ticket {
    /** The unique identifier for the ticket */
    private IntegerProperty ticketId;
    
    /** The ID of the event this ticket is for */
    private IntegerProperty eventId;
    
    /** The ID of the customer who owns this ticket */
    private IntegerProperty customerId;
    
    /** The ID of the seat assigned to this ticket */
    private IntegerProperty seatId;
    
    /** The price of the ticket */
    private DoubleProperty price;
    
    /** The current status of the ticket (ACTIVE, CANCELLED, REFUNDED) */
    private StringProperty status;
    
    /** The date and time when the ticket was purchased */
    private ObjectProperty<LocalDateTime> purchaseDate;

    /**
     * Constructs a new Ticket with the specified properties.
     *
     * @param ticketId The unique identifier for the ticket
     * @param eventId The ID of the event this ticket is for
     * @param customerId The ID of the customer who owns this ticket
     * @param seatId The ID of the seat assigned to this ticket
     * @param price The price of the ticket
     * @param status The current status of the ticket
     * @param purchaseDate The date and time when the ticket was purchased
     */
    public Ticket(int ticketId, int eventId, int customerId, int seatId, double price, String status, LocalDateTime purchaseDate) {
        this.ticketId = new SimpleIntegerProperty(ticketId);
        this.eventId = new SimpleIntegerProperty(eventId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.seatId = new SimpleIntegerProperty(seatId);
        this.price = new SimpleDoubleProperty(price);
        this.status = new SimpleStringProperty(status);
        this.purchaseDate = new SimpleObjectProperty<>(purchaseDate);
    }

    /**
     * Gets the ticket ID.
     * @return The ticket ID
     */
    public int getTicketId() { return ticketId.get(); }
    
    /**
     * Sets the ticket ID.
     * @param ticketId The new ticket ID
     */
    public void setTicketId(int ticketId) { this.ticketId.set(ticketId); }
    
    /**
     * Gets the ticket ID property for JavaFX binding.
     * @return The ticket ID property
     */
    public IntegerProperty ticketIdProperty() { return ticketId; }

    /**
     * Gets the event ID.
     * @return The event ID
     */
    public int getEventId() { return eventId.get(); }
    
    /**
     * Sets the event ID.
     * @param eventId The new event ID
     */
    public void setEventId(int eventId) { this.eventId.set(eventId); }
    
    /**
     * Gets the event ID property for JavaFX binding.
     * @return The event ID property
     */
    public IntegerProperty eventIdProperty() { return eventId; }

    /**
     * Gets the customer ID.
     * @return The customer ID
     */
    public int getCustomerId() { return customerId.get(); }
    
    /**
     * Sets the customer ID.
     * @param customerId The new customer ID
     */
    public void setCustomerId(int customerId) { this.customerId.set(customerId); }
    
    /**
     * Gets the customer ID property for JavaFX binding.
     * @return The customer ID property
     */
    public IntegerProperty customerIdProperty() { return customerId; }

    /**
     * Gets the seat ID.
     * @return The seat ID
     */
    public int getSeatId() { return seatId.get(); }
    
    /**
     * Sets the seat ID.
     * @param seatId The new seat ID
     */
    public void setSeatId(int seatId) { this.seatId.set(seatId); }
    
    /**
     * Gets the seat ID property for JavaFX binding.
     * @return The seat ID property
     */
    public IntegerProperty seatIdProperty() { return seatId; }

    /**
     * Gets the ticket price.
     * @return The ticket price
     */
    public double getPrice() { return price.get(); }
    
    /**
     * Sets the ticket price.
     * @param price The new ticket price
     */
    public void setPrice(double price) { this.price.set(price); }
    
    /**
     * Gets the price property for JavaFX binding.
     * @return The price property
     */
    public DoubleProperty priceProperty() { return price; }

    /**
     * Gets the ticket status.
     * @return The ticket status
     */
    public String getStatus() { return status.get(); }
    
    /**
     * Sets the ticket status.
     * @param status The new ticket status
     */
    public void setStatus(String status) { this.status.set(status); }
    
    /**
     * Gets the status property for JavaFX binding.
     * @return The status property
     */
    public StringProperty statusProperty() { return status; }

    /**
     * Gets the purchase date.
     * @return The purchase date
     */
    public LocalDateTime getPurchaseDate() { return purchaseDate.get(); }
    
    /**
     * Sets the purchase date.
     * @param purchaseDate The new purchase date
     */
    public void setPurchaseDate(LocalDateTime purchaseDate) { this.purchaseDate.set(purchaseDate); }
    
    /**
     * Gets the purchase date property for JavaFX binding.
     * @return The purchase date property
     */
    public ObjectProperty<LocalDateTime> purchaseDateProperty() { return purchaseDate; }
}
