package org.example.bookingsystem;

import javafx.beans.property.*;
import java.time.LocalDateTime;

public class Event {
    private IntegerProperty eventId;
    private StringProperty title;
    private ObjectProperty<LocalDateTime> dateTime;
    private IntegerProperty venueId;
    private StringProperty layoutType;
    private BooleanProperty isPublic;

    public Event(int eventId, String title, LocalDateTime dateTime, int venueId, String layoutType, boolean isPublic) {
        this.eventId = new SimpleIntegerProperty(eventId);
        this.title = new SimpleStringProperty(title);
        this.dateTime = new SimpleObjectProperty<>(dateTime);
        this.venueId = new SimpleIntegerProperty(venueId);
        this.layoutType = new SimpleStringProperty(layoutType);
        this.isPublic = new SimpleBooleanProperty(isPublic);
    }

    public int getEventId() { return eventId.get(); }
    public void setEventId(int eventId) { this.eventId.set(eventId); }
    public IntegerProperty eventIdProperty() { return eventId; }

    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return title; }

    public LocalDateTime getDateTime() { return dateTime.get(); }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime.set(dateTime); }
    public ObjectProperty<LocalDateTime> dateTimeProperty() { return dateTime; }

    public int getVenueId() { return venueId.get(); }
    public void setVenueId(int venueId) { this.venueId.set(venueId); }
    public IntegerProperty venueIdProperty() { return venueId; }

    public String getLayoutType() { return layoutType.get(); }
    public void setLayoutType(String layoutType) { this.layoutType.set(layoutType); }
    public StringProperty layoutTypeProperty() { return layoutType; }

    public boolean isPublic() { return isPublic.get(); }
    public void setPublic(boolean isPublic) { this.isPublic.set(isPublic); }
    public BooleanProperty isPublicProperty() { return isPublic; }
}
