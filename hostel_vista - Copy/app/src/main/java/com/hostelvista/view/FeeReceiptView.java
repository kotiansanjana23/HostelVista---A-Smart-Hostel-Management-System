package com.hostelvista.view;

import com.hostelvista.controller.FeeReceiptController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FeeReceiptView {

    private VBox receiptLayout;
    private VBox rootLayout;
    private Button downloadButton;
    private Scene scene;

    public FeeReceiptView(Stage stage) {
        // Sidebar
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #9B59FF; -fx-background-radius: 20;");
        sidebar.setPrefWidth(200);

        Label welcome = createSidebarLabel("Welcome !");
        Label equipment = createSidebarLabel("Hostel Information");
        Label notifications = createSidebarLabel("Notifications");
        Label settings = createSidebarLabel("Settings");
        sidebar.getChildren().addAll(welcome, equipment, notifications, settings);

        // Title and Receipt Info
        Label titleLabel = new Label("Hostel Fee Receipt");
        titleLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.web("#9B59FF"));

        Label receiptId = createReceiptLabel("Receipt ID: HSTL20250721001");
        Label studentName = createReceiptLabel("Student Name: N/A");
        Label studentId = createReceiptLabel("Student ID: 22CO123");
        Label roomNo = createReceiptLabel("Room Number: A-203");
        Label amountPaid = createReceiptLabel("Amount Paid: ₹20,000");
        Label paymentMode = createReceiptLabel("Payment Mode: UPI");
        Label paymentDate = createReceiptLabel("Date: 21-07-2025");

        VBox infoBox = new VBox(10, receiptId, studentName, studentId, roomNo, amountPaid, paymentMode, paymentDate);
        infoBox.setPadding(new Insets(20));
        infoBox.setStyle("-fx-background-color: #EFEAFE; -fx-background-radius: 20; -fx-border-color: #9B59FF; -fx-border-radius: 20;");
        infoBox.setAlignment(Pos.CENTER_LEFT);

        receiptLayout = new VBox(15, titleLabel, infoBox);
        receiptLayout.setAlignment(Pos.CENTER);
        receiptLayout.setStyle("-fx-padding: 20;");

        downloadButton = new Button("Download PDF");
        downloadButton.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        downloadButton.setStyle("-fx-background-color: #9B59FF; -fx-text-fill: white; -fx-background-radius: 20;");

        VBox rightPane = new VBox(20, titleLabel, infoBox, downloadButton);
        rightPane.setPadding(new Insets(40));
        rightPane.setAlignment(Pos.TOP_CENTER);
        rightPane.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20;");

        HBox root = new HBox(30, sidebar, rightPane);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #F2F2FF;");

        rootLayout = new VBox(root);
        scene = new Scene(rootLayout, 850, 500);

        // Attach controller
        new FeeReceiptController(this, stage);
    }

    private Label createReceiptLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Poppins", 14));
        label.setTextFill(Color.web("#3F3F3F"));
        return label;
    }

    private Label createSidebarLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Poppins", FontWeight.BOLD, 16));
        label.setTextFill(Color.WHITE);
        return label;
    }

    public VBox getReceiptLayout() {
        return receiptLayout;
    }

    public Button getDownloadButton() {
        return downloadButton;
    }

    public Scene getScene() {
        return scene;
    }
}
