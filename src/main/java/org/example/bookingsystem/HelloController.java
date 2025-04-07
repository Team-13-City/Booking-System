package org.example.bookingsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private ComboBox<String> eventComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Spinner<Integer> ticketSpinner;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    private List<Booking> bookings = new ArrayList<>();

    @FXML
    public void initialize() {
        // Initialize event options
        ObservableList<String> events = FXCollections.observableArrayList(
                "Concert",
                "Theater Show",
                "Sports Event",
                "Conference",
                "Exhibition"
        );
        eventComboBox.setItems(events);

        // Set default date to today
        datePicker.setValue(LocalDate.now());

        // Initialize ticket spinner
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        ticketSpinner.setValueFactory(valueFactory);
    }

    @FXML
    protected void onBookTicketClick() {
        if (validateInput()) {
            Booking booking = new Booking(
                    eventComboBox.getValue(),
                    datePicker.getValue(),
                    ticketSpinner.getValue(),
                    nameField.getText(),
                    emailField.getText()
            );

            bookings.add(booking);

            showAlert("Success", "Tickets booked successfully!\n" +
                    "Event: " + booking.getEvent() + "\n" +
                    "Date: " + booking.getDate() + "\n" +
                    "Tickets: " + booking.getNumberOfTickets());

            clearForm();
        }
    }

    @FXML
    protected void onViewBookingsClick() {
        if (bookings.isEmpty()) {
            showAlert("Bookings", "No bookings found.");
            return;
        }

        StringBuilder bookingDetails = new StringBuilder("Your Bookings:\n\n");
        for (Booking booking : bookings) {
            String email = booking.getEmail();
            if (email.matches("^[\\w.-]+@(?:gmail\\.com|abv\\.com)$") ) {
            bookingDetails.append("Event: ").append(booking.getEvent()).append("\n")
                    .append("Date: ").append(booking.getDate()).append("\n")
                    .append("Tickets: ").append(booking.getNumberOfTickets()).append("\n")
                    .append("Name: ").append(booking.getName()).append("\n")
                    .append("Email: ").append(booking.getEmail()).append("\n\n");
            } else {
                bookingDetails.append("Invalid email for booking: ").append(email).append("\n\n");
            }
            } 

        showAlert("Your Bookings", bookingDetails.toString());
    }

    @FXML
    protected void onHelpClick() {
        showAlert("Help",
                "How to use the Booking System:\n\n" +
                        "1. Select an event from the dropdown\n" +
                        "2. Choose a date\n" +
                        "3. Select the number of tickets\n" +
                        "4. Enter your name and email\n" +
                        "5. Click 'Book Tickets' to complete your booking\n\n" +
                        "You can view your bookings by clicking 'View My Bookings'");
    }

    private boolean validateInput() {
        if (eventComboBox.getValue() == null) {
            showAlert("Error", "Please select an event");
            return false;
        }

        if (datePicker.getValue() == null) {
            showAlert("Error", "Please select a date");
            return false;
        }

        if (nameField.getText().isEmpty()) {
            showAlert("Error", "Please enter your name");
            return false;
        }

        if (emailField.getText().isEmpty() || !emailField.getText().contains("@")) {
            showAlert("Error", "Please enter a valid email address");
            return false;
        }

        return true;
    }

    private void clearForm() {
        eventComboBox.setValue(null);
        datePicker.setValue(LocalDate.now());
        ticketSpinner.getValueFactory().setValue(1);
        nameField.clear();
        emailField.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private static class Booking {
        private final String event;
        private final LocalDate date;
        private final int numberOfTickets;
        private final String name;
        private final String email;

        public Booking(String event, LocalDate date, int numberOfTickets, String name, String email) {
            this.event = event;
            this.date = date;
            this.numberOfTickets = numberOfTickets;
            this.name = name;
            this.email = email;
        }

        public String getEvent() { return event; }
        public LocalDate getDate() { return date; }
        public int getNumberOfTickets() { return numberOfTickets; }
        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
