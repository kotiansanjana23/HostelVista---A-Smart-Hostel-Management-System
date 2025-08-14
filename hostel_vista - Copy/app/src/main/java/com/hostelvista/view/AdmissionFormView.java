package com.hostelvista.view;

import com.google.cloud.firestore.Firestore;
import com.hostelvista.Authentication.FirebaseInitializer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class AdmissionFormView {

    public GridPane grid = new GridPane();
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

    public AdmissionFormView(Stage stage) {
        // Event handling logic moved here
        submitBtn.setOnAction(e -> {
            FirebaseInitializer.initialize();
            Map<String, Object> data = new HashMap<>();

            // Personal Info
            data.put("fullName", fullName.getText());
            data.put("dob", dob.getValue() != null ? dob.getValue().toString() : "");
            data.put("gender", gender.getValue());
            data.put("nationality", nationality.getValue());
            data.put("address", address.getText());
            data.put("city", city.getText());
            data.put("postalCode", postalCode.getText());
            data.put("phone", phone.getText());
            data.put("email", email.getText());

            // Academic Info
            data.put("program", program.getValue());
            data.put("admissionYear", year.getValue());

            // Parent Info
            data.put("fatherName", fatherName.getText());
            data.put("fatherOccupation", fatherOcc.getText());
            data.put("fatherPhone", fatherPhone.getText());
            data.put("motherName", motherName.getText());
            data.put("motherOccupation", motherOcc.getText());
            data.put("motherPhone", motherPhone.getText());
            data.put("guardianName", guardianName.getText());
            data.put("guardianRelation", guardianRelation.getValue());
            data.put("guardianPhone", guardianPhone.getText());

            // Emergency Info
            data.put("emergencyName", emergencyName.getText());
            data.put("emergencyRelation", emergencyRel.getValue());
            data.put("emergencyPhone", emergencyPhone.getText());

            // Medical & Additional
            data.put("medicalConditions", conditions.getText());
            data.put("allergies", allergies.getText());
            data.put("additionalInfo", additionalInfo.getText());
            data.put("admissionDate", declarationDate.getValue() != null ? declarationDate.getValue().toString() : "");

            Firestore db = FirebaseInitializer.getDB();

            new Thread(() -> {
                try {
                    db.collection("admissions").add(data);
                    System.out.println("Submitting data: " + data.toString());
                    System.out.println("✅ Submitted to Firestore: " + data);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("❌ Failed to submit form");
                }
            }).start();
        });
    }

    public Scene getScene(Stage primaryStage) {
        String labelStyle = "-fx-text-fill: #3F3F3F; -fx-font-size: 13px;";
        String inputStyle = "-fx-background-color: #FFFFFF; -fx-border-color: #DAD2FF; -fx-border-radius: 6; -fx-background-radius: 6;";

        gender.getItems().addAll("Male", "Female", "Other");
        nationality.getItems().addAll("Indian", "American", "Japanese", "Other");
        program.getItems().addAll("Science", "Commerce", "Arts");
        year.getItems().addAll("1st year", "2nd year", "3rd year", "4th year");
        guardianRelation.getItems().addAll("Father", "Mother", "Uncle", "Aunt", "Other");
        emergencyRel.getItems().addAll("Father", "Mother", "Guardian", "Other");

        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color: #EFEAFE;");
        grid.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("STUDENT ADMISSION FORM");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #7F3DFF;");
        grid.add(titleLabel, 0, 0, 2, 1);

        addRow("Full Name:", fullName, 1, labelStyle);
        addRow("Date of Birth:", dob, 2, labelStyle);
        addRow("Gender:", gender, 4, labelStyle);
        addRow("Nationality:", nationality, 5, labelStyle);
        addRow("Address:", address, 6, labelStyle);
        addRow("City:", city, 7, labelStyle);
        addRow("Postal Code:", postalCode, 8, labelStyle);
        addRow("Phone Number:", phone, 9, labelStyle);
        addRow("Email Address:", email, 10, labelStyle);
        addRow("Preferred Program:", program, 11, labelStyle);
        addRow("Admission Year:", year, 12, labelStyle);
        addRow("Father's Name:", fatherName, 13, labelStyle);
        addRow("Occupation:", fatherOcc, 14, labelStyle);
        addRow("Contact Number:", fatherPhone, 15, labelStyle);
        addRow("Mother's Name:", motherName, 16, labelStyle);
        addRow("Occupation:", motherOcc, 17, labelStyle);
        addRow("Contact Number:", motherPhone, 18, labelStyle);
        addRow("Guardian's Name:", guardianName, 19, labelStyle);
        addRow("Relationship with Student:", guardianRelation, 20, labelStyle);
        addRow("Guardian Phone:", guardianPhone, 21, labelStyle);
        addRow("Emergency Contact Name:", emergencyName, 22, labelStyle);
        addRow("Relationship:", emergencyRel, 23, labelStyle);
        addRow("Emergency Phone:", emergencyPhone, 24, labelStyle);
        addRow("Medical Conditions:", conditions, 25, labelStyle);
        addRow("Allergies:", allergies, 26, labelStyle);
        addRow("How did you hear about us?", additionalInfo, 27, labelStyle);
        addRow("Date of Admission:", declarationDate, 28, labelStyle);

        

        submitBtn.setStyle("-fx-background-color: #7F3DFF; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 40;");
        HBox buttonBox = new HBox(submitBtn);
        buttonBox.setPadding(new Insets(20));
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 0, 29, 2, 1);
        
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        return new Scene(scrollPane, 1560, 790);
    }

    private void addRow(String labelText, javafx.scene.Node input, int row, String labelStyle) {
        Label label = new Label(labelText);
        label.setStyle(labelStyle);
        grid.add(label, 0, row);
        grid.add(input, 1, row);
    }
}
