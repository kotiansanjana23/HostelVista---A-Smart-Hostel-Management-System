package com.hostelvista.view;

import com.hostelvista.controller.QRScannerController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class QRScannerView {
    private Stage stage;
    private Scene scene;
    private StackPane cameraPane;
    private QRScannerController controller;

    public QRScannerView(Stage stage) {
        this.stage = stage;
        this.controller = new QRScannerController(this);

        BorderPane root = new BorderPane();
        cameraPane = new StackPane();
        cameraPane.setPadding(new Insets(20));

        // Add camera preview here
        controller.startCamera();

        // Back button
        Button backButton = new Button("Back");
        backButton.setFont(new Font(16));
        backButton.setOnAction(e -> controller.handleBack());

        root.setCenter(cameraPane);
        root.setBottom(backButton);
        BorderPane.setMargin(backButton, new Insets(10));

        scene = new Scene(root, 1570, 790);
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

   public void show() {
    stage.setTitle("QR Attendance Scanner");
    stage.setScene(scene);
    stage.show();
}

}


