package com.hostelvista.view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ComplaintView {
    public ComboBox<String> complaintType;
    public TextArea description;
    public Button submitButton;
    public Button backButton;
    public VBox layout;

    public ComplaintView() {
        Label title = new Label("Anonymous Complaint Form");
        title.setFont(Font.font("Poppins", 22));
        title.setTextFill(Color.web("#7748c6"));
        title.setAlignment(Pos.CENTER);

        complaintType = new ComboBox<>();
        complaintType.getItems().addAll(
                "Mess Food", "Room Cleanliness", "Warden Issue",
                "Noise Disturbance", "Electricity/Water", "Other"
        );
        complaintType.setValue("Other");
        complaintType.setMaxWidth(300);
        complaintType.setStyle("-fx-background-color: #EEE6FF; -fx-text-fill: #4B0082;");

        description = new TextArea();
        description.setPromptText("Enter complaint details...");
        description.setPrefHeight(120);
        description.setWrapText(true);
        description.setMaxWidth(300);
        description.setStyle("-fx-border-radius: 15; -fx-background-radius: 15;");

        submitButton = new Button("SUBMIT");
        submitButton.setStyle("-fx-background-color: #b583f3; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 20;");
        submitButton.setPrefWidth(100);

        backButton = new Button("BACK");
        backButton.setStyle("-fx-background-color: #f0e6ff; -fx-text-fill: #7748c6; -fx-font-weight: bold; -fx-background-radius: 20;");
        backButton.setPrefWidth(100);

        HBox buttonRow = new HBox(20, backButton, submitButton);
        buttonRow.setAlignment(Pos.CENTER);

        layout = new VBox(20,
                title,
                new Label("Complaint Type:"), complaintType,
                new Label("Description:"), description,
                buttonRow
        );
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setMaxWidth(500);
        layout.setStyle("-fx-border-radius: 30; -fx-background-radius: 30; -fx-background-color: white;");
    }

    public Scene getScene() {
        StackPane root = new StackPane(layout);
        root.setStyle("-fx-background-color: #f9f5fc;");
        return new Scene(root, 1560, 790);
    }
}
