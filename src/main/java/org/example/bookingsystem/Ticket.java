package org.example.bookingsystem;

import javafx.beans.property.*;
import java.time.LocalDateTime;

public class Ticket {
    private IntegerProperty ticketId;
    private IntegerProperty eventId;
    private IntegerProperty customerId;
    private IntegerProperty seatId;
    private DoubleProperty price;
    private StringProperty status; // ACTIVE, CANCELLED, REFUNDED
    private ObjectProperty<LocalDateTime> purchaseDate;

    public Ticket(int ticketId, int eventId, int customerId, int seatId, double price, String status, LocalDateTime purchaseDate) {
        this.ticketId = new SimpleIntegerProperty(ticketId);
        this.eventId = new SimpleIntegerProperty(eventId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.seatId = new SimpleIntegerProperty(seatId);
        this.price = new SimpleDoubleProperty(price);
        this.status = new SimpleStringProperty(status);
        this.purchaseDate = new SimpleObjectProperty<>(purchaseDate);
    }

    public int getTicketId() { return ticketId.get(); }
    public void setTicketId(int ticketId) { this.ticketId.set(ticketId); }
    public IntegerProperty ticketIdProperty() { return ticketId; }

    public int getEventId() { return eventId.get(); }
    public void setEventId(int eventId) { this.eventId.set(eventId); }
    public IntegerProperty eventIdProperty() { return eventId; }

    public int getCustomerId() { return customerId.get(); }
    public void setCustomerId(int customerId) { this.customerId.set(customerId); }
    public IntegerProperty customerIdProperty() { return customerId; }

    public int getSeatId() { return seatId.get(); }
    public void setSeatId(int seatId) { this.seatId.set(seatId); }
    public IntegerProperty seatIdProperty() { return seatId; }

    public double getPrice() { return price.get(); }
    public void setPrice(double price) { this.price.set(price); }
    public DoubleProperty priceProperty() { return price; }

    public String getStatus() { return status.get(); }
    public void setStatus(String status) { this.status.set(status); }
    public StringProperty statusProperty() { return status; }

    public LocalDateTime getPurchaseDate() { return purchaseDate.get(); }
    public void setPurchaseDate(LocalDateTime purchaseDate) { this.purchaseDate.set(purchaseDate); }
    public ObjectProperty<LocalDateTime> purchaseDateProperty() { return purchaseDate; }
}
