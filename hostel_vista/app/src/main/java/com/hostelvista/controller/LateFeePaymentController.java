package com.hostelvista.controller;

import com.hostelvista.view.LateFeePaymentView;
import com.hostelvista.view.Studentdashboard;

import javafx.scene.control.*;
import javafx.stage.Stage;

public class LateFeePaymentController {

    private final double totalFee;
    private final TextField amountField;
    private final Label paymentLabel;

    public LateFeePaymentController(double totalFee, TextField amountField, Label paymentLabel) {
        this.totalFee = totalFee;
        this.amountField = amountField;
        this.paymentLabel = paymentLabel;
    }

    // public LateFeePaymentController(LateFeePaymentView view, Stage stage) {
    //     //TODO Auto-generated constructor stub
    // }

    // public void setUpActions(Button payBtn, Button fullBtn, Button clearBtn, Button resetBtn, Button backBtn, Stage stage) {

    //     fullBtn.setOnAction(e -> {
    //         amountField.setText(String.valueOf(totalFee));
    //         paymentLabel.setText("Payment\n₹" + totalFee);
    //     });

    //     clearBtn.setOnAction(e -> {
    //         amountField.setText("0");
    //         paymentLabel.setText("Payment\n₹0.0");
    //     });

    //     payBtn.setOnAction(e -> {
    //         try {
    //             double enteredAmount = Double.parseDouble(amountField.getText());
    //             if (enteredAmount > 0 && enteredAmount <= totalFee) {
    //                 showInfo("Payment Successful", "Payment of ₹" + enteredAmount + " successful!");
    //                 paymentLabel.setText("Payment\n₹" + enteredAmount);
    //             } else {
    //                 showError("Enter valid payment amount.");
    //             }
    //         } catch (NumberFormatException ex) {
    //             showError("Invalid input. Please enter a number.");
    //         }
    //     });

    //     resetBtn.setOnAction(e -> {
    //         amountField.setText("0");
    //         paymentLabel.setText("Payment\n₹0.0");
    //     });

    //     backBtn.setOnAction(e -> {
    //         System.out.println("Back button clicked. Navigating back...");
    //         stage.close();
    //     });
    // }
    public void setUpActions(Button payButton, Button fullAmountBtn, Button clearBtn, Button resetButton, Button backButton, Stage stage) {

    fullAmountBtn.setOnAction(e -> {
        amountField.setText(String.valueOf(totalFee));
    });

    clearBtn.setOnAction(e -> {
        amountField.clear();
    });

    payButton.setOnAction(e -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Status");
        alert.setHeaderText(null);
        alert.setContentText("Payment Successful!");
        alert.showAndWait();
    });

    resetButton.setOnAction(e -> {
        amountField.setText("0");
    });

    backButton.setOnAction(e -> {
        stage.setScene(new Studentdashboard(stage).getScene()); // or however you return to dashboard
    });
}
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
