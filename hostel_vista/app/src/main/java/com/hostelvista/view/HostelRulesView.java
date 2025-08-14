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

public class HostelRulesView {

    private BorderPane rootLayout;

//    @Override
// public void start(Stage primaryStage) {
//     rootLayout = new BorderPane(); // ✅ Initialize before sidebar uses it
//     rootLayout.setStyle("-fx-background-color: #F8F7FF;");

//     VBox sidebar = createSidebar(primaryStage); // now uses valid rootLayout
//     rootLayout.setLeft(sidebar);
//     BorderPane.setMargin(sidebar, new Insets(10));

//     BorderPane mainContent = createMainContent(primaryStage);
//     rootLayout.setCenter(mainContent);

//     Scene scene = new Scene(rootLayout, 1200, 800);
//     primaryStage.setScene(scene);
//     primaryStage.setTitle("Hostel Rules");
//     primaryStage.show();
// }


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

        // ✅ ADD FUNCTIONALITY: Navigate to Hostel Info Page
        hostelInfoBox.setOnMouseClicked(e -> {
            BorderPane hostelInfoContent = createHostelInfoContent();
            rootLayout.setCenter(hostelInfoContent);
        });

        sidebar.getChildren().addAll(studentBox, homeBox, hostelInfoBox, notificationBox, spacer, settingsContainer);
        return sidebar;
    }

    public BorderPane createMainContent(Stage stage) {
        BorderPane contentLayout = new BorderPane();
        contentLayout.setStyle("-fx-background-color: #EFEAFE;");
        StackPane.setMargin(contentLayout, new Insets(10));

        Label title = new Label("Hostel Rules & Regulations");
        title.setFont(Font.font("Arial", 26));
        title.setStyle("-fx-text-fill: #9f5fbfff; -fx-font-weight: bold;");

        SVGPath backArrow = new SVGPath();
        backArrow.setContent("M19 12H5M12 19l-7-7 7-7");
        backArrow.setStroke(Color.web("#555555"));
        backArrow.setStrokeWidth(2.5);

        StackPane backButton = new StackPane(backArrow);
        backButton.setPadding(new Insets(10));
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #E0E0E0; -fx-background-radius: 50; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: transparent;"));
        // backButton.setOnMouseClicked(e -> primaryStage.close());

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
        BorderPane topBar = new BorderPane();
        topBar.setLeft(title);
        topBar.setRight(backButton);
        topBar.setPadding(new Insets(20, 30, 10, 30));
        contentLayout.setTop(topBar);

        VBox rulesContainer = new VBox(20);
        rulesContainer.setAlignment(Pos.TOP_CENTER);
        String[][] rulesData = {
            {"1. Attendance and Curfew", "All students must return to the hostel before 9:00 PM.", "Attendance will be taken daily at 9:15 PM.", "Late entries without permission will be reported to the warden."},
            {"2. Cleanliness", "Rooms must be kept clean and tidy at all times.", "Littering in hallways and bathrooms is strictly prohibited."},
            {"3. Visitors Policy", "No visitors allowed in rooms without prior permission.", "Visiting hours are from 5:00 PM to 7:00 PM only."},
            {"4. Noise Control", "Maintain silence after 10:00 PM.", "No loud music or shouting inside hostel premises."},
            {"5. Mess Conduct", "Follow mess timings strictly.", "Wasting food is not tolerated.", "Maintain decorum during meal times."}
        };
        for (String[] ruleGroup : rulesData) {
            rulesContainer.getChildren().add(createRuleBox(ruleGroup));
        }

        ScrollPane scrollPane = new ScrollPane(rulesContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #EFEAFE; -fx-border-color: transparent;");
        scrollPane.setPadding(new Insets(10, 30, 20, 30));
        contentLayout.setCenter(scrollPane);

        return contentLayout;
    }

    private VBox createRuleBox(String[] ruleGroup) {
        VBox box = new VBox(8);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: white; -fx-border-color: #DAD2FF; -fx-border-radius: 12; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(127,61,255,0.15), 8, 0, 0, 4);");
        Label mainRule = new Label(ruleGroup[0]);
        mainRule.setFont(Font.font("Arial", 18));
        mainRule.setStyle("-fx-text-fill: #9a54baff; -fx-font-weight: bold; -fx-underline: true;");
        box.getChildren().add(mainRule);
        for (int i = 1; i < ruleGroup.length; i++) {
            Label subRule = new Label("• " + ruleGroup[i]);
            subRule.setFont(Font.font("Arial", 15));
            subRule.setTextFill(Color.web("#3F3F3F"));
            subRule.setWrapText(true);
            subRule.setPadding(new Insets(0, 0, 0, 10));
            box.getChildren().add(subRule);
        }
        return box;
    }

    private BorderPane createHostelInfoContent() {
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: #EFEAFE;");
        Label label = new Label("🏨 Hostel Information Page");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        label.setTextFill(Color.web("#9B7EDC"));
        StackPane center = new StackPane(label);
        center.setPadding(new Insets(20));
        layout.setCenter(center);
        return layout;
    }

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
