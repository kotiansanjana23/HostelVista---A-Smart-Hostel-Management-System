
package com.hostelvista.controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.hostelvista.view.QRScannerView;
import com.hostelvista.view.Studentdashboard;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class QRScannerController {
    private QRScannerView view;
    private Webcam webcam;
    private JFrame window;
    private volatile boolean scanning;

    public QRScannerController(QRScannerView view) {
        this.view = view;
    }

    public void startCamera() {
        webcam = Webcam.getDefault();
        webcam.setViewSize(new java.awt.Dimension(640, 480));
        webcam.open();

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);

        window = new JFrame("QR Scanner");
        window.add(panel);
        window.setSize(1560, 790);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        scanning = true;

        new Thread(() -> {
            while (scanning) {
                BufferedImage image = webcam.getImage();
                if (image != null) {
                    String result = decodeQRCode(image);
                    if (result != null) {
                        scanning = false;
                        webcam.close();

                        // Platform.runLater(() -> {
                        //     JOptionPane.showMessageDialog(null, "QR Code: " + result);
                        //     handleBack(); // Go back to dashboard
                        // });
                        Platform.runLater(() -> {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Attendance Success");
    alert.setHeaderText(null);
    alert.setContentText("✅ Attendance marked successfully!");
    alert.showAndWait();  // Wait for user to close
    handleBack();         // Then go back to dashboard
});

                        break;
                    }
                }

                try {
                    Thread.sleep(100); // reduce CPU usage
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String decodeQRCode(BufferedImage image) {
        try {
            LuminanceSource source = new com.google.zxing.client.j2se.BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            return null; // No QR code found
        }
    }

    public void handleBack() {
        scanning = false;

        if (webcam != null && webcam.isOpen()) {
            webcam.close();
        }

        if (window != null) {
            window.dispose();
        }

        Stage stage = view.getStage();
        Studentdashboard dashboard = new Studentdashboard(stage);
        stage.setScene(dashboard.getScene());
    }
}

