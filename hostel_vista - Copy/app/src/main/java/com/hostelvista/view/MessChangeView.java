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
    private VBox sidebar;
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
        // Sidebar
        sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #9B59FF; -fx-background-radius: 20;");
        sidebar.setPrefWidth(200);
        Label welcome = createSidebarLabel("Welcome!");
        Label dashboard = createSidebarLabel("Dashboard");
        Label mess = createSidebarLabel("Mess Change");
        Label settings = createSidebarLabel("Settings");
        sidebar.getChildren().addAll(welcome, dashboard, mess, settings);

        Label titleLabel = new Label("Mess Change Request");
        titleLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.web("#9B59FF"));

        // Fields and Labels
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

        VBox rightPane = new VBox(20, titleLabel, form);
        rightPane.setPadding(new Insets(40));
        rightPane.setAlignment(Pos.TOP_CENTER);
        rightPane.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20;");

        HBox root = new HBox(30, sidebar, rightPane);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #F2F2FF;");

        scene = new Scene(root,1560, 790);
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

    private Label createSidebarLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Poppins", FontWeight.BOLD, 16));
        label.setTextFill(Color.WHITE);
        return label;
    }

    private Label createFieldLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Poppins", FontWeight.SEMI_BOLD, 13));
        label.setTextFill(Color.web("#4B0082"));
        return label;
    }

    public Scene getScene() {
        return scene;
    }
}

