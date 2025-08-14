package com.hostelvista.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.hostelvista.Authentication.FirebaseInitializer;
import com.hostelvista.view.ComplaintFormView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ComplaintFormController {

    private final ComplaintFormView view;
    private final Stage stage;

    public ComplaintFormController(Stage stage) {
        this.stage = stage;
        FirebaseInitializer.initialize(); // Ensure Firebase is initialized
        view = new ComplaintFormView();
        initActions();
    }

    private void initActions() {
        view.submitButton.setOnAction(e -> handleSubmit());
        view.backButton.setOnAction(e -> stage.close()); // Optionally navigate back instead
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

    public void showView() {
        Scene scene = new Scene(view.getView(), 600, 600);
        stage.setScene(scene);
        stage.setTitle("Anonymous Complaint Form");
        stage.show();
    }
}
