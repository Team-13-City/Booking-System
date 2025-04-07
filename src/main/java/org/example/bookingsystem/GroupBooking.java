package org.example.bookingsystem;

import javafx.beans.property.*;
import java.time.LocalDate;

public class GroupBooking {
    private IntegerProperty groupId;
    private IntegerProperty eventId;
    private IntegerProperty size;
    private StringProperty seatPreferences;
    private StringProperty paymentStatus;
    private ObjectProperty<LocalDate> deadline;

    public GroupBooking(int groupId, int eventId, int size, String seatPreferences, String paymentStatus, LocalDate deadline) {
        this.groupId = new SimpleIntegerProperty(groupId);
        this.eventId = new SimpleIntegerProperty(eventId);
        this.size = new SimpleIntegerProperty(size);
        this.seatPreferences = new SimpleStringProperty(seatPreferences);
        this.paymentStatus = new SimpleStringProperty(paymentStatus);
        this.deadline = new SimpleObjectProperty<>(deadline);
    }

    public int getGroupId() { return groupId.get(); }
    public void setGroupId(int groupId) { this.groupId.set(groupId); }
    public IntegerProperty groupIdProperty() { return groupId; }

    public int getEventId() { return eventId.get(); }
    public void setEventId(int eventId) { this.eventId.set(eventId); }
    public IntegerProperty eventIdProperty() { return eventId; }

    public int getSize() { return size.get(); }
    public void setSize(int size) { this.size.set(size); }
    public IntegerProperty sizeProperty() { return size; }

    public String getSeatPreferences() { return seatPreferences.get(); }
    public void setSeatPreferences(String seatPreferences) { this.seatPreferences.set(seatPreferences); }
    public StringProperty seatPreferencesProperty() { return seatPreferences; }

    public String getPaymentStatus() { return paymentStatus.get(); }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus.set(paymentStatus); }
    public StringProperty paymentStatusProperty() { return paymentStatus; }

    public LocalDate getDeadline() { return deadline.get(); }
    public void setDeadline(LocalDate deadline) { this.deadline.set(deadline); }
    public ObjectProperty<LocalDate> deadlineProperty() { return deadline; }
}
