package com.hostelvista.view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class GuestView {

    private VBox mainLayout;
    private Label title;
    private HBox bedsBox;
    private Button enterDetailsButton;
    private Button payButton;
    private Button backButton;
    private Scene scene;
    private Image bedImage;

    public VBox getMainLayout() {
        return mainLayout;
    }

    public HBox getBedsBox() {
        return bedsBox;
    }

    public Button getEnterDetailsButton() {
        return enterDetailsButton;
    }

    public Button getPayButton() {
        return payButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Image getBedImage() {
        return bedImage;
    }

    public Scene getScene() {
        return scene;
    }

    public GuestView(Stage stage) {
     //   bedImage = new Image("\\Assets\\Images\\image3.jpg"); // Use forward slash
     bedImage = new Image(getClass().getResource("/Assets/images/image3.jpg").toExternalForm());

        mainLayout = new VBox(50);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #814dc5; -fx-background-radius: 15;");

        title = new Label("Select Bed In Guest Room");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 36px; -fx-font-weight: bold;");

        bedsBox = new HBox(20);
        bedsBox.setAlignment(Pos.CENTER);

    // bedImage.setFitWidth(200);
    // bedImageView.setFitHeight(150);
    // bedsBox.getChildren().add(bedImageView);

        enterDetailsButton = new Button("Enter Details");
        styleButton(enterDetailsButton);

        payButton = new Button("PAY");
        styleButton(payButton);

        backButton = new Button("⬅ Back");
        styleButton(backButton);

        HBox buttonBox = new HBox(15, enterDetailsButton, payButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(title, bedsBox, buttonBox);

        scene = new Scene(mainLayout, 1560, 790);
    }

    private void styleButton(Button button) {
        button.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        button.setMaxWidth(350);
        button.setPrefHeight(50);
        button.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-border-color: #ccc;");
    }
}
