package org.example.bookingsystem.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LancasterUI extends Application {

    private Scene loginScene(Stage stage) {
        VBox loginLayout = new VBox(10);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(20));

        Label title = new Label("Lancaster Booking System");
        title.setFont(new Font("Arial", 24));

        Label signInLabel = new Label("Sign In");
        signInLabel.setFont(new Font("Arial", 18));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Sign In");
        loginButton.setOnAction(e -> stage.setScene(mainDashboardScene(stage)));

        loginLayout.getChildren().addAll(title, signInLabel, usernameField, passwordField, loginButton);

        return new Scene(loginLayout, 400, 300);
    }

    private Scene mainDashboardScene(Stage stage) {
        BorderPane layout = new BorderPane();

        Label header = new Label("Lancaster Booking System");
        header.setFont(new Font("Arial", 20));
        header.setPadding(new Insets(10));

        TabPane tabPane = new TabPane();

        tabPane.getTabs().addAll(
                createTicketsTab(),
                createCustomersTab(),
                createEventsTab(),
                new Tab("Meetings")
        );

        layout.setTop(header);
        layout.setCenter(tabPane);

        return new Scene(layout, 800, 600);
    }

    private Tab createTicketsTab() {
        Tab tab = new Tab("Tickets");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TableView<String> table = new TableView<>(); // Placeholder
        Label label = new Label("Ticket Sales");
        Button newSale = new Button("New Sale");

        layout.getChildren().addAll(label, table, newSale);
        tab.setContent(layout);
        return tab;
    }

    private Tab createCustomersTab() {
        Tab tab = new Tab("Customers");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TableView<String> table = new TableView<>(); // Placeholder
        Label label = new Label("Customers");
        Button addCustomer = new Button("Add Customer");

        layout.getChildren().addAll(label, table, addCustomer);
        tab.setContent(layout);
        return tab;
    }

    private Tab createEventsTab() {
        Tab tab = new Tab("Events");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label label = new Label("Upcoming Events");
        TextArea eventDetails = new TextArea();
        eventDetails.setPromptText("Add event details...");
        Button addEvent = new Button("Add Event");

        layout.getChildren().addAll(label, eventDetails, addEvent);
        tab.setContent(layout);
        return tab;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lancaster Booking System");
        primaryStage.setScene(loginScene(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
