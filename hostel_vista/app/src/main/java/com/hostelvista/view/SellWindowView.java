package com.hostelvista.view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SellWindowView {

    public ImageView imageView;
    public TextField titleField;
    public TextArea descriptionArea;
    public TextField priceField;
    public Button uploadBtn;
    public Button sellBtn;
    public Button backButton;

    public Scene getScene(Stage stage) {
        imageView = new ImageView();
        imageView.setFitHeight(180);
        imageView.setFitWidth(220);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-border-color: #4B0082; -fx-border-width: 2;");

        uploadBtn = new Button("📸 Upload Image");
        uploadBtn.setStyle("-fx-background-color: #DAD2FF; -fx-text-fill: #4B0082;");

        VBox imageSection = new VBox(10, imageView, uploadBtn);
        imageSection.setAlignment(Pos.CENTER);

        titleField = new TextField();
        titleField.setPromptText("Enter product title");
        titleField.setMaxWidth(300);

        descriptionArea = new TextArea();
        descriptionArea.setPromptText("Enter product description...");
        descriptionArea.setWrapText(true);
        descriptionArea.setPrefRowCount(4);
        descriptionArea.setMaxWidth(300);

        priceField = new TextField();
        priceField.setPromptText("Enter price");
        priceField.setMaxWidth(300);

        sellBtn = new Button("📤 Sell");
        sellBtn.setStyle("-fx-background-color: #4B0082; -fx-text-fill: white; -fx-font-size: 20;");
        backButton = new Button("🔙 Back");
backButton.setStyle("-fx-background-color: #FFD6D6; -fx-text-fill: #4B0082; -fx-font-size: 16px;");


        VBox content = new VBox(15,
                new Label("📤 Post Item for Sale"),
                imageSection,
                titleField,
                descriptionArea,
                priceField,
                sellBtn,
                backButton
        );
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: #F4F0FF;-fx-font-size: 15;");

        return new Scene(content, 1560, 790);
    }
     private void styleButton(Button button) {
        button.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        button.setMaxWidth(350);
        button.setPrefHeight(50);
        button.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-border-color: #ccc;");
    }
     public Button getBackButton() {
        return backButton;
    }
    
}

