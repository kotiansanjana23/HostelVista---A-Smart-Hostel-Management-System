
package com.hostelvista.view;

import com.hostelvista.controller.DashboardFeesController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

public class DashboardFeesView {
    private Scene scene;
    private Stage stage;
    private DashboardFeesController controller;

    public DashboardFeesView(Stage stage) {
        this.stage = stage;
        this.controller = new DashboardFeesController(this);
        buildUI();
    }

    public Scene getScene() {
        return scene;
    }

    private void buildUI() {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(60));
        sidebar.setStyle("-fx-background-color:#9B7EDC; -fx-background-radius : 20;");
        sidebar.prefWidth(220);

        Label studentName = new Label("Welcome\nhostelvista1!");
        studentName.setTextFill(Color.BLUEVIOLET);
        studentName.setFont(Font.font(16));
        studentName.setStyle("-fx-background-color: #D2B6F3;  -fx-background-radius: 5;");
        studentName.setAlignment(Pos.CENTER);
        studentName.setMaxWidth(600);
        HBox.setHgrow(studentName, Priority.ALWAYS);

        Image profileImage = new Image("\\Assets\\Images\\profile.jpg");
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitWidth(60);
        profileImageView.setFitHeight(60);

        Circle clip = new Circle(30, 30, 30);
        profileImageView.setClip(clip);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage clippedImage = profileImageView.snapshot(parameters, null);
        profileImageView.setClip(null);
        profileImageView.setImage(clippedImage);

        HBox studentBox = new HBox(profileImageView, studentName);
        studentBox.setAlignment(Pos.CENTER_LEFT);
        studentBox.setSpacing(5);
        studentBox.setMaxWidth(Double.MAX_VALUE);
        studentBox.setMinHeight(80);
        studentBox.setStyle("-fx-background-color: #D2B6F3; -fx-padding: 15px; -fx-background-radius: 10;");

        HBox homeBox = createSidebarItemWithIcon("Home", "\\Assets\\Images\\hh.png", 70);
        HBox hostelInfoBox = createSidebarItemWithIcon("Hostel Information", "\\Assets\\Images\\doc.png", 0);
        HBox notificationBox = createSidebarItemWithIcon("Notification", "\\Assets\\Images\\noti.png", 0);
        HBox settingBox = createSidebarItemWithIcon("Settings", "\\Assets\\Images\\sett.png", 0);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(130, 130, 130, 0));
        grid.setHgap(120);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);

        grid.add(controller.createCard("Fees Payment", "\\Assets\\Images\\feepayment.png"), 0, 0);
        grid.add(controller.createCard("Late feePayment", "\\Assets\\Images\\latepayment.png"), 1, 0);
        grid.add(controller.createCard("Payment Receipt", "\\Assets\\Images\\paymentreceipt.png"), 0, 1);

        ImageView arrowIcon = new ImageView(new Image("\\Assets\\Images\\backarrow.png"));
        arrowIcon.setFitWidth(50);
        arrowIcon.setFitHeight(50);
        Button backButton = new Button();
        backButton.setGraphic(arrowIcon);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> controller.handleBackClick(stage));
        

        HBox topRight = new HBox(backButton);
        topRight.setAlignment(Pos.TOP_RIGHT);
        topRight.setPadding(new Insets(10, 10, 0, 0));

        VBox rightContent = new VBox(10, topRight, grid);
        rightContent.setPadding(new Insets(20));
        rightContent.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(rightContent, Priority.ALWAYS);

        sidebar.getChildren().addAll(studentBox, homeBox, hostelInfoBox, notificationBox, spacer, settingBox);

        HBox hb = new HBox(sidebar, rightContent);
        HBox.setMargin(sidebar, new Insets(10, 0, 10, 0));

        this.scene = new Scene(hb, 1560, 790);
    }

    private HBox createSidebarItemWithIcon(String text, String imagePath, int topPadding) {
        ImageView icon = new ImageView(imagePath);
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

    public Stage getStage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStage'");
    }
}

