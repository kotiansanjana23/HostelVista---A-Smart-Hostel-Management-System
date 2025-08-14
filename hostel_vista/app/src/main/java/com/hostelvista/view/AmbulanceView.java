package com.hostelvista.view;

import com.hostelvista.controller.AmbulanceController;
import com.hostelvista.controller.AmbulanceController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AmbulanceView{
    private Scene scene;
    private Stage stage;

    public AmbulanceView(Stage stage) {
        this.stage = stage;
        this.scene = createScene();
    }

    private Scene createScene() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: white;");

        // 🚑 Ambulance Header
        Label ambulanceLabel = new Label("Ambulance Phone No: 1234567891");
        ambulanceLabel.setFont(Font.font("Arial", 30));
        ambulanceLabel.setTextFill(Color.PURPLE);

        ImageView arrowIcon = new ImageView(new Image("/Assets/Images/backarrow.png"));
        arrowIcon.setFitWidth(50);
        arrowIcon.setFitHeight(50);

        Button backButton = new Button();
        backButton.setGraphic(arrowIcon);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> {
            new AmbulanceController(stage).handleBack(); // Delegate back logic
        });

        HBox topBar = new HBox(910);
        topBar.setAlignment(Pos.TOP_LEFT);
        topBar.setPadding(new Insets(10));
        topBar.getChildren().addAll(ambulanceLabel, backButton);

        VBox contactList = new VBox(15);
        contactList.getChildren().addAll(
            createContactCard("xyz", "1234567891", "Hostel Warden InCharge"),
            createContactCard("xyz", "1234567891", "Warden"),
            createContactCard("xyz", "1234567891", "Warden2"),
            createContactCard("xyz", "1234567891", "Campus Doctor 1"),
            createContactCard("xyz", "1234567891", "Campus Doctor 2")
        );

        root.getChildren().addAll(topBar, contactList);

        return new Scene(root, 1560, 790);
    }

    private HBox createContactCard(String name, String phone, String designation) {
        VBox textSection = new VBox(11);
        textSection.setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label("Name: " + name);
        nameLabel.setFont(Font.font("Arial", 21));
        nameLabel.setTextFill(Color.web("#4B0082"));
        nameLabel.setStyle("-fx-font-weight: bold");

        Label phoneLabel = new Label("Phone No: " + phone);
        phoneLabel.setFont(Font.font("Arial", 21));
        phoneLabel.setTextFill(Color.web("#4B0082"));

        Label designationLabel = new Label("Designation: " + designation);
        designationLabel.setFont(Font.font("Arial", 21));
        designationLabel.setTextFill(Color.web("#4B0082"));

        textSection.getChildren().addAll(nameLabel, phoneLabel, designationLabel);

        ImageView callIcon = new ImageView(new Image(getClass().getResource("/Assets/Images/call1.png").toExternalForm()));
        callIcon.setFitWidth(30);
        callIcon.setFitHeight(30);

        StackPane iconCircle = new StackPane(callIcon);
        iconCircle.setPrefSize(60, 60);
        iconCircle.setStyle(
            "-fx-background-color: #3d0898ff;" +
            "-fx-background-radius: 50%;" +
            "-fx-border-radius: 50%;" +
            "-fx-alignment: center;"
        );
        javafx.scene.shape.Circle clip = new javafx.scene.shape.Circle();
clip.radiusProperty().bind(iconCircle.widthProperty().divide(2));
clip.centerXProperty().bind(iconCircle.widthProperty().divide(2));
clip.centerYProperty().bind(iconCircle.heightProperty().divide(2));
iconCircle.setClip(clip);
        iconCircle.setOnMouseClicked(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Calling");
            alert.setHeaderText(null);
            alert.setContentText("Calling " + phone + "...");
            alert.showAndWait();
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox card = new HBox(50);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(7));
        card.setStyle("-fx-border-color: #B19CD9; -fx-border-radius: 15; -fx-background-radius: 15;");
        card.getChildren().addAll(textSection, spacer, iconCircle);

        return card;
    }

    public Scene getScene() {
        return scene;
    }
}

