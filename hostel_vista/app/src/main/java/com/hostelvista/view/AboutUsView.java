package com.hostelvista.view;

import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AboutUsView {

    public Scene getScene(Stage primaryStage, Scene previousScene) {
        BorderPane rootLayout = new BorderPane();
        rootLayout.setStyle("-fx-background-color: #F8F7FF;");

        VBox sidebar = createSidebar(primaryStage, previousScene);
        rootLayout.setLeft(sidebar);
        BorderPane.setMargin(sidebar, new Insets(10));

        BorderPane mainContent = createMainContent(primaryStage, previousScene);
        rootLayout.setCenter(mainContent);

        return new Scene(rootLayout, 1560, 790);
    }

    private VBox createSidebar(Stage stage, Scene previousScene) {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20, 30, 20, 30));
        sidebar.setStyle("-fx-background-color:#9B7EDC; -fx-background-radius: 20;");
        sidebar.setPrefWidth(250);

        HBox studentBox = createProfileSection();
        HBox homeBox = createSidebarItemWithIcon("Home", "file:Assets/Images/hh.png", 20);
        HBox hostelInfoBox = createSidebarItemWithIcon("Hostel Information", "file:Assets/Images/doc.png", 0);
        HBox notificationBox = createSidebarItemWithIcon("Notification", "file:Assets/Images/noti.png", 0);
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        VBox settingsContainer = createSettingsDropdown(stage);

        homeBox.setOnMouseClicked(e -> stage.setScene(previousScene));

        sidebar.getChildren().addAll(studentBox, homeBox, hostelInfoBox, notificationBox, spacer, settingsContainer);
        return sidebar;
    }

    private BorderPane createMainContent(Stage primaryStage, Scene previousScene) {
        VBox content = new VBox(35);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: white;");

        Label gratitudeTitle = new Label("Special Gratitude Towards");
        gratitudeTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        gratitudeTitle.setTextFill(Color.web("#5E35B1"));

        VBox gratitudeBox = new VBox(30);
        gratitudeBox.getChildren().addAll(
            createGratitudeCard("file:Assets/Images/Sir.jpg", "Shashi Sir", " is more than just a teacher – he’s a guru who truly transforms students..."),
            createGratitudeCard("file:Assets/Images/C2W.jpg", "Core2Web", " has always been more than a coding class..."),
            createGratitudeCard("file:Assets/Images/punam.jpg", "Punam Khedkar", ". We experienced the true meaning of mentorship under Punam Khedkar...")
        );

        Label aboutUsTitleBanner = new Label("AboutUs");
        aboutUsTitleBanner.setStyle("-fx-background-color: #D6BCF6; -fx-padding: 10 250; -fx-background-radius: 15; -fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #5E35B1;");

        VBox aboutUsDetails = new VBox(20);
        aboutUsDetails.getChildren().addAll(
            createAboutUsText("Welcome to HostelVista – Simplifying Hostel Life, One Click at a Time.", "HostelVista is a smart and reliable hostel and mess management system..."),
            createAboutUsText("Why HostelVista?", null),
            createAboutUsSubText("Secure Access & Role-Based Dashboards:", "Whether you’re a student, warden, or administrator..."),
            createAboutUsSubText("Complaint & Feedback Handling:", "Raise maintenance issues or share your thoughts..."),
            createAboutUsSubText("Smart Mess Management", "Switch your mess, get daily menus, and give feedback..."),
            createAboutUsSubText("Room & Attendance Management:", "Easy room shifting requests, attendance logs..."),
            createAboutUsSubText("Emergency Help:", "Quick ambulance requests and emergency contacts..."),
            createAboutUsSubText("Real-Time Notifications:", "Stay updated with announcements, fines, events..."),
            createAboutUsText("Built by Students, For Students", "HostelVista isn’t just a product — it’s a solution crafted by passionate developers...")
        );

        VBox teamSection = new VBox(10);
        teamSection.setAlignment(Pos.CENTER);
        teamSection.setStyle("-fx-background-color: #E8E2F4; -fx-padding: 20; -fx-background-radius: 15;");
        Label teamTitle = new Label("Team Behind HostelVista");
        teamTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        teamTitle.setTextFill(Color.web("#9148c1"));
        Label teamMembers = new Label("● Hemangi Purkar ● Jidnyasa Kale ● Sanjana Kotian ● Swapnil Kadam");
        teamMembers.setFont(Font.font("Arial", 16));
        teamMembers.setTextFill(Color.web("#9148c1"));
        teamSection.getChildren().addAll(teamTitle, teamMembers);

        content.getChildren().addAll(gratitudeTitle, gratitudeBox, aboutUsTitleBanner, aboutUsDetails, teamSection);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: white; -fx-border-color: transparent;");

        BorderPane pageLayout = new BorderPane(scrollPane);
        pageLayout.setStyle("-fx-background-color: #F8F7FF; -fx-border-color: #D6BCF6; -fx-border-width: 2; -fx-border-radius: 20;");

        SVGPath backArrow = new SVGPath();
        backArrow.setContent("M19 12H5M12 19l-7-7 7-7");
        backArrow.setFill(Color.DARKVIOLET);
        backArrow.setScaleX(2);
        backArrow.setScaleY(2);

        StackPane backButton = new StackPane(backArrow);
        backButton.setPadding(new Insets(10));
        backButton.setOnMouseClicked(e -> primaryStage.setScene(previousScene));

        HBox topBar = new HBox(new Region(), backButton);
        HBox.setHgrow(topBar.getChildren().get(0), Priority.ALWAYS);
        topBar.setPadding(new Insets(10, 10, 0, 0));
        pageLayout.setTop(topBar);

        return pageLayout;
    }

    private VBox createSettingsDropdown(Stage stage) {
        Label aboutUsLabel = new Label("About Us");
        Label logoutLabel = new Label("Log Out");

        VBox subMenu = new VBox(10, aboutUsLabel, logoutLabel);
        subMenu.setPadding(new Insets(5, 0, 5, 45));
        subMenu.setVisible(false);
        subMenu.setManaged(false);

        aboutUsLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-cursor: hand;");
        logoutLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-cursor: hand;");

        HBox settingsHeader = createSidebarItemWithIcon("Settings", "file:Assets/Images/sett.png", 0);

        SVGPath dropdownArrow = new SVGPath();
        dropdownArrow.setContent("M7 10l5 5 5-5z");
        dropdownArrow.setFill(Color.WHITE);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        settingsHeader.getChildren().addAll(spacer, dropdownArrow);

        settingsHeader.setOnMouseClicked(e -> {
            boolean isVisible = !subMenu.isVisible();
            subMenu.setVisible(isVisible);
            subMenu.setManaged(isVisible);
            RotateTransition rt = new RotateTransition(Duration.millis(200), dropdownArrow);
            rt.setToAngle(isVisible ? 180 : 0);
            rt.play();
        });

        logoutLabel.setOnMouseClicked(e -> stage.close());

        return new VBox(settingsHeader, subMenu);
    }

    private HBox createProfileSection() {
        Label studentName = new Label("Hi, StudentName !");
        studentName.setTextFill(Color.web("#5E35B1"));
        studentName.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        studentName.setAlignment(Pos.CENTER_LEFT);
        studentName.setPadding(new Insets(5, 5, 5, 10));

        ImageView profileImageView = createCircularImageView("file:Assets/Images/profile.jpg", 40);

        HBox studentBox = new HBox(10, profileImageView, studentName);
        studentBox.setAlignment(Pos.CENTER_LEFT);
        studentBox.setStyle("-fx-background-color: #D2B6F3; -fx-padding: 10px; -fx-background-radius: 10;");
        studentBox.setMaxWidth(Double.MAX_VALUE);
        return studentBox;
    }

    private HBox createSidebarItemWithIcon(String text, String imagePath, int topPadding) {
        ImageView icon = new ImageView();
        try {
            icon.setImage(new Image(imagePath));
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imagePath);
        }
        icon.setFitHeight(30);
        icon.setFitWidth(30);

        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Arial", 18));
        label.setStyle("-fx-font-weight:bold");

        HBox box = new HBox(15, icon, label);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(topPadding, 10, 0, 10));
        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: #8A6ECB; -fx-background-radius: 8; -fx-cursor: hand;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: transparent;"));

        return box;
    }

    private HBox createGratitudeCard(String imagePath, String boldText, String regularText) {
        ImageView photo = createCircularImageView(imagePath, 100);

        Text bold = new Text(boldText);
        bold.setFill(Color.web("#9148c1"));
        bold.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 14));

        Text regular = new Text(regularText);
        regular.setFill(Color.web("#9148c1"));
        regular.setFont(Font.font("Arial", FontPosture.ITALIC, 14));

        TextFlow description = new TextFlow(bold, regular);
        description.setLineSpacing(5);

        HBox card = new HBox(20, photo, description);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: #F5F1FD; -fx-background-radius: 15;");
        return card;
    }

    private VBox createAboutUsText(String title, String body) {
        VBox box = new VBox(5);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.web("#5E35B1"));
        box.getChildren().add(titleLabel);
        if (body != null && !body.isEmpty()) {
            Label bodyLabel = new Label(body);
            bodyLabel.setWrapText(true);
            bodyLabel.setFont(Font.font("Arial", 14));
            bodyLabel.setTextFill(Color.web("#5E35B1"));
            box.getChildren().add(bodyLabel);
        }
        return box;
    }

    private VBox createAboutUsSubText(String title, String body) {
        VBox box = createAboutUsText(title, body);
        box.setPadding(new Insets(0, 0, 0, 15));
        return box;
    }

    private ImageView createCircularImageView(String imagePath, int size) {
        ImageView imageView = new ImageView();
        try {
            imageView.setImage(new Image(imagePath));
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imagePath);
        }
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        Circle clip = new Circle(size / 2.0, size / 2.0, size / 2.0);
        imageView.setClip(clip);
        return imageView;
    }
}
