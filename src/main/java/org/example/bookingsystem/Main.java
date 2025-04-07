package org.example.bookingsystem;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main extends Application {

    private TabPane tabPane;
    private RevenueManager revenueManager = new RevenueManager();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Booking System - Employee Dashboard");

        tabPane = new TabPane();

        // Existing modules
        Tab customersTab = new Tab("Customers", createCustomerPane());
        Tab eventsTab = new Tab("Events", createEventPane());
        Tab ticketsTab = new Tab("Tickets", createTicketPane());
        Tab groupBookingsTab = new Tab("Group Bookings", createGroupBookingPane());
        Tab venuesTab = new Tab("Venues", createVenuePane());

        // New functionality modules
        Tab paymentTab = new Tab("Payments", createPaymentPane());
        Tab revenueTab = new Tab("Revenue Reports", createRevenuePane());
        Tab seatsTab = new Tab("Seats", createSeatsPane());
        Tab discountsTab = new Tab("Discounts", createDiscountsPane());

        tabPane.getTabs().addAll(customersTab, eventsTab, ticketsTab, groupBookingsTab, venuesTab,
                paymentTab, revenueTab, seatsTab, discountsTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane, 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ---------- Existing Module Panes (Customers, Events, Tickets, Group Bookings, Venues) ----------
    // (Implementations similar to your previous CRUD modules; see earlier examples.)

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
        // Add/Edit/Delete buttons (code omitted for brevity; see earlier sample)
        ToolBar toolbar = new ToolBar(new Button("Add"), new Button("Edit"), new Button("Delete"));
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

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
        ToolBar toolbar = new ToolBar(new Button("Add"), new Button("Edit"), new Button("Delete"));
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

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
        ToolBar toolbar = new ToolBar(new Button("Add"), new Button("Edit"), new Button("Delete"));
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

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
        ToolBar toolbar = new ToolBar(new Button("Add"), new Button("Edit"), new Button("Delete"));
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

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
        ToolBar toolbar = new ToolBar(new Button("Add"), new Button("Edit"), new Button("Delete"));
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

    // ---------- New Functionality Panes ----------

    // Payment Processing Pane – uses PaymentInfo and PaymentResult via PaymentService.
    private BorderPane createPaymentPane() {
        BorderPane pane = new BorderPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField cardField = new TextField();
        cardField.setPromptText("Card Number");
        TextField expiryField = new TextField();
        expiryField.setPromptText("Expiry Date (MM/YY)");
        TextField cvvField = new TextField();
        cvvField.setPromptText("CVV");
        TextField billingField = new TextField();
        billingField.setPromptText("Billing Address");
        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        grid.add(new Label("Card Number:"), 0, 0);
        grid.add(cardField, 1, 0);
        grid.add(new Label("Expiry Date:"), 0, 1);
        grid.add(expiryField, 1, 1);
        grid.add(new Label("CVV:"), 0, 2);
        grid.add(cvvField, 1, 2);
        grid.add(new Label("Billing Address:"), 0, 3);
        grid.add(billingField, 1, 3);
        grid.add(new Label("Amount:"), 0, 4);
        grid.add(amountField, 1, 4);

        Button processBtn = new Button("Process Payment");
        grid.add(processBtn, 1, 5);

        processBtn.setOnAction(e -> {
            PaymentInfo info = new PaymentInfo();
            info.cardNumber = cardField.getText();
            info.expiryDate = expiryField.getText();
            info.cvv = cvvField.getText();
            info.billingAddress = billingField.getText();
            double amount = Double.parseDouble(amountField.getText());
            PaymentResult result = PaymentService.processPayment(info, amount);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(result.success) {
                alert.setHeaderText("Payment Successful");
                alert.setContentText("Transaction ID: " + result.transactionID);
            } else {
                alert.setHeaderText("Payment Failed");
                alert.setContentText("Error: " + result.errorMessage);
            }
            alert.showAndWait();
        });

        pane.setCenter(grid);
        return pane;
    }

    // Revenue Reports Pane – uses RevenueManager and Report.
    private BorderPane createRevenuePane() {
        BorderPane pane = new BorderPane();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        // Daily Revenue Section
        HBox dailyBox = new HBox(10);
        Label dailyLabel = new Label("Select Date for Daily Revenue:");
        DatePicker datePicker = new DatePicker(LocalDate.now());
        Button dailyBtn = new Button("Calculate Revenue");
        Label dailyResult = new Label();
        dailyBox.getChildren().addAll(dailyLabel, datePicker, dailyBtn, dailyResult);

        dailyBtn.setOnAction(e -> {
            LocalDate date = datePicker.getValue();
            double revenue = revenueManager.calculateDailyRevenue(date);
            dailyResult.setText("Revenue: " + revenue);
        });

        // Monthly Report Section
        HBox monthlyBox = new HBox(10);
        Button monthlyBtn = new Button("Generate Monthly Report");
        TextArea reportArea = new TextArea();
        reportArea.setPrefRowCount(10);
        monthlyBox.getChildren().addAll(monthlyBtn);

        monthlyBtn.setOnAction(e -> {
            Report report = revenueManager.generateMonthlyReport();
            String reportCSV = report.exportAsCSV();
            reportArea.setText(reportCSV);
        });

        vbox.getChildren().addAll(dailyBox, new Separator(), monthlyBox, reportArea);
        pane.setCenter(vbox);
        return pane;
    }

    // Seats Pane – shows available seats and lets users reserve or release them.
    private BorderPane createSeatsPane() {
        BorderPane pane = new BorderPane();
        TableView<Seat> tableView = new TableView<>();
        TableColumn<Seat, String> seatIdCol = new TableColumn<>("Seat ID");
        seatIdCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().seatID));
        TableColumn<Seat, Boolean> reservedCol = new TableColumn<>("Reserved");
        reservedCol.setCellValueFactory(data -> new ReadOnlyBooleanWrapper(data.getValue().isReserved));
        TableColumn<Seat, String> viewCol = new TableColumn<>("View Quality");
        viewCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().viewQuality.name()));
        TableColumn<Seat, Boolean> accessibleCol = new TableColumn<>("Accessible");
        accessibleCol.setCellValueFactory(data -> new ReadOnlyBooleanWrapper(data.getValue().isAccessable));

        tableView.getColumns().addAll(seatIdCol, reservedCol, viewCol, accessibleCol);
        List<Seat> seats = SeatDAO.getAllSeats();
        tableView.getItems().setAll(seats);

        Button reserveBtn = new Button("Reserve Seat");
        reserveBtn.setOnAction(e -> {
            Seat selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null && !selected.isReserved) {
                selected.markReserved();
                SeatDAO.updateSeat(selected);
                tableView.refresh();
            } else {
                showAlert("Select an available seat to reserve.");
            }
        });

        Button releaseBtn = new Button("Release Seat");
        releaseBtn.setOnAction(e -> {
            Seat selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null && selected.isReserved) {
                selected.markReleased();
                SeatDAO.updateSeat(selected);
                tableView.refresh();
            } else {
                showAlert("Select a reserved seat to release.");
            }
        });

        ToolBar toolbar = new ToolBar(reserveBtn, releaseBtn);
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

    // Discounts Pane – provides CRUD operations for discount codes.
    private BorderPane createDiscountsPane() {
        BorderPane pane = new BorderPane();
        TableView<Discount> tableView = new TableView<>();
        TableColumn<Discount, String> codeCol = new TableColumn<>("Code");
        codeCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().code));
        TableColumn<Discount, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().type.name()));
        TableColumn<Discount, Double> valueCol = new TableColumn<>("Value");
        valueCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().value));
        TableColumn<Discount, LocalDate> fromCol = new TableColumn<>("Valid From");
        fromCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().validFrom));
        TableColumn<Discount, LocalDate> untilCol = new TableColumn<>("Valid Until");
        untilCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().validUntil));
        TableColumn<Discount, String> eligibilityCol = new TableColumn<>("Eligibility");
        eligibilityCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().eligibilityCriteria));

        tableView.getColumns().addAll(codeCol, typeCol, valueCol, fromCol, untilCol, eligibilityCol);
        tableView.getItems().setAll(DiscountDAO.getAllDiscounts());

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> {
            Dialog<Discount> dialog = new Dialog<>();
            dialog.setTitle("Add Discount");
            TextField codeField = new TextField();
            codeField.setPromptText("Code");
            ComboBox<String> typeBox = new ComboBox<>();
            typeBox.getItems().addAll("PERCENTAGE", "FIXED");
            TextField valueField = new TextField();
            valueField.setPromptText("Value");
            TextField fromField = new TextField();
            fromField.setPromptText("YYYY-MM-DD");
            TextField untilField = new TextField();
            untilField.setPromptText("YYYY-MM-DD");
            TextField eligibilityField = new TextField();
            eligibilityField.setPromptText("Eligibility Criteria");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.add(new Label("Code:"), 0, 0);
            grid.add(codeField, 1, 0);
            grid.add(new Label("Type:"), 0, 1);
            grid.add(typeBox, 1, 1);
            grid.add(new Label("Value:"), 0, 2);
            grid.add(valueField, 1, 2);
            grid.add(new Label("Valid From:"), 0, 3);
            grid.add(fromField, 1, 3);
            grid.add(new Label("Valid Until:"), 0, 4);
            grid.add(untilField, 1, 4);
            grid.add(new Label("Eligibility:"), 0, 5);
            grid.add(eligibilityField, 1, 5);
            dialog.getDialogPane().setContent(grid);

            ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveBtn) {
                    Discount discount = new Discount();
                    discount.code = codeField.getText();
                    discount.type = typeBox.getValue().equals("PERCENTAGE") ? Discount.Type.PERCENTAGE : Discount.Type.FIXED;
                    discount.value = Double.parseDouble(valueField.getText());
                    discount.validFrom = LocalDate.parse(fromField.getText());
                    discount.validUntil = LocalDate.parse(untilField.getText());
                    discount.eligibilityCriteria = eligibilityField.getText();
                    return discount;
                }
                return null;
            });

            dialog.showAndWait().ifPresent(discount -> {
                DiscountDAO.addDiscount(discount);
                tableView.getItems().setAll(DiscountDAO.getAllDiscounts());
            });
        });

        Button editBtn = new Button("Edit");
        editBtn.setOnAction(e -> {
            Discount selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Dialog<Discount> dialog = new Dialog<>();
                dialog.setTitle("Edit Discount");
                TextField codeField = new TextField(selected.code);
                codeField.setDisable(true);
                ComboBox<String> typeBox = new ComboBox<>();
                typeBox.getItems().addAll("PERCENTAGE", "FIXED");
                typeBox.setValue(selected.type.name());
                TextField valueField = new TextField(String.valueOf(selected.value));
                TextField fromField = new TextField(selected.validFrom.toString());
                TextField untilField = new TextField(selected.validUntil.toString());
                TextField eligibilityField = new TextField(selected.eligibilityCriteria);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.add(new Label("Code:"), 0, 0);
                grid.add(codeField, 1, 0);
                grid.add(new Label("Type:"), 0, 1);
                grid.add(typeBox, 1, 1);
                grid.add(new Label("Value:"), 0, 2);
                grid.add(valueField, 1, 2);
                grid.add(new Label("Valid From:"), 0, 3);
                grid.add(fromField, 1, 3);
                grid.add(new Label("Valid Until:"), 0, 4);
                grid.add(untilField, 1, 4);
                grid.add(new Label("Eligibility:"), 0, 5);
                grid.add(eligibilityField, 1, 5);
                dialog.getDialogPane().setContent(grid);

                ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(saveBtn, ButtonType.CANCEL);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == saveBtn) {
                        selected.type = typeBox.getValue().equals("PERCENTAGE") ? Discount.Type.PERCENTAGE : Discount.Type.FIXED;
                        selected.value = Double.parseDouble(valueField.getText());
                        selected.validFrom = LocalDate.parse(fromField.getText());
                        selected.validUntil = LocalDate.parse(untilField.getText());
                        selected.eligibilityCriteria = eligibilityField.getText();
                        return selected;
                    }
                    return null;
                });

                dialog.showAndWait().ifPresent(discount -> {
                    DiscountDAO.updateDiscount(discount);
                    tableView.getItems().setAll(DiscountDAO.getAllDiscounts());
                });
            } else {
                showAlert("Please select a discount to edit.");
            }
        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> {
            Discount selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (confirmDeletion("discount")) {
                    DiscountDAO.deleteDiscount(selected.code);
                    tableView.getItems().setAll(DiscountDAO.getAllDiscounts());
                }
            } else {
                showAlert("Please select a discount to delete.");
            }
        });

        ToolBar toolbar = new ToolBar(addBtn, editBtn, deleteBtn);
        pane.setTop(toolbar);
        pane.setCenter(tableView);
        return pane;
    }

    // Utility methods for alerts and deletion confirmation
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }

    private boolean confirmDeletion(String entity) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this " + entity + "?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
