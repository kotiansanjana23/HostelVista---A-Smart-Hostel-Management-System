package com.hostelvista.view;

import com.hostelvista.controller.LateFeePaymentController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class LateFeePaymentView {

    private final double totalFee = 200.0;
    private Scene scene;

    public LateFeePaymentView(Stage stage) {
        start(stage);
    }

    public void start(Stage stage) {
        // --- UI Components ---
        Label noteLabel = new Label("*NOTE:  \"Late Fee Payment\"");
        noteLabel.setFont(Font.font("Arial", 17));
        noteLabel.setTextFill(Color.web("#5A4B7F"));

        Label totalFeesLabel = new Label("Total Fees\n₹" + totalFee);
        totalFeesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        totalFeesLabel.setTextFill(Color.web("#8025b4"));
        totalFeesLabel.setAlignment(Pos.CENTER);

        Label paymentLabel = new Label("Payment\n₹0.0");
        paymentLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        paymentLabel.setTextFill(Color.web("#8025b4"));
        paymentLabel.setAlignment(Pos.CENTER);

        Button payButton = new Button("PAY");
        payButton.setStyle("-fx-background-color: #8025b4; -fx-text-fill: white; -fx-font-size: 20; -fx-padding: 10 20;");

        HBox topBox = new HBox(40, totalFeesLabel, paymentLabel, payButton);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(10));
        topBox.setStyle("-fx-border-color: #8025b4; -fx-border-width: 2;");

        VBox dueInfo = new VBox(5,
            new Text("Fee Type : Late fees"),
            new Text("Due Date: 22-06-2025"),
            new Text("Academic Year: 25-26")
        );
        dueInfo.setPadding(new Insets(10));
        dueInfo.setStyle(" -fx-font-size: 18");

        TextField amountField = new TextField("0");
        amountField.setPrefWidth(100);

        Button fullAmountBtn = new Button("FULL AMOUNT");
        Button clearBtn = new Button("X");
        clearBtn.setStyle("-fx-background-color: #D3D3D3; -fx-text-fill: #333;");

        HBox amountBox = new HBox(10, amountField, clearBtn);
        amountBox.setAlignment(Pos.CENTER);

        VBox inputSection = new VBox(10, dueInfo, amountBox, fullAmountBtn);
        inputSection.setAlignment(Pos.CENTER);

        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: #8025b4; -fx-text-fill: white; -fx-font-size: 16;");

        // Button backButton = new Button("←");
        // backButton.setFont(Font.font(25));
        // backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #8025b4;");
        Button backButton = new Button("Back");
backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
backButton.setStyle("-fx-background-color: #8025b4; -fx-text-fill: white; -fx-font-size: 16;");


        HBox backContainer = new HBox(backButton);
        backContainer.setAlignment(Pos.TOP_LEFT);
HBox buttonBar = new HBox(10, backButton, resetButton);
buttonBar.setAlignment(Pos.CENTER);

VBox layout = new VBox(20, noteLabel, topBox, inputSection, buttonBar);

       // VBox layout = new VBox(20, backContainer, noteLabel, topBox, inputSection, resetButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        // --- Live update the label when typing ---
        amountField.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                double val = Double.parseDouble(newVal);
                paymentLabel.setText("Payment\n₹" + val);
            } catch (NumberFormatException ex) {
                paymentLabel.setText("Payment\n₹0.0");
            }
        });

        // --- Controller Actions ---
        LateFeePaymentController controller = new LateFeePaymentController(totalFee, amountField, paymentLabel);
        controller.setUpActions(payButton, fullAmountBtn, clearBtn, resetButton, backButton, stage);

        // --- Scene Setup ---
        scene = new Scene(layout, 1560, 790);
        stage.setTitle("Fee Payment Portal");
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }
}
