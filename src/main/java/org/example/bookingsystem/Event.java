/**
 * Represents an event in the Booking System.
 * This class models an event with properties for identification, title, scheduling,
 * venue association, layout type, and visibility status.
 */
package org.example.bookingsystem;

import java.time.LocalDateTime;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class representing an event in the booking system.
 * Uses JavaFX properties for observable values that can be bound to UI elements.
 */
public class Event {
    /** The unique identifier for the event */
    private IntegerProperty eventId;
    
    /** The title of the event */
    private StringProperty title;
    
    /** The date and time when the event takes place */
    private ObjectProperty<LocalDateTime> dateTime;
    
    /** The ID of the venue where the event takes place */
    private IntegerProperty venueId;
    
    /** The type of seating layout for the event */
    private StringProperty layoutType;
    
    /** Whether the event is public or private */
    private BooleanProperty isPublic;

    /**
     * Constructs a new Event with the specified properties.
     *
     * @param eventId The unique identifier for the event
     * @param title The title of the event
     * @param dateTime The date and time when the event takes place
     * @param venueId The ID of the venue where the event takes place
     * @param layoutType The type of seating layout for the event
     * @param isPublic Whether the event is public or private
     */
    public Event(int eventId, String title, LocalDateTime dateTime, int venueId, String layoutType, boolean isPublic) {
        this.eventId = new SimpleIntegerProperty(eventId);
        this.title = new SimpleStringProperty(title);
        this.dateTime = new SimpleObjectProperty<>(dateTime);
        this.venueId = new SimpleIntegerProperty(venueId);
        this.layoutType = new SimpleStringProperty(layoutType);
        this.isPublic = new SimpleBooleanProperty(isPublic);
    }

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
     * Gets the event title.
     * @return The event title
     */
    public String getTitle() { return title.get(); }
    
    /**
     * Sets the event title.
     * @param title The new event title
     */
    public void setTitle(String title) { this.title.set(title); }
    
    /**
     * Gets the title property for JavaFX binding.
     * @return The title property
     */
    public StringProperty titleProperty() { return title; }

    /**
     * Gets the event date and time.
     * @return The event date and time
     */
    public LocalDateTime getDateTime() { return dateTime.get(); }
    
    /**
     * Sets the event date and time.
     * @param dateTime The new event date and time
     */
    public void setDateTime(LocalDateTime dateTime) { this.dateTime.set(dateTime); }
    
    /**
     * Gets the date and time property for JavaFX binding.
     * @return The date and time property
     */
    public ObjectProperty<LocalDateTime> dateTimeProperty() { return dateTime; }

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
     * Gets the layout type.
     * @return The layout type
     */
    public String getLayoutType() { return layoutType.get(); }
    
    /**
     * Sets the layout type.
     * @param layoutType The new layout type
     */
    public void setLayoutType(String layoutType) { this.layoutType.set(layoutType); }
    
    /**
     * Gets the layout type property for JavaFX binding.
     * @return The layout type property
     */
    public StringProperty layoutTypeProperty() { return layoutType; }

    /**
     * Checks if the event is public.
     * @return true if the event is public, false otherwise
     */
    public boolean isPublic() { return isPublic.get(); }
    
    /**
     * Sets the public status of the event.
     * @param isPublic The new public status
     */
    public void setPublic(boolean isPublic) { this.isPublic.set(isPublic); }
    
    /**
     * Gets the public status property for JavaFX binding.
     * @return The public status property
     */
    public BooleanProperty isPublicProperty() { return isPublic; }
}
