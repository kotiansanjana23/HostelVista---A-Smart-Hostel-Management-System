package com.hostelvista.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Set;

public class GymView extends Application {

    private StackPane backButton;

    private Stage primaryStage;

    public GymView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public GymView() {} // For JavaFX compatibility

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #FFFFFF;");
        root.setPadding(new Insets(20));

        // Top bar
        HBox topBar = createTopBar();
        root.setTop(topBar);

        // Main scrollable content
        VBox mainContent = createMainContent();
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");
        root.setCenter(scrollPane);

        // 🔙 Back button click: Navigate to DashboardgymView
        backButton.setOnMouseClicked(e -> {
    System.out.println("Back button clicked! Navigating to DashboardgymView...");
    DashboardGymView dashboardGymView = new DashboardGymView(primaryStage);
    primaryStage.setScene(dashboardGymView.getScene()); // ✅ switch to DashboardGymView
});


        primaryStage.setTitle("Gym Facilities");
        Scene scene = new Scene(root, 1560, 790);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createTopBar() {
        Label titleLabel = new Label("Gym");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.PURPLE);
        titleLabel.setStyle("-fx-background-color: #E1D5F8; -fx-background-radius: 15; -fx-padding: 10 30;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        SVGPath backArrow = new SVGPath();
        backArrow.setContent("M19 12H5M12 19l-7-7 7-7");
        backArrow.setStroke(Color.web("#9f57c6"));
        backArrow.setStrokeWidth(2.5);

        backButton = new StackPane(backArrow);
        backButton.setPadding(new Insets(10));
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #E1D5F8; -fx-background-radius: 50;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: transparent;"));

        HBox topBar = new HBox(titleLabel, spacer, backButton);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(10, 20, 10, 20));
        return topBar;
    }

    private VBox createMainContent() {
        VBox contentBox = new VBox(25);
        contentBox.setPadding(new Insets(25));
        contentBox.setStyle("-fx-border-color: #D6BCF6; -fx-border-width: 2; -fx-border-radius: 20; -fx-background-radius: 20;");
        BorderPane.setMargin(contentBox, new Insets(20, 0, 0, 0));

        Node timingsSection = createSection("Gym Timings:",
                "General Hours: 6:00 AM - 10:00 PM (Monday to Saturday) Sunday: Closed for maintenance",
                "Time Slots:",
                "Girls' Timings:",
                "    Morning: 6:30 AM - 8:00 AM",
                "    Evening: 4:00 PM - 5:30 PM",
                "Boys' Timings:",
                "    Morning: 8:00 AM - 10:00 AM",
                "    Evening: 6:00 PM - 9:00 PM",
                "Note: The gym is open for all residents during common hours not specified in the slots above, but priority will be given based on the schedule."
        );

        Node equipmentSection = createSection("Equipment List:",
                "Cardio Machines:",
                "    • Treadmills (2)",
                "    • Elliptical Cross-Trainer (1)",
                "    • Stationary Exercise Bike (1)",
                "Strength Training:",
                "    • Dumbbell Rack (Pairs from 2.5 kg to 20 kg)",
                "    • Barbell with Weight Plates",
                "    • Adjustable Bench Press",
                "    • Lat Pulldown Machine",
                "    • Leg Press Machine",
                "    • Pull-up Bar",
                "Accessories:",
                "    • Yoga Mats (5)",
                "    • Resistance Bands (Set of 3)",
                "    • Skipping Ropes",
                "    • Foam Roller"
        );

        Node contactSection = createSection("Contact Info:",
                "Gym In-charge / Trainer: Mr. Rajveer Singh",
                "    • Phone: +91 1234567891",
                "    • Email: gym.trainer@hostelvista.com",
                "    • Available for Assistance: 7:00 AM - 9:00 AM and 6:00 PM - 8:00 PM"
        );

        contentBox.getChildren().addAll(timingsSection, equipmentSection, contactSection);
        return contentBox;
    }

    private VBox createSection(String title, String... lines) {
        VBox sectionVBox = new VBox(8);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.web("#9c5abaff"));

        VBox linesVBox = new VBox(5);
        Set<String> boldLines = Set.of(
                "Time Slots:",
                "Girls' Timings:",
                "Boys' Timings:",
                "Note:",
                "Cardio Machines:",
                "Strength Training:",
                "Accessories:",
                "Gym In-charge / Trainer:"
        );

        for (String line : lines) {
            Text text = new Text(line);
            if (boldLines.stream().anyMatch(line::startsWith)) {
                text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            } else {
                text.setFont(Font.font("Arial", 14));
            }
            text.setFill(Color.web("#8e5ea7ff"));
            linesVBox.getChildren().add(text);
        }

        sectionVBox.getChildren().addAll(titleLabel, linesVBox);
        return sectionVBox;
    }
}

