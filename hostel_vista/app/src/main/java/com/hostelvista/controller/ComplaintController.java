package com.hostelvista.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.hostelvista.Authentication.FirebaseInitializer;
import com.hostelvista.view.ComplaintView;
import com.hostelvista.view.Studentdashboard;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ComplaintController {
    private ComplaintView view;
    private Stage stage;

    public ComplaintController(Stage stage) {
        this.stage = stage;
        FirebaseInitializer.initialize();
        view = new ComplaintView();
        setupListeners();
    }

    private void setupListeners() {
        view.submitButton.setOnAction(e -> handleSubmit());
        view.backButton.setOnAction(e -> {
            // Navigate back to student dashboard
            Studentdashboard dashboard = new Studentdashboard(stage);
            stage.setScene(dashboard.getScene());
            stage.setTitle("Student Dashboard");
        });; // Navigation
    }

    private void handleSubmit() {
        Map<String, Object> complaintData = new HashMap<>();
        complaintData.put("type", view.complaintType.getValue());
        complaintData.put("description", view.description.getText());

        Firestore db = FirebaseInitializer.getDB();

        new Thread(() -> {
            try {
                ApiFuture<QuerySnapshot> future = db.collection("complaints").get();
                int count = future.get().size() + 1;
                String docId = "student" + count;

                db.collection("complaints").document(docId).set(complaintData);
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Complaint submitted successfully!");
                    alert.showAndWait();
                    view.description.clear();
                    view.complaintType.setValue("Other");
                });
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to submit complaint.");
                    alert.showAndWait();
                });
            }
        }).start();
    }

    public void showForm() {
        stage.setScene(view.getScene());
        stage.setTitle("Anonymous Complaint Form");
        stage.show();
    }
}
