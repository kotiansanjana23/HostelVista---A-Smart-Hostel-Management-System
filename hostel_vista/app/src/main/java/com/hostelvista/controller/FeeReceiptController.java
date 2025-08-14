package com.hostelvista.controller;

import com.hostelvista.view.FeeReceiptView;
import com.hostelvista.view.Studentdashboard;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FeeReceiptController {

    private final FeeReceiptView view;

    public FeeReceiptController(FeeReceiptView view, Stage stage) {
        this.view = view;

        // PDF Download Handler
        view.getDownloadButton().setOnAction(e -> printToPDF(stage));

        // ✅ Back Button Handler
        view.getBackButton().setOnAction(e -> {
            Studentdashboard dashboard = new Studentdashboard(stage);
            stage.setScene(dashboard.getScene());
            stage.setTitle("Student Dashboard");
        });
    }

    private void printToPDF(Stage stage) {
        VBox receiptLayout = view.getReceiptLayout();
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(stage)) {
            boolean success = job.printPage(receiptLayout);
            if (success) {
                job.endJob();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "PDF Saved Successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save PDF.");
                alert.showAndWait();
            }
        }
    }
}
