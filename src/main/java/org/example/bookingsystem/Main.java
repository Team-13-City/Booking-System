/**
 * The main application class for the Booking System.
 * This class extends JavaFX Application and provides the user interface
 * for managing customers, events, tickets, group bookings, and venues.
 */
package org.example.bookingsystem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The main application class that extends JavaFX Application.
 * Provides a tabbed interface for managing different aspects of the booking system.
 */
public class Main extends Application {

    /** The main tab pane that contains all the system's tabs */
    private TabPane tabPane;

    /**
     * Initializes and starts the JavaFX application.
     * Sets up the main window with tabs for different management sections.
     *
     * @param primaryStage The primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Booking System - Employee Dashboard");

        tabPane = new TabPane();

        // Create tabs for each module
        Tab customersTab = new Tab("Customers", createCustomerPane());
        Tab eventsTab = new Tab("Events", createEventPane());
        Tab ticketsTab = new Tab("Tickets", createTicketPane());
        Tab groupBookingsTab = new Tab("Group Bookings", createGroupBookingPane());
        Tab venuesTab = new Tab("Venues", createVenuePane());

        tabPane.getTabs().addAll(customersTab, eventsTab, ticketsTab, groupBookingsTab, venuesTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates and returns a pane for managing customers.
     * Includes a table view of customers and buttons for CRUD operations.
     *
     * @return A BorderPane containing the customer management interface
     */
    private BorderPane createCustomerPane() {
        BorderPane pane = new BorderPane();
        TableView<Customer> tableView = new TableView<>();

        TableColumn<Customer, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customer, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Customer, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<Customer, Boolean> friendsCol = new TableColumn<>("Friends");
        friendsCol.setCellValueFactory(new PropertyValueFactory<>("friends"));

        tableView.getColumns().addAll(idCol, nameCol, emailCol, roleCol, friendsCol);
        tableView.getItems().setAll(CustomerDAO.getAllCustomers());

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> {
            Dialog<Customer> dialog = new Dialog<>();
            dialog.setTitle("Add Customer");
            TextField nameField = new TextField();
            nameField.setPromptText("Name");
            TextField emailField = new TextField();
            emailField.setPromptText("Email");
            ComboBox<String> roleBox = new ComboBox<>();
            roleBox.getItems().addAll("PATRON", "STAFF", "VIP", "MARKETING");
            CheckBox friendsCheck = new CheckBox("Friends of Landcaster");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("Email:"), 0, 1);
            grid.add(emailField, 1, 1);
            grid.add(new Label("Role:"), 0, 2);
            grid.add(roleBox, 1, 2);
            grid.add(friendsCheck, 1, 3);
            dialog.getDialogPane().setContent(grid);

            ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveBtn) {
                    return new Customer(0, nameField.getText(), emailField.getText(), roleBox.getValue(), friendsCheck.isSelected());
                }
                return null;
            });

            dialog.showAndWait().ifPresent(customer -> {
                CustomerDAO.addCustomer(customer);
                tableView.getItems().setAll(CustomerDAO.getAllCustomers());
            });
        });

        Button editBtn = new Button("Edit");
        editBtn.setOnAction(e -> {
            Customer selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Dialog<Customer> dialog = new Dialog<>();
                dialog.setTitle("Edit Customer");
                TextField nameField = new TextField(selected.getName());
                TextField emailField = new TextField(selected.getEmail());
                ComboBox<String> roleBox = new ComboBox<>();
                roleBox.getItems().addAll("PATRON", "STAFF", "VIP", "MARKETING");
                roleBox.setValue(selected.getRole());
                CheckBox friendsCheck = new CheckBox("Friends of Landcaster");
                friendsCheck.setSelected(selected.isFriends());

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.add(new Label("Name:"), 0, 0);
                grid.add(nameField, 1, 0);
                grid.add(new Label("Email:"), 0, 1);
                grid.add(emailField, 1, 1);
                grid.add(new Label("Role:"), 0, 2);
                grid.add(roleBox, 1, 2);
                grid.add(friendsCheck, 1, 3);
                dialog.getDialogPane().setContent(grid);

                ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == saveBtn) {
                        selected.setName(nameField.getText());
                        selected.setEmail(emailField.getText());
                        selected.setRole(roleBox.getValue());
                        selected.setFriends(friendsCheck.isSelected());
                        return selected;
                    }
                    return null;
                });

                dialog.showAndWait().ifPresent(customer -> {
                    CustomerDAO.updateCustomer(customer);
                    tableView.getItems().setAll(CustomerDAO.getAllCustomers());
                });
            } else {
                showAlert("Please select a customer to edit.");
            }
        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> {
            Customer selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (confirmDeletion("customer")) {
                    CustomerDAO.deleteCustomer(selected.getCustomerId());
                    tableView.getItems().setAll(CustomerDAO.getAllCustomers());
                }
            } else {
                showAlert("Please select a customer to delete.");
            }
        });

        ToolBar toolbar = new ToolBar(addBtn, editBtn, deleteBtn);
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

    /**
     * Creates and returns a pane for managing events.
     * Includes a table view of events and buttons for CRUD operations.
     *
     * @return A BorderPane containing the event management interface
     */
    private BorderPane createEventPane() {
        BorderPane pane = new BorderPane();
        TableView<Event> tableView = new TableView<>();

        TableColumn<Event, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("eventId"));

        TableColumn<Event, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Event, LocalDateTime> dateCol = new TableColumn<>("Date/Time");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

        TableColumn<Event, Integer> venueCol = new TableColumn<>("Venue ID");
        venueCol.setCellValueFactory(new PropertyValueFactory<>("venueId"));

        TableColumn<Event, String> layoutCol = new TableColumn<>("Layout");
        layoutCol.setCellValueFactory(new PropertyValueFactory<>("layoutType"));

        TableColumn<Event, Boolean> publicCol = new TableColumn<>("Public");
        publicCol.setCellValueFactory(new PropertyValueFactory<>("public"));

        tableView.getColumns().addAll(idCol, titleCol, dateCol, venueCol, layoutCol, publicCol);
        tableView.getItems().setAll(EventDAO.getAllEvents());

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> {
            Dialog<Event> dialog = new Dialog<>();
            dialog.setTitle("Add Event");
            TextField titleField = new TextField();
            titleField.setPromptText("Title");
            TextField dateTimeField = new TextField();
            dateTimeField.setPromptText("YYYY-MM-DDTHH:MM");
            TextField venueIdField = new TextField();
            venueIdField.setPromptText("Venue ID");
            TextField layoutField = new TextField();
            layoutField.setPromptText("Layout Type");
            CheckBox publicCheck = new CheckBox("Public");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.add(new Label("Title:"), 0, 0);
            grid.add(titleField, 1, 0);
            grid.add(new Label("Date/Time:"), 0, 1);
            grid.add(dateTimeField, 1, 1);
            grid.add(new Label("Venue ID:"), 0, 2);
            grid.add(venueIdField, 1, 2);
            grid.add(new Label("Layout:"), 0, 3);
            grid.add(layoutField, 1, 3);
            grid.add(publicCheck, 1, 4);
            dialog.getDialogPane().setContent(grid);

            ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveBtn) {
                    return new Event(
                            0,
                            titleField.getText(),
                            LocalDateTime.parse(dateTimeField.getText()),
                            Integer.parseInt(venueIdField.getText()),
                            layoutField.getText(),
                            publicCheck.isSelected()
                    );
                }
                return null;
            });

            dialog.showAndWait().ifPresent(event -> {
                EventDAO.addEvent(event);
                tableView.getItems().setAll(EventDAO.getAllEvents());
            });
        });

        Button editBtn = new Button("Edit");
        editBtn.setOnAction(e -> {
            Event selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Dialog<Event> dialog = new Dialog<>();
                dialog.setTitle("Edit Event");
                TextField titleField = new TextField(selected.getTitle());
                TextField dateTimeField = new TextField(selected.getDateTime().toString());
                TextField venueIdField = new TextField(String.valueOf(selected.getVenueId()));
                TextField layoutField = new TextField(selected.getLayoutType());
                CheckBox publicCheck = new CheckBox("Public");
                publicCheck.setSelected(selected.isPublic());

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.add(new Label("Title:"), 0, 0);
                grid.add(titleField, 1, 0);
                grid.add(new Label("Date/Time:"), 0, 1);
                grid.add(dateTimeField, 1, 1);
                grid.add(new Label("Venue ID:"), 0, 2);
                grid.add(venueIdField, 1, 2);
                grid.add(new Label("Layout:"), 0, 3);
                grid.add(layoutField, 1, 3);
                grid.add(publicCheck, 1, 4);
                dialog.getDialogPane().setContent(grid);

                ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == saveBtn) {
                        selected.setTitle(titleField.getText());
                        selected.setDateTime(LocalDateTime.parse(dateTimeField.getText()));
                        selected.setVenueId(Integer.parseInt(venueIdField.getText()));
                        selected.setLayoutType(layoutField.getText());
                        selected.setPublic(publicCheck.isSelected());
                        return selected;
                    }
                    return null;
                });

                dialog.showAndWait().ifPresent(event -> {
                    EventDAO.updateEvent(event);
                    tableView.getItems().setAll(EventDAO.getAllEvents());
                });
            } else {
                showAlert("Please select an event to edit.");
            }
        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> {
            Event selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (confirmDeletion("event")) {
                    EventDAO.deleteEvent(selected.getEventId());
                    tableView.getItems().setAll(EventDAO.getAllEvents());
                }
            } else {
                showAlert("Please select an event to delete.");
            }
        });

        ToolBar toolbar = new ToolBar(addBtn, editBtn, deleteBtn);
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

    /**
     * Creates and returns a pane for managing tickets.
     * Includes a table view of tickets and buttons for CRUD operations.
     *
     * @return A BorderPane containing the ticket management interface
     */
    private BorderPane createTicketPane() {
        BorderPane pane = new BorderPane();
        TableView<Ticket> tableView = new TableView<>();

        TableColumn<Ticket, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ticketId"));

        TableColumn<Ticket, Integer> eventIdCol = new TableColumn<>("Event ID");
        eventIdCol.setCellValueFactory(new PropertyValueFactory<>("eventId"));

        TableColumn<Ticket, Integer> customerIdCol = new TableColumn<>("Customer ID");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        TableColumn<Ticket, Integer> seatIdCol = new TableColumn<>("Seat ID");
        seatIdCol.setCellValueFactory(new PropertyValueFactory<>("seatId"));

        TableColumn<Ticket, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Ticket, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Ticket, LocalDateTime> purchaseDateCol = new TableColumn<>("Purchase Date");
        purchaseDateCol.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));

        tableView.getColumns().addAll(idCol, eventIdCol, customerIdCol, seatIdCol, priceCol, statusCol, purchaseDateCol);
        tableView.getItems().setAll(TicketDAO.getAllTickets());

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> {
            Dialog<Ticket> dialog = new Dialog<>();
            dialog.setTitle("Add Ticket");
            TextField eventIdField = new TextField();
            eventIdField.setPromptText("Event ID");
            TextField customerIdField = new TextField();
            customerIdField.setPromptText("Customer ID");
            TextField seatIdField = new TextField();
            seatIdField.setPromptText("Seat ID");
            TextField priceField = new TextField();
            priceField.setPromptText("Price");
            ComboBox<String> statusBox = new ComboBox<>();
            statusBox.getItems().addAll("ACTIVE", "CANCELLED", "REFUNDED");
            TextField purchaseDateField = new TextField();
            purchaseDateField.setPromptText("YYYY-MM-DDTHH:MM");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.add(new Label("Event ID:"), 0, 0);
            grid.add(eventIdField, 1, 0);
            grid.add(new Label("Customer ID:"), 0, 1);
            grid.add(customerIdField, 1, 1);
            grid.add(new Label("Seat ID:"), 0, 2);
            grid.add(seatIdField, 1, 2);
            grid.add(new Label("Price:"), 0, 3);
            grid.add(priceField, 1, 3);
            grid.add(new Label("Status:"), 0, 4);
            grid.add(statusBox, 1, 4);
            grid.add(new Label("Purchase Date:"), 0, 5);
            grid.add(purchaseDateField, 1, 5);
            dialog.getDialogPane().setContent(grid);

            ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveBtn) {
                    return new Ticket(
                            0,
                            Integer.parseInt(eventIdField.getText()),
                            Integer.parseInt(customerIdField.getText()),
                            Integer.parseInt(seatIdField.getText()),
                            Double.parseDouble(priceField.getText()),
                            statusBox.getValue(),
                            LocalDateTime.parse(purchaseDateField.getText())
                    );
                }
                return null;
            });

            dialog.showAndWait().ifPresent(ticket -> {
                TicketDAO.addTicket(ticket);
                tableView.getItems().setAll(TicketDAO.getAllTickets());
            });
        });

        Button editBtn = new Button("Edit");
        editBtn.setOnAction(e -> {
            Ticket selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Dialog<Ticket> dialog = new Dialog<>();
                dialog.setTitle("Edit Ticket");
                TextField eventIdField = new TextField(String.valueOf(selected.getEventId()));
                TextField customerIdField = new TextField(String.valueOf(selected.getCustomerId()));
                TextField seatIdField = new TextField(String.valueOf(selected.getSeatId()));
                TextField priceField = new TextField(String.valueOf(selected.getPrice()));
                ComboBox<String> statusBox = new ComboBox<>();
                statusBox.getItems().addAll("ACTIVE", "CANCELLED", "REFUNDED");
                statusBox.setValue(selected.getStatus());
                TextField purchaseDateField = new TextField(selected.getPurchaseDate().toString());

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.add(new Label("Event ID:"), 0, 0);
                grid.add(eventIdField, 1, 0);
                grid.add(new Label("Customer ID:"), 0, 1);
                grid.add(customerIdField, 1, 1);
                grid.add(new Label("Seat ID:"), 0, 2);
                grid.add(seatIdField, 1, 2);
                grid.add(new Label("Price:"), 0, 3);
                grid.add(priceField, 1, 3);
                grid.add(new Label("Status:"), 0, 4);
                grid.add(statusBox, 1, 4);
                grid.add(new Label("Purchase Date:"), 0, 5);
                grid.add(purchaseDateField, 1, 5);
                dialog.getDialogPane().setContent(grid);

                ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == saveBtn) {
                        selected.setEventId(Integer.parseInt(eventIdField.getText()));
                        selected.setCustomerId(Integer.parseInt(customerIdField.getText()));
                        selected.setSeatId(Integer.parseInt(seatIdField.getText()));
                        selected.setPrice(Double.parseDouble(priceField.getText()));
                        selected.setStatus(statusBox.getValue());
                        selected.setPurchaseDate(LocalDateTime.parse(purchaseDateField.getText()));
                        return selected;
                    }
                    return null;
                });

                dialog.showAndWait().ifPresent(ticket -> {
                    TicketDAO.updateTicket(ticket);
                    tableView.getItems().setAll(TicketDAO.getAllTickets());
                });
            } else {
                showAlert("Please select a ticket to edit.");
            }
        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> {
            Ticket selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (confirmDeletion("ticket")) {
                    TicketDAO.deleteTicket(selected.getTicketId());
                    tableView.getItems().setAll(TicketDAO.getAllTickets());
                }
            } else {
                showAlert("Please select a ticket to delete.");
            }
        });

        ToolBar toolbar = new ToolBar(addBtn, editBtn, deleteBtn);
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

    /**
     * Creates and returns a pane for managing group bookings.
     * Includes a table view of group bookings and buttons for CRUD operations.
     *
     * @return A BorderPane containing the group booking management interface
     */
    private BorderPane createGroupBookingPane() {
        BorderPane pane = new BorderPane();
        TableView<GroupBooking> tableView = new TableView<>();

        TableColumn<GroupBooking, Integer> idCol = new TableColumn<>("Group ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("groupId"));

        TableColumn<GroupBooking, Integer> eventIdCol = new TableColumn<>("Event ID");
        eventIdCol.setCellValueFactory(new PropertyValueFactory<>("eventId"));

        TableColumn<GroupBooking, Integer> sizeCol = new TableColumn<>("Size");
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));

        TableColumn<GroupBooking, String> prefCol = new TableColumn<>("Seat Preferences");
        prefCol.setCellValueFactory(new PropertyValueFactory<>("seatPreferences"));

        TableColumn<GroupBooking, String> paymentCol = new TableColumn<>("Payment Status");
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

        TableColumn<GroupBooking, LocalDate> deadlineCol = new TableColumn<>("Deadline");
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));

        tableView.getColumns().addAll(idCol, eventIdCol, sizeCol, prefCol, paymentCol, deadlineCol);
        tableView.getItems().setAll(GroupBookingDAO.getAllGroupBookings());

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> {
            Dialog<GroupBooking> dialog = new Dialog<>();
            dialog.setTitle("Add Group Booking");
            TextField eventIdField = new TextField();
            eventIdField.setPromptText("Event ID");
            TextField sizeField = new TextField();
            sizeField.setPromptText("Size");
            TextField seatPrefField = new TextField();
            seatPrefField.setPromptText("Seat Preferences");
            TextField paymentField = new TextField();
            paymentField.setPromptText("Payment Status");
            TextField deadlineField = new TextField();
            deadlineField.setPromptText("YYYY-MM-DD");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.add(new Label("Event ID:"), 0, 0);
            grid.add(eventIdField, 1, 0);
            grid.add(new Label("Size:"), 0, 1);
            grid.add(sizeField, 1, 1);
            grid.add(new Label("Seat Preferences:"), 0, 2);
            grid.add(seatPrefField, 1, 2);
            grid.add(new Label("Payment Status:"), 0, 3);
            grid.add(paymentField, 1, 3);
            grid.add(new Label("Deadline:"), 0, 4);
            grid.add(deadlineField, 1, 4);
            dialog.getDialogPane().setContent(grid);

            ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveBtn) {
                    return new GroupBooking(
                            0,
                            Integer.parseInt(eventIdField.getText()),
                            Integer.parseInt(sizeField.getText()),
                            seatPrefField.getText(),
                            paymentField.getText(),
                            LocalDate.parse(deadlineField.getText())
                    );
                }
                return null;
            });

            dialog.showAndWait().ifPresent(booking -> {
                GroupBookingDAO.addGroupBooking(booking);
                tableView.getItems().setAll(GroupBookingDAO.getAllGroupBookings());
            });
        });

        Button editBtn = new Button("Edit");
        editBtn.setOnAction(e -> {
            GroupBooking selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Dialog<GroupBooking> dialog = new Dialog<>();
                dialog.setTitle("Edit Group Booking");
                TextField eventIdField = new TextField(String.valueOf(selected.getEventId()));
                TextField sizeField = new TextField(String.valueOf(selected.getSize()));
                TextField seatPrefField = new TextField(selected.getSeatPreferences());
                TextField paymentField = new TextField(selected.getPaymentStatus());
                TextField deadlineField = new TextField(selected.getDeadline().toString());

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.add(new Label("Event ID:"), 0, 0);
                grid.add(eventIdField, 1, 0);
                grid.add(new Label("Size:"), 0, 1);
                grid.add(sizeField, 1, 1);
                grid.add(new Label("Seat Preferences:"), 0, 2);
                grid.add(seatPrefField, 1, 2);
                grid.add(new Label("Payment Status:"), 0, 3);
                grid.add(paymentField, 1, 3);
                grid.add(new Label("Deadline:"), 0, 4);
                grid.add(deadlineField, 1, 4);
                dialog.getDialogPane().setContent(grid);

                ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == saveBtn) {
                        selected.setEventId(Integer.parseInt(eventIdField.getText()));
                        selected.setSize(Integer.parseInt(sizeField.getText()));
                        selected.setSeatPreferences(seatPrefField.getText());
                        selected.setPaymentStatus(paymentField.getText());
                        selected.setDeadline(LocalDate.parse(deadlineField.getText()));
                        return selected;
                    }
                    return null;
                });

                dialog.showAndWait().ifPresent(booking -> {
                    GroupBookingDAO.updateGroupBooking(booking);
                    tableView.getItems().setAll(GroupBookingDAO.getAllGroupBookings());
                });
            } else {
                showAlert("Please select a group booking to edit.");
            }
        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> {
            GroupBooking selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (confirmDeletion("group booking")) {
                    GroupBookingDAO.deleteGroupBooking(selected.getGroupId());
                    tableView.getItems().setAll(GroupBookingDAO.getAllGroupBookings());
                }
            } else {
                showAlert("Please select a group booking to delete.");
            }
        });

        ToolBar toolbar = new ToolBar(addBtn, editBtn, deleteBtn);
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

    /**
     * Creates and returns a pane for managing venues.
     * Includes a table view of venues and buttons for CRUD operations.
     *
     * @return A BorderPane containing the venue management interface
     */
    private BorderPane createVenuePane() {
        BorderPane pane = new BorderPane();
        TableView<Venue> tableView = new TableView<>();

        TableColumn<Venue, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("venueId"));

        TableColumn<Venue, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Venue, Integer> capacityCol = new TableColumn<>("Capacity");
        capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        tableView.getColumns().addAll(idCol, nameCol, capacityCol);
        tableView.getItems().setAll(VenueDAO.getAllVenues());

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> {
            Dialog<Venue> dialog = new Dialog<>();
            dialog.setTitle("Add Venue");
            TextField nameField = new TextField();
            nameField.setPromptText("Name");
            TextField capacityField = new TextField();
            capacityField.setPromptText("Capacity");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("Capacity:"), 0, 1);
            grid.add(capacityField, 1, 1);
            dialog.getDialogPane().setContent(grid);

            ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveBtn) {
                    return new Venue(0, nameField.getText(), Integer.parseInt(capacityField.getText()));
                }
                return null;
            });

            dialog.showAndWait().ifPresent(venue -> {
                VenueDAO.addVenue(venue);
                tableView.getItems().setAll(VenueDAO.getAllVenues());
            });
        });

        Button editBtn = new Button("Edit");
        editBtn.setOnAction(e -> {
            Venue selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Dialog<Venue> dialog = new Dialog<>();
                dialog.setTitle("Edit Venue");
                TextField nameField = new TextField(selected.getName());
                TextField capacityField = new TextField(String.valueOf(selected.getCapacity()));

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.add(new Label("Name:"), 0, 0);
                grid.add(nameField, 1, 0);
                grid.add(new Label("Capacity:"), 0, 1);
                grid.add(capacityField, 1, 1);
                dialog.getDialogPane().setContent(grid);

                ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == saveBtn) {
                        selected.setName(nameField.getText());
                        selected.setCapacity(Integer.parseInt(capacityField.getText()));
                        return selected;
                    }
                    return null;
                });

                dialog.showAndWait().ifPresent(venue -> {
                    VenueDAO.updateVenue(venue);
                    tableView.getItems().setAll(VenueDAO.getAllVenues());
                });
            } else {
                showAlert("Please select a venue to edit.");
            }
        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> {
            Venue selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (confirmDeletion("venue")) {
                    VenueDAO.deleteVenue(selected.getVenueId());
                    tableView.getItems().setAll(VenueDAO.getAllVenues());
                }
            } else {
                showAlert("Please select a venue to delete.");
            }
        });

        ToolBar toolbar = new ToolBar(addBtn, editBtn, deleteBtn);
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

    /**
     * Displays an alert dialog with the specified message.
     *
     * @param message The message to display in the alert
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Shows a confirmation dialog for deletion operations.
     *
     * @param entity The type of entity being deleted (e.g., "customer", "event")
     * @return true if the user confirms the deletion, false otherwise
     */
    private boolean confirmDeletion(String entity) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this " + entity + "?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    /**
     * The main entry point for the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
