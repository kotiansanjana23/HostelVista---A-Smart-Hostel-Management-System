package com.hostelvista.controller;

import com.hostelvista.view.FeeReceiptView;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class FeeReceiptController {

    private final FeeReceiptView view;

    public FeeReceiptController(FeeReceiptView view, Stage stage) {
        this.view = view;

        view.getDownloadButton().setOnAction(e -> printToPDF(stage));
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

