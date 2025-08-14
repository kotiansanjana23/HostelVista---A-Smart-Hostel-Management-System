package com.hostelvista.controller;

import java.net.URL;

import com.hostelvista.view.DashboardFeesView;
import com.hostelvista.view.FeePaymentView;
import com.hostelvista.view.FeeReceiptView;
import com.hostelvista.view.LateFeePaymentView;
import com.hostelvista.view.LateFeePaymentView;
import com.hostelvista.view.Studentdashboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class DashboardFeesController {
    private final DashboardFeesView view;

    public DashboardFeesController(DashboardFeesView view) {
        this.view = view;
    }

    public Node createCard(String title, String imagePath) {
        VBox outerBox = new VBox(10);
        outerBox.setAlignment(Pos.TOP_CENTER);
        outerBox.setPrefWidth(250);
        outerBox.setPrefHeight(500);

        StackPane cardBox = new StackPane();
        cardBox.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
        cardBox.setPadding(new Insets(20));
        cardBox.setPrefSize(200, 300);
        cardBox.setMinHeight(140);
        cardBox.setMaxHeight(140);
        cardBox.setMaxWidth(250);

        try {
    String resolvedPath = imagePath.startsWith("/") ? imagePath : "/" + imagePath;
    URL imageUrl = DashboardFeesController.class.getResource(resolvedPath);
    
    if (imageUrl == null) {
        throw new IllegalArgumentException("Image not found: " + resolvedPath);
    }

    Image iconImage = new Image(imageUrl.toExternalForm());
    ImageView icon = new ImageView(iconImage);
    icon.setFitWidth(100);
    icon.setFitHeight(97);
    cardBox.getChildren().add(icon);
} catch (Exception e) {
    e.printStackTrace();
    Label fallback = new Label("No Image");
    fallback.setTextFill(Color.WHITE);
    fallback.setFont(Font.font(14));
    cardBox.getChildren().add(fallback);
}


        Label label1 = new Label(title);
        label1.setFont(Font.font(25));
        label1.setTextFill(Color.BLUEVIOLET);
        label1.setWrapText(true);
        label1.setMaxWidth(200);
        label1.setAlignment(Pos.CENTER);
        label1.setStyle("-fx-font-weight: bold");

        TextFlow flow = new TextFlow(label1);
        flow.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        flow.setMaxWidth(200);
        flow.setStyle("-fx-padding: 5;");
        flow.setLineSpacing(3);

        outerBox.setOnMouseEntered(e -> {
            outerBox.setScaleX(1.10);
            outerBox.setScaleY(1.10);
            outerBox.setStyle("-fx-cursor: hand;");
            cardBox.setStyle("-fx-background-color: #7f3fe0; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 4);");
        });

        outerBox.setOnMouseExited(e -> {
            outerBox.setScaleX(1.0);
            outerBox.setScaleY(1.0);
            cardBox.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
        });

        // outerBox.setOnMouseClicked(e -> {
        //     System.out.println("Card clicked: " + title);
        //     Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        //     try {
        //         if (title.equalsIgnoreCase("Fees Payment") || title.equalsIgnoreCase("Payment Receipt")) {
        //             FeeReceiptView receiptView = new FeeReceiptView(currentStage);
        //             currentStage.setScene(receiptView.getScene());
        //             System.out.println("Navigated to FeeReceiptView");
        //         } else if (title.equalsIgnoreCase("Late feePayment")) {
        //             LateFeePaymentView lateFeeView = new LateFeePaymentView(currentStage);
        //             currentStage.setScene(lateFeeView.getScene());
        //             System.out.println("Navigated to LateFeeReceiptView");
        //         }else if (title.equalsIgnoreCase("FeePayment")) {
        //             FeePaymentView lateFeeView = new FeePaymentView(currentStage);
        //             currentStage.setScene(FeeView.getScene());
        //             System.out.println("Navigated to LateFeeReceiptView");
        //     }
        //  } catch (Exception ex) {
        //         ex.printStackTrace();
        //         System.out.println("Error navigating to: " + title);
        //     }
        // });
        outerBox.setOnMouseClicked(e -> {
    System.out.println("Card clicked: " + title);
    Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

    try {
        if (title.equalsIgnoreCase("Fees Payment")) {
            FeePaymentView feePaymentView = new FeePaymentView(currentStage);
            currentStage.setScene(feePaymentView.getScene());
            currentStage.setTitle("Fee Payment");
            System.out.println("Navigated to FeePaymentView");

        } else if (title.equalsIgnoreCase("Payment Receipt")) {
            FeeReceiptView receiptView = new FeeReceiptView(currentStage);
            currentStage.setScene(receiptView.getScene());
            currentStage.setTitle("Fee Receipt");
            System.out.println("Navigated to FeeReceiptView");

        } else if (title.equalsIgnoreCase("Late feePayment")) {
            LateFeePaymentView lateFeeView = new LateFeePaymentView(currentStage);
            currentStage.setScene(lateFeeView.getScene());
            currentStage.setTitle("Late Fee Payment");
            System.out.println("Navigated to LateFeePaymentView");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Error navigating to: " + title);
    }
});


        outerBox.getChildren().addAll(cardBox, flow);
        return outerBox;
    }

    public void handleBackClick(Stage stage) {
        Studentdashboard dashboard = new Studentdashboard(stage);
        Scene scene = dashboard.getScene();
        stage.setScene(scene);
    }
}
