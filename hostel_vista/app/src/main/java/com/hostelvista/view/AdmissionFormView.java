package com.hostelvista.view;

import com.hostelvista.controller.AdmissionFormController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AdmissionFormView {

    public TextField fullName = new TextField();
    public DatePicker dob = new DatePicker();
    public ComboBox<String> gender = new ComboBox<>();
    public ComboBox<String> nationality = new ComboBox<>();
    public TextField address = new TextField();
    public TextField city = new TextField();
    public TextField postalCode = new TextField();
    public TextField phone = new TextField();
    public TextField email = new TextField();
    public ComboBox<String> program = new ComboBox<>();
    public ComboBox<String> year = new ComboBox<>();

    public TextField fatherName = new TextField(), fatherOcc = new TextField(), fatherPhone = new TextField();
    public TextField motherName = new TextField(), motherOcc = new TextField(), motherPhone = new TextField();
    public TextField guardianName = new TextField(), guardianPhone = new TextField();
    public ComboBox<String> guardianRelation = new ComboBox<>();
    public TextField emergencyName = new TextField(), emergencyPhone = new TextField();
    public ComboBox<String> emergencyRel = new ComboBox<>();
    public TextField conditions = new TextField(), allergies = new TextField(), additionalInfo = new TextField();
    public DatePicker declarationDate = new DatePicker();

    public Button submitBtn = new Button("SUBMIT");
    public Button backBtn = new Button("Back");

    private AdmissionFormController controller;

    public AdmissionFormView(Stage stage) {}

    public Scene getScene(Stage stage) {
        controller = new AdmissionFormController(this, stage);

        gender.getItems().addAll("Male", "Female", "Other");
        nationality.getItems().addAll("Indian", "American", "Japanese", "Other");
        program.getItems().addAll("Science", "Commerce", "Arts");
        year.getItems().addAll("1st year", "2nd year", "3rd year", "4th year");
        guardianRelation.getItems().addAll("Father", "Mother", "Uncle", "Aunt", "Other");
        emergencyRel.getItems().addAll("Father", "Mother", "Guardian", "Other");

        Label title = new Label("Admission Form");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 46));
        title.setTextFill(Color.web("#6A1B9A"));
        title.setPadding(new Insets(10));
        title.setAlignment(Pos.CENTER);
        title.setBackground(new Background(new BackgroundFill(Color.web("#c98bd4ff"), new CornerRadii(20), Insets.EMPTY)));
        title.setMaxWidth(Double.MAX_VALUE);
       
       
        VBox leftCol = new VBox(18,
                row("Full Name", fullName),
                row("Date of Birth", dob),
                row("Gender", gender),
                row("Nationality", nationality),
                row("Address", address),
                row("City", city),
                row("Postal Code", postalCode),
                row("Phone Number", phone),
                row("Email", email),
                row("Preferred Program", program),
                row("Admission Year", year),

                // Moved from right column
                row("Father's Name", fatherName),
                row("Father's Occupation", fatherOcc),
                row("Father's Phone", fatherPhone)
        );

        VBox rightCol = new VBox(18,
                row("Mother's Name", motherName),
                row("Mother's Occupation", motherOcc),
                row("Mother's Phone", motherPhone),
                row("Guardian Name", guardianName),
                row("Guardian Relation", guardianRelation),
                row("Guardian Phone", guardianPhone),
                row("Emergency Name", emergencyName),
                row("Emergency Phone", emergencyPhone),
                row("Emergency Relation", emergencyRel),
                row("Medical Conditions", conditions),
                row("Allergies", allergies),
                row("How did you hear about us?", additionalInfo),
                row("Admission Date", declarationDate)
        );

        leftCol.setPadding(new Insets(20));
        rightCol.setPadding(new Insets(20));

        Line divider = new Line(0, 0, 0, 800);
        divider.setStrokeWidth(1.7);
        divider.setStroke(Color.LIGHTGRAY);
        VBox dividerBox = new VBox(divider);
        dividerBox.setAlignment(Pos.CENTER);

        HBox formSplit = new HBox(60, leftCol, dividerBox, rightCol);
        formSplit.setAlignment(Pos.TOP_CENTER);
        formSplit.setPadding(new Insets(20));

        // Buttons
        backBtn.setStyle("-fx-background-color: #b08df5ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 40;");
        submitBtn.setStyle("-fx-background-color: #7F3DFF; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 40;");
        backBtn.setPrefWidth(120);
        submitBtn.setPrefWidth(120);

        HBox buttonBox = new HBox(20, backBtn, submitBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        VBox layout = new VBox(30, title, formSplit, buttonBox);
        layout.setStyle("-fx-background-color: #EFEAFE;");
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        return new Scene(scrollPane, 1560, 790);
    }

    private HBox row(String labelText, Control input) {
        Label label = new Label(labelText);
        label.setStyle("-fx-text-fill: #3F3F3F;");
        label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        input.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #DAD2FF; -fx-border-radius: 6; -fx-background-radius: 6;");
        input.setPrefHeight(36);
        input.setPrefWidth(260);

        label.setMinWidth(180);
        HBox row = new HBox(10, label, input);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }
}
