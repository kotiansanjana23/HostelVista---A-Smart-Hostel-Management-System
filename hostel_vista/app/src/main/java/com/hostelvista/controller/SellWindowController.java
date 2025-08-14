package com.hostelvista.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.hostelvista.Authentication.FirebaseInitializer;
import com.hostelvista.view.DashboardExchangeView;
import com.hostelvista.view.SellWindowView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class SellWindowController {

    private SellWindowView view;
    private File selectedImageFile;

    public void start(Stage stage) {
        FirebaseInitializer.initialize();
        view = new SellWindowView();
        Scene scene = view.getScene(stage);
        addListeners(stage);
        stage.setTitle("📤 Sell Item - HostelVista");
        stage.setScene(scene);
        stage.show();
    }

    private void addListeners(Stage stage) {
        view.uploadBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Product Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            selectedImageFile = fileChooser.showOpenDialog(stage);
            if (selectedImageFile != null) {
                view.imageView.setImage(new Image(selectedImageFile.toURI().toString()));
            }
        });
        
        view.getBackButton().setOnAction(e -> {
    DashboardExchangeView dashboardExchange = new DashboardExchangeView(stage, null); // If you have hostServices, pass it instead of null
    dashboardExchange.createUI(); // Make sure this is called
    stage.setScene(dashboardExchange.getScene());
});

        view.sellBtn.setOnAction(e -> {
            if (view.titleField.getText().isEmpty() || view.descriptionArea.getText().isEmpty()
                    || view.priceField.getText().isEmpty() || view.imageView.getImage() == null) {
                showAlert("Please fill in all fields and upload an image.");
            } else {
                uploadProductToFirebase(
                        view.titleField.getText(),
                        view.descriptionArea.getText(),
                        view.priceField.getText()
                );
            }
        });
    }

    private void uploadProductToFirebase(String title, String description, String price) {
        Map<String, Object> data = new HashMap<>();
        data.put("title", title);
        data.put("description", description);
        data.put("price", price);

        Firestore db = FirebaseInitializer.getDB();

        new Thread(() -> {
            try {
                ApiFuture<QuerySnapshot> future = db.collection("sell_items").get();
                int count = future.get().size() + 1;
                String docId = "item" + count;

                ApiFuture<WriteResult> writeFuture = db.collection("sell_items").document(docId).set(data);
                writeFuture.get();

                Platform.runLater(() -> {
                    showAlert("✅ Product posted as " + docId);
                    view.titleField.clear();
                    view.descriptionArea.clear();
                    view.priceField.clear();
                    view.imageView.setImage(null);
                });

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                Platform.runLater(() ->
                        showAlert("❌ Failed to upload product. Try again.")
                );
            }
        }).start();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HostelVista Exchange");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
