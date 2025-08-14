package com.hostelvista.view;

import com.hostelvista.controller.DashboardExchangeController;

import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.application.HostServices;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class DashboardExchangeView {
    private Scene scene;
    private Stage stage;
    private DashboardExchangeController controller;
    private HostServices hostServices;

    // public DashboardExchangeView(Stage stage) {
    //     this.stage = stage;
    //     this.controller = new DashboardExchangeController(this, stage);
    //     createUI();
    // }
    public DashboardExchangeView(Stage stage, HostServices hostServices) {
    this.stage = stage;
    this.hostServices = hostServices;
    this.controller = new DashboardExchangeController(this, stage, hostServices);
    createUI();
}
    
    public void createUI() {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(60));
        sidebar.setStyle("-fx-background-color:#9B7EDC; -fx-background-radius : 20;");
        sidebar.setPrefWidth(350);

        Label studentName = new Label("Welcome\nhostelvista1");
        studentName.setTextFill(Color.BLUEVIOLET);
        studentName.setFont(Font.font(16));
        studentName.setStyle("-fx-background-color: #D2B6F3;  -fx-background-radius: 5;");
        studentName.setMaxWidth(600);
        studentName.setPadding(new Insets(0));
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
        studentBox.setStyle("-fx-background-color: #D2B6F3; -fx-padding: 15px; -fx-background-radius: 10;");
        studentBox.setMaxWidth(Double.MAX_VALUE);
        studentBox.setMinHeight(80);

        HBox homeBox = controller.createSidebarItemWithIcon("Home", "\\Assets\\Images\\hh.png", 70);
        HBox hostelInfoBox = controller.createSidebarItemWithIcon("Hostel Information", "\\Assets\\Images\\doc.png", 0);
        HBox notificationBox = controller.createSidebarItemWithIcon("Notification", "\\Assets\\Images\\noti.png", 0);
        HBox settingBox = controller.createSidebarItemWithIcon("Settings", "\\Assets\\Images\\sett.png", 0);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().addAll(studentBox, homeBox, hostelInfoBox, notificationBox, spacer, settingBox);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(130, 130, 130, 0));
        grid.setHgap(120);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);

        // Create cards
        grid.add(controller.createCard("Guest Room\n  Allocation", "\\Assets\\Images\\guest.png"), 0, 0);
        grid.add(controller.createCard("Exchange", "\\Assets\\Images\\exchange.png"), 1, 0);
//        grid.add(controller.createCard(
//     "Guest Room\n  Allocation",
//     "Assets/images/guest.png",
//     (Runnable) controller::handleGuestCardClick,
//     20,
//     250
// ), 1, 0);
        ImageView arrowIcon = new ImageView(new Image("\\Assets\\Images\\backarrow.png"));
        arrowIcon.setFitWidth(50);
        arrowIcon.setFitHeight(50);

        Button backButton = new Button();
        backButton.setGraphic(arrowIcon);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> controller.navigateToStudentDashboard());

        HBox topRight = new HBox(backButton);
        topRight.setAlignment(Pos.TOP_RIGHT);
        topRight.setPadding(new Insets(10, 10, 0, 0));

        VBox rightContent = new VBox(10, topRight, grid);
        rightContent.setPadding(new Insets(20));
        rightContent.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(rightContent, Priority.ALWAYS);

        HBox mainLayout = new HBox(sidebar, rightContent);
        HBox.setMargin(sidebar, new Insets(10, 0, 10, 0));

        scene = new Scene(mainLayout, 1560, 790);
        stage.setScene(scene);
        stage.setTitle("HostelVista");
        stage.show();
    }

    public void show(Stage stage2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }

    public Scene getScene() {
        // TODO Auto-generated method stub
        return scene;
    }

    public Stage getStage() {
       return this.stage;
       
}
    public void start(Stage stage2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}