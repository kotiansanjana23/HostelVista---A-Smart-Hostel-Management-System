package com.hostelvista.controller;

import com.hostelvista.view.DashboardmessView;
import com.hostelvista.view.MessChangeView;
import javafx.stage.Stage;

public class MessChangeController {
    private Stage stage;
    private MessChangeView view;

    public MessChangeController(Stage stage) {
        this.stage = stage;
        this.view = new MessChangeView();
        initialize();
        stage.setScene(view.getScene());
    }

    private void initialize() {
        view.backBtn.setOnAction(e -> {
            DashboardmessView dashboard = new DashboardmessView(stage);
            stage.setScene(dashboard.getScene());
        });

        view.submitBtn.setOnAction(e -> {
            // Example form handling
            String name = view.nameField.getText();
            String id = view.idField.getText();
            String room = view.roomField.getText();
            String currentMess = view.currentMessBox.getValue();
            String newMess = view.newMessBox.getValue();
            String reason = view.reasonArea.getText();

            if (name.isEmpty() || id.isEmpty() || room.isEmpty() || currentMess == null || newMess == null || reason.isEmpty()) {
                System.out.println("⚠️ Please fill in all fields!");
                return;
            }

            System.out.println("✅ Mess change request submitted for: " + name);
            // TODO: Push this data to Firebase or DB
        });
    }
}

