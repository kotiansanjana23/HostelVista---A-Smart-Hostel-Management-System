package com.hostelvista.view;

import com.hostelvista.controller.DashboardGymController;

//port com.hostelvista.StudentdashboardCont;
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
import javafx.stage.Stage;

public class DashboardGymView {
    private Scene scene;

    public DashboardGymView(Stage stage) {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(60));
        sidebar.setStyle("-fx-background-color:#9B7EDC; -fx-background-radius : 20;");
        sidebar.setPrefWidth(220);

        Label studentName = new Label("Welcome\nStudentName!");
        studentName.setTextFill(Color.BLUEVIOLET);
        studentName.setStyle("-fx-background-color: #D2B6F3; -fx-background-radius: 5;");
        studentName.setAlignment(Pos.CENTER);
        studentName.setMaxWidth(600);
        studentName.setPadding(new Insets(0));

        ImageView profileImageView = new ImageView(new Image("\\Assets\\Images\\profile.jpg"));
        profileImageView.setFitWidth(60);
        profileImageView.setFitHeight(60);

        Circle clip = new Circle(30, 30, 30);
        profileImageView.setClip(clip);

        HBox studentBox = new HBox(profileImageView, studentName);
        studentBox.setAlignment(Pos.CENTER_LEFT);
        studentBox.setStyle("-fx-background-color: #D2B6F3; -fx-padding: 15px; -fx-background-radius: 10;");
        studentBox.setSpacing(5);
        studentBox.setMinHeight(80);

        // Sidebar items
        sidebar.getChildren().addAll(
            studentBox,
            createSidebarItem("Home", "\\Assets\\Images\\hh.png"),
            createSidebarItem("Hostel Information", "\\Assets\\Images\\doc.png"),
            createSidebarItem("Notification", "\\Assets\\Images\\noti.png"),
            new Region(),
            createSidebarItem("Setting", "\\Assets\\Images\\sett.png")
        );
        VBox.setVgrow(sidebar.getChildren().get(4), Priority.ALWAYS); // spacer

        // Cards grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(130, 130, 130, 0));
        grid.setHgap(120);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);

        grid.add(createCard("Gym", "\\Assets\\Images\\gym.png"), 0, 0);
        grid.add(createCard("Laundry", "\\Assets\\Images\\laundry.png"), 1, 0);
        grid.add(createCard("Study Room", "\\Assets\\Images\\studyroom.png"), 0, 1);

        // Back button in top-right
        ImageView arrowIcon = new ImageView(new Image("\\Assets\\Images\\backarrow.png"));
arrowIcon.setFitWidth(50);
arrowIcon.setFitHeight(50);

Button backButton = new Button();
backButton.setGraphic(arrowIcon);
backButton.setStyle("-fx-background-color: transparent;");

// ✅ Correct way to call controller
DashboardGymController controller = new DashboardGymController(stage);
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

        this.scene = new Scene(root, 1560, 790);
    }

    public Scene getScene() {
        return scene;
    }

    private HBox createSidebarItem(String text, String iconPath) {
        ImageView icon = new ImageView(new Image(iconPath));
        icon.setFitHeight(30);
        icon.setFitWidth(30);

        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-font-weight: bold;");
        label.setFont(javafx.scene.text.Font.font("Poppins", 18));
        label.setPadding(new Insets(0, 0, 0, 10));

        HBox box = new HBox(icon, label);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setSpacing(10);
        return box;
    }

    private VBox createCard(String title, String imagePath) {
        VBox outerBox = new VBox(10);
        outerBox.setAlignment(Pos.TOP_CENTER);
        outerBox.setPrefSize(250, 500);

        StackPane cardBox = new StackPane();
        cardBox.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
        cardBox.setPadding(new Insets(20));
        cardBox.setPrefSize(200, 140);
        cardBox.setMaxWidth(250);

        try {
            Image iconImage = new Image(imagePath);
            ImageView icon = new ImageView(iconImage);
            icon.setFitWidth(150);
            icon.setFitHeight(110);
            cardBox.getChildren().add(icon);
        } catch (Exception e) {
            Label fallback = new Label("No Image");
            fallback.setTextFill(Color.WHITE);
            cardBox.getChildren().add(fallback);
        }

        Label label = new Label(title);
        label.setTextFill(Color.BLUEVIOLET);
        label.setFont(javafx.scene.text.Font.font(25));
        label.setStyle("-fx-font-weight: bold;");
        label.setAlignment(Pos.CENTER);

        outerBox.getChildren().addAll(cardBox, label);

        outerBox.setOnMouseEntered(e -> {
            outerBox.setScaleX(1.1);
            outerBox.setScaleY(1.1);
            cardBox.setStyle("-fx-background-color: #7f3fe0; -fx-background-radius: 20;");
        });

        outerBox.setOnMouseExited(e -> {
            outerBox.setScaleX(1.0);
            outerBox.setScaleY(1.0);
            cardBox.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
        });

       
if (title.equalsIgnoreCase("Gym")) {
    outerBox.setOnMouseClicked(event -> {
        System.out.println("Gym card clicked");
        Stage stage = (Stage) outerBox.getScene().getWindow();
        new DashboardGymController(stage).handleGymFacilitiesClick();
    });
}
if (title.equalsIgnoreCase("Laundry")) {
    outerBox.setOnMouseClicked(event -> {
        System.out.println("Laundry card clicked");
        Stage stage = (Stage) outerBox.getScene().getWindow();
        new DashboardGymController(stage).handleLaundryClick(); // ✅ Call method in controller
    });
}



        return outerBox;
    }
}
