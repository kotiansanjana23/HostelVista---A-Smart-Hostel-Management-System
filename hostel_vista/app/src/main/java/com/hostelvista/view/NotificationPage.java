package com.hostelvista.view;

import javafx.animation.RotateTransition;
import javafx.application.Application;
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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NotificationPage {

    // @Override
    // public VBox createMainContent(Stage primaryStage) {
    //     // --- Root Layout ---
    //     BorderPane rootLayout = new BorderPane();
    //     rootLayout.setStyle("-fx-background-color: #F8F7FF;");

    //     // --- Sidebar ---
    //     VBox sidebar = createSidebar(primaryStage);
    //     rootLayout.setLeft(sidebar);
    //     BorderPane.setMargin(sidebar, new Insets(10));

    //     // --- Main Content ---
    //     VBox mainContent = createMainContent(primaryStage);
    //     rootLayout.setCenter(mainContent);

    //     // --- Scene and Stage Setup ---
    //     Scene scene = new Scene(rootLayout, 1200, 800);
    //     primaryStage.setScene(scene);
    //     primaryStage.setTitle("HostelVista - Notifications");
    //     primaryStage.show();
    // }

    // /**
    //  * Creates the sidebar on the left side of the screen.
    //  */
    private VBox createSidebar(Stage stage) {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20, 30, 20, 30));
        sidebar.setStyle("-fx-background-color:#9B7EDC; -fx-background-radius: 20;");
        sidebar.setPrefWidth(250);

        HBox studentBox = createProfileSection();
        HBox homeBox = createSidebarItemWithIcon("Home", "file:assets/images/hh.png", 20);
        HBox hostelInfoBox = createSidebarItemWithIcon("Hostel Information", "file:assets/images/doc.png", 0);
        HBox notificationBox = createSidebarItemWithIcon("Notification", "file:assets/images/noti.png", 0);
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        VBox settingsContainer = createSettingsDropdown(stage);

        sidebar.getChildren().addAll(studentBox, homeBox, hostelInfoBox, notificationBox, spacer, settingsContainer);
        return sidebar;
    }

    /**
     * Creates the main content area with the back button and notification tiles.
     */
    public VBox createMainContent(Stage stage) {
        VBox contentVBox = new VBox(10);
        contentVBox.setPadding(new Insets(30));
        contentVBox.setStyle("-fx-background-color: white; -fx-border-color: #D6BCF6; -fx-border-width: 2; -fx-border-radius: 20;");
        BorderPane.setMargin(contentVBox, new Insets(10));


        // --- Top bar with Back Button ---
        HBox topBar = createTopBar(stage);
        contentVBox.getChildren().add(topBar);

        // --- Tile titles and descriptions ---
        String[] titles = {"Admission", "Mess", "Feedback", "Complaints", "Ambulance"};
        String[] descriptions = {
            "New admission guidelines for 2025 batch released.",
            "Today's lunch includes Paneer Butter Masala & Rice.",
            "Feedback portal is open till Friday midnight.",
            "Your water leakage complaint has been resolved.",
            "Ambulance will be on standby during the college fest."
        };

        for (int i = 0; i < titles.length; i++) {
            VBox tile = createNotificationTile(titles[i], descriptions[i]);
            contentVBox.getChildren().add(tile);
        }

        // --- Wrap contentVBox in ScrollPane ---
        ScrollPane scrollPane = new ScrollPane(contentVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent; -fx-border-color: transparent;");
        
        VBox mainContainer = new VBox(scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        
        return mainContainer;
    }

    private HBox createTopBar(Stage stage) {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        SVGPath backArrow = new SVGPath();
        backArrow.setContent("M19 12H5M12 19l-7-7 7-7"); // Left arrow
        backArrow.setStroke(Color.web("#9f57c6"));
        backArrow.setStrokeWidth(2.5);

        StackPane backButton = new StackPane(backArrow);
        backButton.setPadding(new Insets(10));
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #E1D5F8; -fx-background-radius: 50; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: transparent;"));
        // backButton.setOnMouseClicked(e -> stage.close());
        backButton.setOnMouseClicked(e -> {
            // Go back to dashboard
            if (stage.getScene() != null && stage.getScene().getRoot() instanceof BorderPane) {
                BorderPane root = (BorderPane) stage.getScene().getRoot();
                VBox rightContent = new VBox(10, new Wardendashboard().createTopRightIcons(stage), 
                    new Wardendashboard().createDashboardGrid(stage));
                rightContent.setPadding(new Insets(20));
                rightContent.setAlignment(Pos.TOP_RIGHT);
                root.setCenter(rightContent);
            }
        });
        HBox topBar = new HBox(spacer, backButton);
        topBar.setAlignment(Pos.TOP_RIGHT);
        return topBar;
    }

    private VBox createNotificationTile(String titleText, String descriptionText) {
        VBox tile = new VBox(5);
        tile.setPadding(new Insets(30));
        tile.setAlignment(Pos.CENTER_LEFT);
        tile.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #9f57c6;" +
            "-fx-border-width: 1.2;" +
            "-fx-border-radius: 40;" +
            "-fx-background-radius: 40;"
        );

        Label title = new Label(titleText);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setTextFill(Color.PURPLE);

        Label description = new Label(descriptionText);
        description.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        description.setTextFill(Color.PURPLE);
        description.setWrapText(true);

        tile.getChildren().addAll(title, description);
        return tile;
    }
    
    // --- Helper methods for Sidebar ---
    
    private VBox createSettingsDropdown(Stage stage) {
        Label aboutUsLabel = new Label("About Us");
        Label logoutLabel = new Label("Log Out");
        VBox subMenu = new VBox(10, aboutUsLabel, logoutLabel);
        subMenu.setPadding(new Insets(5, 0, 5, 45));
        subMenu.setVisible(false);
        subMenu.setManaged(false);
        aboutUsLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: normal; -fx-cursor: hand;");
        logoutLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: normal; -fx-cursor: hand;");
        aboutUsLabel.setOnMouseEntered(e -> aboutUsLabel.setUnderline(true));
        aboutUsLabel.setOnMouseExited(e -> aboutUsLabel.setUnderline(false));
        logoutLabel.setOnMouseEntered(e -> logoutLabel.setUnderline(true));
        logoutLabel.setOnMouseExited(e -> logoutLabel.setUnderline(false));

        HBox settingsHeader = createSidebarItemWithIcon("Settings", "file:assets/images/sett.png", 0);
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
        aboutUsLabel.setOnMouseClicked(e -> System.out.println("About Us clicked!"));

        return new VBox(settingsHeader, subMenu);
    }

    private HBox createProfileSection() {
        Label studentName = new Label("Hi,StudentName !");
        studentName.setTextFill(Color.web("#5E35B1"));
        studentName.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        studentName.setAlignment(Pos.CENTER_LEFT);
        studentName.setPadding(new Insets(5, 5, 5, 10));
        ImageView profileImageView = createCircularImageView("file:assets/images/profile.jpg", 40);
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
