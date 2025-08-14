package com.hostelvista.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.hostelvista.Authentication.FirebaseInitializer;
import com.hostelvista.view.FestFormView;
import com.hostelvista.view.Studentdashboard;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class FestFormController {

    private FestFormView view;
    private Stage stage;

    public FestFormController(Stage stage) {
        FestFormView view = new FestFormView(stage);
        this.stage = stage;
        FirebaseInitializer.initialize();

        view = new FestFormView(stage); // Pass stage to view

        view.getSubmitBtn().setOnAction(e -> handleSubmit());
        view.getBackButton().setOnAction(e -> goBack());

        stage.setScene(view.getScene());
        stage.setTitle("HostelVista - Fest Registration");
    }

    private void handleSubmit() {
        if (!view.getAgreeCheck().isSelected()) {
            showAlert("Terms Agreement", "You must agree to the terms before submitting.");
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("fullName", view.getNameField().getText());
        data.put("collegeId", view.getCollegeId().getText());
        data.put("department", view.getDepartment().getValue());
        data.put("year", view.getYear().getValue());
        data.put("festName", view.getFestName().getValue());
        data.put("participationType", view.getParticipationType().getValue());
        data.put("contact", view.getContact().getText());
        data.put("email", view.getEmail().getText());
        data.put("agreedToRules", view.getAgreeCheck().isSelected());

        Firestore db = FirebaseInitializer.getDB();

        new Thread(() -> {
            try {
                ApiFuture<DocumentReference> future = db.collection("fest_forms").add(data);
                future.get();  // Wait for completion
                System.out.println("✅ Fest form submitted: " + data);
            } catch (Exception e) {
                System.err.println("❌ Error saving fest form");
                e.printStackTrace();
            }
        }).start();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    private void goBack() {
        Studentdashboard dashboard = new Studentdashboard(stage);
        Scene dashboardScene = dashboard.getScene();
        stage.setScene(dashboardScene);
        stage.setTitle("Student Dashboard");
    }
}
