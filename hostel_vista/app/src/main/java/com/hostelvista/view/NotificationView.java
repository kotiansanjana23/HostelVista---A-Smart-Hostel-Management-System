package com.hostelvista.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class NotificationView {
    private Scene scene;

    public NotificationView(Stage stage, String role) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        Label title = new Label("Notifications");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label message = new Label();
        message.setStyle("-fx-font-size: 16px;");

        if ("student".equalsIgnoreCase(role)) {
            message.setText("No new notifications.\nCheck your room allocation or mess status.");
        } else if ("warden".equalsIgnoreCase(role)) {
            message.setText("No new notifications.\nMonitor student admissions and mess requests.");
        }

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14px;");
        backButton.setOnAction(e -> {
            if ("student".equalsIgnoreCase(role)) {
                Studentdashboard studentDashboard = new Studentdashboard(stage);
                stage.setScene(studentDashboard.getScene());
            } else if ("warden".equalsIgnoreCase(role)) {
                try {
                    Wardendashboard wardenDashboard = new Wardendashboard();
                    wardenDashboard.start(stage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        layout.getChildren().addAll(title, message, backButton);
        scene = new Scene(layout, 800, 600);

        // Optional: show a popup alert on load
        showNotificationPopup(role);
    }

    public Scene getScene() {
        return scene;
    }

    private void showNotificationPopup(String role) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("New Notification");
        alert.setHeaderText("Hostel Update");

        if ("student".equalsIgnoreCase(role)) {
            alert.setContentText("Your room has been successfully allocated!");
        } else if ("warden".equalsIgnoreCase(role)) {
            alert.setContentText("New student admission request received.");
        } else {
            alert.setContentText("You have a new notification.");
        }

        alert.showAndWait();
    }
}
