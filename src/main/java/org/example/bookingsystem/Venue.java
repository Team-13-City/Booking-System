/**
 * Represents a venue in the Booking System.
 * This class models a venue with properties for identification, name, and capacity.
 */
package org.example.bookingsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class representing a venue in the booking system.
 * Uses JavaFX properties for observable values that can be bound to UI elements.
 */
public class Venue {
    /** The unique identifier for the venue */
    private IntegerProperty venueId;
    
    /** The name of the venue */
    private StringProperty name;
    
    /** The maximum capacity of the venue */
    private IntegerProperty capacity;

    /**
     * Constructs a new Venue with the specified properties.
     *
     * @param venueId The unique identifier for the venue
     * @param name The name of the venue
     * @param capacity The maximum capacity of the venue
     */
    public Venue(int venueId, String name, int capacity) {
        this.venueId = new SimpleIntegerProperty(venueId);
        this.name = new SimpleStringProperty(name);
        this.capacity = new SimpleIntegerProperty(capacity);
    }

    /**
     * Gets the venue ID.
     * @return The venue ID
     */
    public int getVenueId() { return venueId.get(); }
    
    /**
     * Sets the venue ID.
     * @param venueId The new venue ID
     */
    public void setVenueId(int venueId) { this.venueId.set(venueId); }
    
    /**
     * Gets the venue ID property for JavaFX binding.
     * @return The venue ID property
     */
    public IntegerProperty venueIdProperty() { return venueId; }

    /**
     * Gets the venue name.
     * @return The venue name
     */
    public String getName() { return name.get(); }
    
    /**
     * Sets the venue name.
     * @param name The new venue name
     */
    public void setName(String name) { this.name.set(name); }
    
    /**
     * Gets the name property for JavaFX binding.
     * @return The name property
     */
    public StringProperty nameProperty() { return name; }

    /**
     * Gets the venue capacity.
     * @return The venue capacity
     */
    public int getCapacity() { return capacity.get(); }
    
    /**
     * Sets the venue capacity.
     * @param capacity The new venue capacity
     */
    public void setCapacity(int capacity) { this.capacity.set(capacity); }
    
    /**
     * Gets the capacity property for JavaFX binding.
     * @return The capacity property
     */
    public IntegerProperty capacityProperty() { return capacity; }
}
