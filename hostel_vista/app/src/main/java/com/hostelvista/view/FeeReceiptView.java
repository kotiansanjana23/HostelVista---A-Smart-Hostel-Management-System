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
    private Button backButton;
    private Scene scene;

    public FeeReceiptView(Stage stage) {
        // Title and Receipt Info
        Label titleLabel = new Label("Hostel Fee Receipt");
        titleLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web("#9B59FF"));

        Label receiptId = createReceiptLabel("Receipt ID: HSTL20250721001");
        Label studentName = createReceiptLabel("Student Name: N/A");
        Label studentId = createReceiptLabel("Student ID: 22CO123");
        Label roomNo = createReceiptLabel("Room Number: A-203");
        Label amountPaid = createReceiptLabel("Amount Paid: ₹56,000");
        Label paymentMode = createReceiptLabel("Payment Mode: UPI");
        Label paymentDate = createReceiptLabel("Date: 21-07-2025");

        VBox infoBox = new VBox(10, receiptId, studentName, studentId, roomNo, amountPaid, paymentMode, paymentDate);
        infoBox.setPadding(new Insets(30));
        infoBox.setStyle("-fx-background-color: #EFEAFE; -fx-background-radius: 20; -fx-border-color: #9B59FF; -fx-border-radius: 20;");
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.setMaxWidth(700); // increased width

        // Buttons
        downloadButton = new Button("Download PDF");
        downloadButton.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        downloadButton.setStyle("-fx-background-color: #9B59FF; -fx-text-fill: white; -fx-background-radius: 20;");

        backButton = new Button("BACK");
        backButton.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        backButton.setStyle("-fx-background-color: #EFEAFE; -fx-text-fill: #9B59FF; -fx-background-radius: 20;");

        HBox buttonBox = new HBox(20, backButton, downloadButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Layout (centered without sidebar)
        VBox centerContent = new VBox(25, titleLabel, infoBox, buttonBox);
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setPadding(new Insets(50));
        centerContent.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20;");
        centerContent.setMaxWidth(800);

        StackPane root = new StackPane(centerContent); // centering
        root.setStyle("-fx-background-color: #F2F2FF;");
        root.setPadding(new Insets(30));

        rootLayout = new VBox(root);
        scene = new Scene(rootLayout, 1560, 790);

        new FeeReceiptController(this, stage);
    }

    private Label createReceiptLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Poppins", 16));
        label.setTextFill(Color.web("#3F3F3F"));
        return label;
    }

    public VBox getReceiptLayout() {
        return receiptLayout;
    }

    public Button getDownloadButton() {
        return downloadButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Scene getScene() {
        return scene;
    }
}
