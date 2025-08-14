package com.hostelvista.controller;

import com.hostelvista.view.DashboardExchangeView;
import com.hostelvista.view.GuestView;
import com.hostelvista.view.Studentdashboard;

import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class DashboardExchangeController {

    private DashboardExchangeView view;
    private Stage stage;
    private HostServices hostServices;
    
    public DashboardExchangeController(DashboardExchangeView view, Stage stage, HostServices hostServices) {
    this.view = view;
    this.stage = stage;
    this.hostServices = hostServices;
}


    // public DashboardExchangeController(DashboardExchangeView view, Stage stage) {
    //     this.view = view;
    //     this.stage = stage;
    // }

    public HBox createSidebarItemWithIcon(String text, String imagePath, int topPadding) {
        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitHeight(30);
        icon.setFitWidth(30);

        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Poppins", 18));
        label.setStyle("-fx-font-weight:bold");
        label.setPadding(new Insets(0, 0, 0, 10));

        HBox box = new HBox(icon, label);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setSpacing(10);
        box.setPadding(new Insets(topPadding, 0, 0, 0));
        return box;
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
            Image iconImage = new Image(imagePath);
            ImageView icon = new ImageView(iconImage);
            icon.setFitWidth(100);
            icon.setFitHeight(97);
            cardBox.getChildren().add(icon);
        } catch (Exception e) {
            Label fallback = new Label("No Image");
            fallback.setTextFill(Color.WHITE);
            fallback.setFont(Font.font(14));
            cardBox.getChildren().add(fallback);
        }

        Label label = new Label(title);
        label.setFont(Font.font(25));
        label.setTextFill(Color.BLUEVIOLET);
        label.setWrapText(true);
        label.setMaxWidth(200);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-weight: bold");

        TextFlow flow = new TextFlow(label);
        flow.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        flow.setMaxWidth(200);
        flow.setStyle("-fx-padding: 5;");
        flow.setLineSpacing(3);

        outerBox.getChildren().addAll(cardBox, flow);

        // ✅ Navigation logic for card clicks
        outerBox.setOnMouseClicked(e -> {
    System.out.println("Card clicked: " + title);
    if (title.equalsIgnoreCase("Guest Room\n  Allocation")) {
        handleGuestCardClick();
    } else if (title.equalsIgnoreCase("Exchange")) {
        handleSellCardClick();
    }
});
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

        return outerBox;
    }

    private void handleSellCardClick() {
    com.hostelvista.controller.SellWindowController controller = new com.hostelvista.controller.SellWindowController();
    controller.start(stage); // assuming `stage` is a field in your class
}


    // ✅ This method handles navigation to GuestView
    private void handleGuestCardClick() {
    GuestController guestController = new GuestController(stage, hostServices);
}

    // private void handleGuestCardClick() {
    //     Stage currentStage = view.getStage();
    //     System.out.println("Navigating to GuestView...");
    //     GuestView guestView = new GuestView(currentStage);
    //     currentStage.setScene(guestView.getScene());
    //     currentStage.setTitle("Guest Room Booking");
    //     System.out.println("GuestView loaded.");
    // }

    public void handleBackButton() {
        Studentdashboard dashboard = new Studentdashboard(stage);
        stage.setScene(dashboard.getScene());
    }

    public void navigateToStudentDashboard() {
        Stage stage = view.getStage();
        Studentdashboard dashboard = new Studentdashboard(stage);
        stage.setScene(dashboard.getScene());
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
