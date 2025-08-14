package com.hostelvista.view;

import com.hostelvista.controller.DashboardMessController;
import com.hostelvista.controller.StudentMenuController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class DashboardmessView {
    private Scene scene;
    private Stage primaryStage;
    private DashboardMessController controller;

    public DashboardmessView(Stage stage) {
        this.primaryStage = stage;
        this.controller = new DashboardMessController(this, stage);
        createUI();
    }

    private void createUI() {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(60));
        sidebar.setStyle("-fx-background-color:#9B7EDC; -fx-background-radius : 20;");
        sidebar.setPrefWidth(350);

        Label studentName = new Label("Welcome\nhostelvista1!");
        studentName.setTextFill(Color.BLUEVIOLET);
        studentName.setFont(Font.font(16));
        studentName.setStyle("-fx-background-color: #D2B6F3;  -fx-background-radius: 5;");
        studentName.setAlignment(Pos.CENTER);
        studentName.setMaxWidth(600);
        studentName.setPadding(new Insets(0));
        HBox.setHgrow(studentName, Priority.ALWAYS);

        Image profileImage = new Image("\\Assets\\Images\\profile.jpg");
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitWidth(60);
        profileImageView.setFitHeight(60);
        profileImageView.setPreserveRatio(false);

        Circle clip = new Circle(30, 30, 30);
        profileImageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage clippedImage = profileImageView.snapshot(parameters, null);
        profileImageView.setClip(null);
        profileImageView.setImage(clippedImage);

        HBox studentBox = new HBox(profileImageView, studentName);
        studentBox.setAlignment(Pos.CENTER_LEFT);
        studentBox.setStyle("-fx-background-color: #D2B6F3; -fx-padding: 15px; -fx-background-radius: 10;");
        studentBox.setSpacing(5);
        studentBox.setMaxWidth(Double.MAX_VALUE);
        studentBox.setMinHeight(80);

        HBox homeBox = createSidebarItemWithIcon("Home", "\\Assets\\Images\\hh.png", 50);
        HBox hostelInfoBox = createSidebarItemWithIcon("Hostel Information", "\\Assets\\Images\\doc.png", 0);
        HBox NotificationBox = createSidebarItemWithIcon("Notification", "\\Assets\\Images\\noti.png", 0);
        HBox SettingBox = createSidebarItemWithIcon("Settings", "\\Assets\\Images\\sett.png", 0);
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        sidebar.getChildren().addAll(studentBox, homeBox, hostelInfoBox, NotificationBox, spacer, SettingBox);
        

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(130, 130, 130, 0));
        grid.setHgap(120);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);

        grid.add(createCard("Menu", "\\Assets\\Images\\menu.png"), 0, 0);
        grid.add(createCard("Mess Change\n   Request", "\\Assets\\Images\\changerequest.png"), 1, 0);

        ImageView arrowIcon = new ImageView(new Image("\\Assets\\Images\\backarrow.png"));
        arrowIcon.setFitWidth(50);
        arrowIcon.setFitHeight(50);

        Button backButton = new Button();
        backButton.setGraphic(arrowIcon);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> controller.handleBackButton());

        HBox topRight = new HBox(backButton);
        topRight.setAlignment(Pos.TOP_RIGHT);
        topRight.setPadding(new Insets(10, 10, 0, 0));

        VBox rightContent = new VBox(10, topRight, grid);
        rightContent.setPadding(new Insets(20));
        rightContent.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(rightContent, Priority.ALWAYS);

        HBox root = new HBox(sidebar, rightContent);
        HBox.setMargin(sidebar, new Insets(10, 0, 10, 0));

        scene = new Scene(root, 1560, 790);
    }

    public Scene getScene() {
        return scene;
    }

    private HBox createSidebarItemWithIcon(String text, String imagePath, int topPadding) {
        ImageView icon = new ImageView(imagePath);
        icon.setFitHeight(30);
        icon.setFitWidth(30);

        Label label = createSidebarItem(text);
        label.setPadding(new Insets(0, 0, 0, 10));

        HBox box = new HBox(icon, label);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setSpacing(10);
        box.setPadding(new Insets(topPadding, 0, 0, 0));
        return box;
    }

    private VBox createCard(String title, String imagepath) {
        VBox outerBox = new VBox(10);
        outerBox.setAlignment(Pos.TOP_CENTER);
        outerBox.setPrefSize(250, 500);

        StackPane cardBox = new StackPane();
        cardBox.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
        cardBox.setPadding(new Insets(20));
        cardBox.setPrefSize(200, 300);
        cardBox.setMinHeight(140);
        cardBox.setMaxHeight(140);
        cardBox.setMaxWidth(250);

        try {
            Image iconImage = new Image(imagepath);
            ImageView icon = new ImageView(iconImage);
            icon.setFitWidth(90);
            icon.setFitHeight(100);
            cardBox.getChildren().add(icon);
        } catch (Exception e) {
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

       if (title.equalsIgnoreCase("Menu")) {
    outerBox.setOnMouseClicked(event -> {
        System.out.println("Menu card clicked"); // Debug line
        Stage stage = (Stage) outerBox.getScene().getWindow(); // Get the current stage
        StudentMenuController controller = new StudentMenuController(); // Call controller
        controller.showMenuScene(stage); // Navigate to Menu screen
    });
}
else if (title.trim().equalsIgnoreCase("Mess Change Request") || title.trim().equalsIgnoreCase("Mess Change\n   Request")) {
    outerBox.setOnMouseClicked(event -> {
        System.out.println("Mess Change card clicked"); // Debug
        Stage stage = (Stage) outerBox.getScene().getWindow();
        DashboardMessController controller = new DashboardMessController(this, stage);
        controller.handleMessChangeCardClick();
    });
}


        outerBox.getChildren().addAll(cardBox, flow);
        return outerBox;
    }

    private Label createSidebarItem(String text) {
        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Poppins", 18));
        label.setStyle("-fx-font-weight:bold");
        return label;
    }
}

