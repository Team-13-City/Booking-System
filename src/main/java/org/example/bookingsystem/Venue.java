package org.example.bookingsystem;

import javafx.beans.property.*;

public class Venue {
    private IntegerProperty venueId;
    private StringProperty name;
    private IntegerProperty capacity;

    public Venue(int venueId, String name, int capacity) {
        this.venueId = new SimpleIntegerProperty(venueId);
        this.name = new SimpleStringProperty(name);
        this.capacity = new SimpleIntegerProperty(capacity);
    }

    public int getVenueId() { return venueId.get(); }
    public void setVenueId(int venueId) { this.venueId.set(venueId); }
    public IntegerProperty venueIdProperty() { return venueId; }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public int getCapacity() { return capacity.get(); }
    public void setCapacity(int capacity) { this.capacity.set(capacity); }
    public IntegerProperty capacityProperty() { return capacity; }
}
