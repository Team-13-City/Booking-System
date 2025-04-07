package org.example.bookingsystem;

import javafx.beans.property.*;

public class Customer {
    private IntegerProperty customerId;
    private StringProperty name;
    private StringProperty email;
    private StringProperty role;
    private BooleanProperty friends;

    public Customer(int customerId, String name, String email, String role, boolean friends) {
        this.customerId = new SimpleIntegerProperty(customerId);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.role = new SimpleStringProperty(role);
        this.friends = new SimpleBooleanProperty(friends);
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public IntegerProperty customerIdProperty() {
        return customerId;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public StringProperty roleProperty() {
        return role;
    }

    public boolean isFriends() {
        return friends.get();
    }

    public void setFriends(boolean friends) {
        this.friends.set(friends);
    }

    public BooleanProperty friendsProperty() {
        return friends;
    }
}
