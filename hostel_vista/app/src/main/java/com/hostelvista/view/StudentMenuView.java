package com.hostelvista.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;

import com.hostelvista.controller.StudentMenuController;

public class StudentMenuView {
    private Scene scene;

    public StudentMenuView(Stage stage) {
        // ✅ Load back arrow image correctly
        InputStream is = getClass().getResourceAsStream("/Assets/Images/backarrow.png");
        ImageView arrowIcon = null;
        if (is != null) {
            Image arrowImg = new Image(is);
            arrowIcon = new ImageView(arrowImg);
            arrowIcon.setFitWidth(50);
            arrowIcon.setFitHeight(50);
        } else {
            System.out.println("⚠️ Back arrow image not found");
        }

        Button backButton = new Button();
        if (arrowIcon != null) backButton.setGraphic(arrowIcon);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> {
    StudentMenuController controller = new StudentMenuController();
    controller.goBackToDashboard(stage);
});
HBox topBar = new HBox(backButton);
topBar.setPadding(new Insets(15));
topBar.setAlignment(Pos.TOP_RIGHT);

        Label title = new Label("Today’s Menu");
        title.setFont(Font.font("Arial", 26));
        title.setTextFill(Color.web("#9C59D1"));

        Label morningLabel = new Label("Morning:");
        morningLabel.setFont(Font.font("Arial", 20));
        morningLabel.setTextFill(Color.web("#6A1B9A"));

        Label morningItems = new Label("1. Poha\n2. Upma\n3. Chapati\n4. Sweet\n5. Rice\n6. Salad");
        morningItems.setFont(Font.font(16));
        morningItems.setTextFill(Color.web("#7B519D"));

        Label eveningLabel = new Label("Evening:");
        eveningLabel.setFont(Font.font("Arial", 20));
        eveningLabel.setTextFill(Color.web("#6A1B9A"));

        Label eveningItems = new Label("1. Paneer\n2. Veg Curry\n3. Chapati\n4. Dessert\n5. Rice\n6. Salad");
        eveningItems.setFont(Font.font(16));
        eveningItems.setTextFill(Color.web("#7B519D"));

        Button okButton = new Button("OK !");
        okButton.setStyle("-fx-background-color: #d2a8f2; -fx-text-fill: #5e239d; -fx-font-size: 14px; -fx-background-radius: 15;");
       // okButton.setOnAction(e -> stage.close());
       okButton.setOnAction(e -> {
    StudentMenuController controller = new StudentMenuController();
    controller.goBackToDashboard(stage);
});


        VBox menuBox = new VBox(10, morningLabel, morningItems, eveningLabel, eveningItems);
        menuBox.setAlignment(Pos.CENTER_LEFT);

        VBox innerBox = new VBox(20, title, menuBox, okButton);
        innerBox.setAlignment(Pos.CENTER);
        innerBox.setPadding(new Insets(20));

        StackPane borderedPane = new StackPane(innerBox);
        borderedPane.setMaxWidth(350);
        borderedPane.setStyle("-fx-border-color: #9C59D1; -fx-border-radius: 25; -fx-background-radius: 25; -fx-border-width: 1;");
        borderedPane.setPadding(new Insets(30));

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(borderedPane);
        mainLayout.setTop(topBar);
        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(15));
        

        this.scene = new Scene(mainLayout,1560, 790);
        System.out.println("✅ StudentMenuView loaded successfully.");
    }

    public Scene getScene() {
        return scene;
    }
}
