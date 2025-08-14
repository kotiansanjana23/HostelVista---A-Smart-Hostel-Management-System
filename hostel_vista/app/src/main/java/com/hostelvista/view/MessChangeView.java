package com.hostelvista.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MessChangeView {
    private VBox form;
    private Scene scene;

    public TextField nameField = new TextField();
    public TextField idField = new TextField();
    public TextField roomField = new TextField();
    public ComboBox<String> currentMessBox = new ComboBox<>();
    public ComboBox<String> newMessBox = new ComboBox<>();
    public TextArea reasonArea = new TextArea();
    public Button submitBtn = new Button("Submit Request");
    public Button backBtn = new Button("Back");

    public MessChangeView() {
        buildUI();
    }

    private void buildUI() {
        Label titleLabel = new Label("Mess Change Request");
        titleLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.web("#9B59FF"));

        setupInputFields();

        HBox buttonBox = new HBox(10, backBtn, submitBtn);
        buttonBox.setAlignment(Pos.CENTER);

        form = new VBox(10,
                createFieldLabel("Student Name:"), nameField,
                createFieldLabel("Student ID:"), idField,
                createFieldLabel("Room Number:"), roomField,
                createFieldLabel("Current Mess:"), currentMessBox,
                createFieldLabel("Requested New Mess:"), newMessBox,
                createFieldLabel("Reason for mess change:"), reasonArea,
                buttonBox);
        form.setPadding(new Insets(20));
        form.setStyle("-fx-background-color: #EFEAFE; -fx-background-radius: 20;");
        form.setAlignment(Pos.CENTER);
        form.setMaxWidth(400);

        VBox centerPane = new VBox(20, titleLabel, form);
        centerPane.setAlignment(Pos.CENTER);
        centerPane.setPadding(new Insets(30));
        centerPane.setStyle("-fx-background-color: #F2F2FF;");

        StackPane root = new StackPane(centerPane);
        root.setAlignment(Pos.CENTER);

        scene = new Scene(root, 1560, 790);
    }

    private void setupInputFields() {
        nameField.setPromptText("Enter your name");
        idField.setPromptText("Enter your ID");
        roomField.setPromptText("Enter your room number");

        currentMessBox.getItems().addAll("East Mess", "North Mess", "South Mess", "West Mess", "Special Mess");
        currentMessBox.setPromptText("Select Mess");

        newMessBox.getItems().addAll("East Mess", "North Mess", "South Mess", "West Mess", "Special Mess");
        newMessBox.setPromptText("Select Mess");

        reasonArea.setPromptText("Why do you want to change your mess?");
        reasonArea.setWrapText(true);
        reasonArea.setPrefRowCount(4);
    }

    private Label createFieldLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Poppins", FontWeight.SEMI_BOLD, 13));
        label.setTextFill(Color.web("#4B0082"));
        return label;
    }

    public Pane getRoot() {
        return (Pane) scene.getRoot(); // needed for controller’s StackPane wrapping
    }

    public Scene getScene() {
        return scene;
    }
}
