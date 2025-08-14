package com.hostelvista.view;

import com.hostelvista.controller.StudentAdmissionCont;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class StudentAdmission {
    private Stage stage;
    private StudentAdmissionCont controller;

    public StudentAdmission(Stage stage) {
        this.stage = stage;
        this.controller = new StudentAdmissionCont(stage);
    }

    public Scene getScene() {
        VBox sidebar = createSidebar();
        GridPane grid = createDashboardCards();

        // Back Button
        ImageView arrowIcon = new ImageView(new Image("\\Assets\\Images\\backarrow.png"));
        arrowIcon.setFitWidth(50);
        arrowIcon.setFitHeight(50);

        Button backButton = new Button();
        backButton.setGraphic(arrowIcon);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> controller.goBack());

        HBox topRight = new HBox(backButton);
        topRight.setAlignment(Pos.TOP_RIGHT);
        topRight.setPadding(new Insets(10, 10, 0, 0));

        VBox rightContent = new VBox(topRight, grid);
        rightContent.setSpacing(30);

        HBox mainLayout = new HBox(sidebar, rightContent);
        mainLayout.setPadding(new Insets(20));

        return new Scene(mainLayout, 1560, 790);
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(60));
        sidebar.setStyle("-fx-background-color:#9B7EDC; -fx-background-radius: 20;");
        sidebar.setPrefWidth(380);

        Label studentName = new Label("Welcome\nStudentName!");
        studentName.setTextFill(Color.BLUEVIOLET);
        studentName.setFont(Font.font(16));
        studentName.setStyle("-fx-background-color: #D2B6F3; -fx-background-radius: 5;");
        studentName.setMaxWidth(180);
        studentName.setPadding(new Insets(0));
        HBox.setHgrow(studentName, Priority.ALWAYS);

        Image profileImage = new Image("\\Assets\\Images\\profile.jpg");
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitWidth(60);
        profileImageView.setFitHeight(60);
        profileImageView.setClip(new Circle(30, 30, 30));

        HBox studentBox = new HBox(profileImageView, studentName);
        studentBox.setAlignment(Pos.CENTER_LEFT);
        studentBox.setSpacing(5);
        studentBox.setStyle("-fx-background-color: #D2B6F3; -fx-padding: 15px; -fx-background-radius: 10;");
        studentBox.setMinHeight(80);

        HBox homeBox = createSidebarItemWithIcon("Home", "\\Assets\\Images\\hh.png", 70);
        HBox hostelInfoBox = createSidebarItemWithIcon("Hostel Information", "\\Assets\\Images\\doc.png", 0);
        HBox notificationBox = createSidebarItemWithIcon("Notification", "\\Assets\\Images\\noti.png", 0);
        HBox settingBox = createSidebarItemWithIcon("Setting", "\\Assets\\Images\\sett.png", 0);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        sidebar.getChildren().addAll(studentBox, homeBox, hostelInfoBox, notificationBox, spacer, settingBox);
        return sidebar;
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

    private Label createSidebarItem(String text) {
        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Poppins", 18));
        label.setStyle("-fx-font-weight:bold");
        return label;
    }

    private GridPane createDashboardCards() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(115, 80, 80, 40));
        grid.setHgap(120);
        grid.setVgap(30);

        grid.add(createCard("Room Allocation", "\\Assets\\Images\\bed.png", controller::handleRoomBookingClick), 0, 0);
        grid.add(createCard("Admission Form", "\\Assets\\Images\\people.png", controller::handleAdmissionFormClick), 1, 0);

        return grid;
    }

    private VBox createCard(String title, String imagePath, Runnable onClick) {
        VBox outerBox = new VBox(10);
        outerBox.setAlignment(Pos.TOP_CENTER);
        outerBox.setPrefWidth(350);

        StackPane cardBox = new StackPane();
        cardBox.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
        cardBox.setPadding(new Insets(20));
        cardBox.setPrefSize(200, 300);
        cardBox.setMinHeight(200);
        cardBox.setMaxHeight(200);
        cardBox.setMaxWidth(300);

        try {
            ImageView icon = new ImageView(new Image(imagePath));
            icon.setFitWidth(100);
            icon.setFitHeight(70);
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
        label1.setStyle("-fx-font-weight: bold");

        TextFlow flow = new TextFlow(label1);
        flow.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        flow.setMaxWidth(160);

        outerBox.setOnMouseClicked(e -> onClick.run());
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

        outerBox.getChildren().addAll(cardBox, flow);
        return outerBox;
    }

    public void start(Stage stage2) {
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}
