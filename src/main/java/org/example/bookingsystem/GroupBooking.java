/**
 * Represents a group booking in the Booking System.
 * This class models a group booking with properties for identification, event association,
 * group size, seat preferences, payment status, and deadline.
 */
package org.example.bookingsystem;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class representing a group booking in the booking system.
 * Uses JavaFX properties for observable values that can be bound to UI elements.
 */
public class GroupBooking {
    /** The unique identifier for the group booking */
    private IntegerProperty groupId;
    
    /** The ID of the event this group booking is for */
    private IntegerProperty eventId;
    
    /** The size of the group */
    private IntegerProperty size;
    
    /** The seating preferences for the group */
    private StringProperty seatPreferences;
    
    /** The current payment status of the group booking */
    private StringProperty paymentStatus;
    
    /** The deadline for the group booking */
    private ObjectProperty<LocalDate> deadline;

    /**
     * Constructs a new GroupBooking with the specified properties.
     *
     * @param groupId The unique identifier for the group booking
     * @param eventId The ID of the event this group booking is for
     * @param size The size of the group
     * @param seatPreferences The seating preferences for the group
     * @param paymentStatus The current payment status of the group booking
     * @param deadline The deadline for the group booking
     */
    public GroupBooking(int groupId, int eventId, int size, String seatPreferences, String paymentStatus, LocalDate deadline) {
        this.groupId = new SimpleIntegerProperty(groupId);
        this.eventId = new SimpleIntegerProperty(eventId);
        this.size = new SimpleIntegerProperty(size);
        this.seatPreferences = new SimpleStringProperty(seatPreferences);
        this.paymentStatus = new SimpleStringProperty(paymentStatus);
        this.deadline = new SimpleObjectProperty<>(deadline);
    }

    /**
     * Gets the group ID.
     * @return The group ID
     */
    public int getGroupId() { return groupId.get(); }
    
    /**
     * Sets the group ID.
     * @param groupId The new group ID
     */
    public void setGroupId(int groupId) { this.groupId.set(groupId); }
    
    /**
     * Gets the group ID property for JavaFX binding.
     * @return The group ID property
     */
    public IntegerProperty groupIdProperty() { return groupId; }

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
     * Gets the group size.
     * @return The group size
     */
    public int getSize() { return size.get(); }
    
    /**
     * Sets the group size.
     * @param size The new group size
     */
    public void setSize(int size) { this.size.set(size); }
    
    /**
     * Gets the size property for JavaFX binding.
     * @return The size property
     */
    public IntegerProperty sizeProperty() { return size; }

    /**
     * Gets the seat preferences.
     * @return The seat preferences
     */
    public String getSeatPreferences() { return seatPreferences.get(); }
    
    /**
     * Sets the seat preferences.
     * @param seatPreferences The new seat preferences
     */
    public void setSeatPreferences(String seatPreferences) { this.seatPreferences.set(seatPreferences); }
    
    /**
     * Gets the seat preferences property for JavaFX binding.
     * @return The seat preferences property
     */
    public StringProperty seatPreferencesProperty() { return seatPreferences; }

    /**
     * Gets the payment status.
     * @return The payment status
     */
    public String getPaymentStatus() { return paymentStatus.get(); }
    
    /**
     * Sets the payment status.
     * @param paymentStatus The new payment status
     */
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus.set(paymentStatus); }
    
    /**
     * Gets the payment status property for JavaFX binding.
     * @return The payment status property
     */
    public StringProperty paymentStatusProperty() { return paymentStatus; }

    /**
     * Gets the deadline.
     * @return The deadline
     */
    public LocalDate getDeadline() { return deadline.get(); }
    
    /**
     * Sets the deadline.
     * @param deadline The new deadline
     */
    public void setDeadline(LocalDate deadline) { this.deadline.set(deadline); }
    
    /**
     * Gets the deadline property for JavaFX binding.
     * @return The deadline property
     */
    public ObjectProperty<LocalDate> deadlineProperty() { return deadline; }
}
