package com.hostelvista.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class FeedbackFormView {

    private VBox mainLayout;
    private VBox ratingBox;
    private TextArea remarks;
    private Button submitButton;
    private Button backButton;

    private HashMap<String, Integer> ratings = new HashMap<>();

    public FeedbackFormView() {
        mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-border-color: #a879d2; -fx-border-radius: 30; -fx-background-radius: 30; -fx-background-color: white;");
        mainLayout.setMaxWidth(500);
        mainLayout.setMinHeight(500);

        Label title = new Label("FEEDBACK MONTHLY");
        title.setFont(Font.font("Poppins", 22));
        title.setTextFill(Color.web("#7748c6"));
        title.setAlignment(Pos.CENTER);

        ratingBox = new VBox(15);
        ratingBox.setPadding(new Insets(20));
        ratingBox.getChildren().addAll(
                createRatingRow("1. Warden:", "Xyz Name"),
                createRatingRow("2. Cleaning Staff:", "Xyz Name"),
                createRatingRow("3. Security:", "Xyz Name")
        );

        Label remarkLabel = new Label("Remark:");
        remarkLabel.setFont(Font.font("Poppins", 16));
        remarkLabel.setTextFill(Color.web("#7748c6"));

        remarks = new TextArea();
        remarks.setPromptText("Type your feedback...");
        remarks.setPrefHeight(100);
        remarks.setWrapText(true);
        remarks.setStyle("-fx-border-color: #7748c6; -fx-border-radius: 15; -fx-background-radius: 15;");

        submitButton = new Button("SUBMIT");
        submitButton.setStyle("-fx-background-color: #b583f3; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 20;");
        submitButton.setPrefWidth(100);

        backButton = new Button("BACK");
        backButton.setStyle("-fx-background-color: #e6d3f9; -fx-text-fill: #7748c6; -fx-font-weight: bold; -fx-background-radius: 20;");
        backButton.setPrefWidth(100);

        HBox buttonBox = new HBox(15, backButton, submitButton);
        buttonBox.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(title, ratingBox, remarkLabel, remarks, buttonBox);
    }

    private HBox createRatingRow(String labelText, String nameText) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Poppins", 16));
        label.setTextFill(Color.web("#7748c6"));

        Label name = new Label(nameText);
        name.setFont(Font.font("Poppins", 14));
        name.setTextFill(Color.DARKGRAY);

        HBox starsBox = new HBox(5);
        starsBox.setAlignment(Pos.CENTER_LEFT);

        String staffKey = labelText.replace(":", "").trim();
        ratings.put(staffKey, 0);

        for (int i = 1; i <= 5; i++) {
            Label star = new Label("★");
            star.setFont(Font.font("Poppins", 18));
            star.setTextFill(Color.LIGHTGRAY);

            final int ratingValue = i;
            star.setOnMouseClicked(event -> {
                ratings.put(staffKey, ratingValue);
                updateStars(starsBox, ratingValue);
            });

            starsBox.getChildren().add(star);
        }

        HBox row = new HBox(10, label, name, starsBox);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private void updateStars(HBox starsBox, int upTo) {
        for (int i = 0; i < starsBox.getChildren().size(); i++) {
            Label star = (Label) starsBox.getChildren().get(i);
            star.setTextFill(i < upTo ? Color.web("#7748c6") : Color.LIGHTGRAY);
        }
    }

    public Scene getScene() {
        StackPane root = new StackPane(mainLayout);
        root.setStyle("-fx-background-color: #f9f5fc;");
        return new Scene(root, 1560, 790);
    }

    public Map<String, Integer> getRatings() {
        return ratings;
    }

    public String getRemarks() {
        return remarks.getText();
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public Button getBackButton() {
        return backButton;
    }
}
