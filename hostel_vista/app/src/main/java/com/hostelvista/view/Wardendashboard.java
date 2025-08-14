package com.hostelvista.view;

import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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

// ✅ Import added
import com.hostelvista.view.HostelRulesView;
import com.hostelvista.view.NotificationPage;
import com.hostelvista.view.AboutUsView;

public class Wardendashboard {

    private BorderPane rootLayout = new BorderPane();
    private Stage primaryStage;
    private Scene scene;

public Wardendashboard(Stage stage) {
    this.primaryStage = stage;

    VBox sidebar = createSidebar(primaryStage);
    VBox rightContent = new VBox(10, createTopRightIcons(primaryStage), createDashboardGrid(primaryStage));
    rightContent.setPadding(new Insets(20));
    rightContent.setAlignment(Pos.TOP_RIGHT);
    HBox.setHgrow(rightContent, Priority.ALWAYS);

    rootLayout.setLeft(sidebar);
    rootLayout.setCenter(rightContent);

    // ✅ FIX: Initialize scene
    this.scene = new Scene(rootLayout, 1560, 790);
}


    public Wardendashboard() {
        //TODO Auto-generated constructor stub
    }

    public Scene getScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
       // this.scene = createScene();
        VBox sidebar = createSidebar(primaryStage);

        VBox rightContent = new VBox(10, createTopRightIcons(primaryStage), createDashboardGrid(primaryStage));
        rightContent.setPadding(new Insets(20));
        rightContent.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(rightContent, Priority.ALWAYS);

        rootLayout.setLeft(sidebar);
        rootLayout.setCenter(rightContent);

        return new Scene(rootLayout, 1560, 790);
    }

    public GridPane createDashboardGrid(Stage primaryStage) {
        GridPane grid = new GridPane();
      //  grid.setPadding(new Insets(0, 130, 130, 40));
        grid.setPadding(new Insets(0, 130, 130, 40));
        grid.setHgap(120);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);

        grid.add(createCard("Admission\n Approval", "Assets\\Images\\people.png", () -> {
            Scene newScene = new AdmissionApprovalApp().getScene(primaryStage);
            primaryStage.setScene(newScene);
        }), 0, 0);

        grid.add(createCard("Fees", "Assets\\Images\\feepayment.png", () -> {
            Scene newScene = new WardenFeesManage().getScene(primaryStage);
            primaryStage.setScene(newScene);
        }), 1, 0);

        grid.add(createCard("Mess", "Assets\\Images\\restuarant.png", () -> {
            Scene newScene = new WardenMessAdmApr().getScene(primaryStage);
            primaryStage.setScene(newScene);
        }), 2, 0);

        grid.add(createCard("Rooms", "Assets\\Images\\bed.png", () -> {
            Scene newScene = new Wardenroom().getScene(primaryStage);
            primaryStage.setScene(newScene);
        }), 0, 1);

        grid.add(createCard("Complaints", "Assets\\Images\\changerequest.png", () -> {
            Scene newScene = new WardenComplaintManage().getScene(primaryStage);
            primaryStage.setScene(newScene);
        }), 1, 1);

        grid.add(createCard("Fest Tracker", "Assets\\Images\\location.png", () -> {
            Scene newScene = new FestTracker().getScene(primaryStage);
            primaryStage.setScene(newScene);
        }), 2, 1);

        grid.add(createCard("Feedback", "Assets\\Images\\handshake.png", () -> {
            Scene newScene = new Feedbackwar().getScene(primaryStage);
            primaryStage.setScene(newScene);
        }), 0, 2);

        return grid;
    }

    private VBox createSidebar(Stage stage) {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20, 30, 20, 30));
        sidebar.setStyle("-fx-background-color:#9B7EDC; -fx-background-radius: 20;");
        sidebar.setPrefWidth(300);

        Label studentName = new Label("Welcome\nhostelvista3!");
        studentName.setTextFill(Color.BLUEVIOLET);
        studentName.setFont(Font.font(16));
        studentName.setStyle("-fx-background-color: #D2B6F3;  -fx-background-radius: 5;");
        studentName.setAlignment(Pos.CENTER);
        studentName.setMaxWidth(600);
        HBox.setHgrow(studentName, Priority.ALWAYS);

        Image profileImage = new Image("Assets\\Images\\profile.jpg");
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

        HBox homeBox = createSidebarItemWithIcon("Home", "Assets\\Images\\hh.png",40);
        HBox hostelInfoBox = createSidebarItemWithIcon("Hostel Information", "Assets\\Images\\doc.png",0);
        HBox notificationBox = createSidebarItemWithIcon("Notification", "Assets\\Images\\noti.png",0);

        VBox settingsDropdown = createSettingsDropdown(stage);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // homeBox.setOnMouseClicked(e -> {
        //     VBox rightContent = new VBox(10, createTopRightIcons(stage), createDashboardGrid(stage));
        //     rightContent.setPadding(new Insets(20));
        //     rightContent.setAlignment(Pos.TOP_RIGHT);
        //     HBox.setHgrow(rightContent, Priority.ALWAYS);
        //     rootLayout.setCenter(rightContent);
        // });

        // // ✅ Updated: Open HostelRulesView on click
        // hostelInfoBox.setOnMouseClicked(e -> {
        //     try {
        //         Scene rulesScene = new HostelRulesView().getScene(stage, stage.getScene());
        //         stage.setScene(rulesScene);
        //     } catch (Exception ex) {
        //         ex.printStackTrace();
        //     }
        // });

        // // ✅ Updated: Open NotificationPage on click
        // notificationBox.setOnMouseClicked(e -> {
        //     try {
        //         Scene notificationScene = new NotificationPage().getScene(stage, stage.getScene());
        //         stage.setScene(notificationScene);
        //     } catch (Exception ex) {
        //         ex.printStackTrace();
        //     }
        // });

        homeBox.setOnMouseClicked(e -> showDashboard());
        
        hostelInfoBox.setOnMouseClicked(e -> showHostelInfo());
        
        notificationBox.setOnMouseClicked(e -> showNotifications());

        sidebar.getChildren().addAll(studentBox, homeBox, hostelInfoBox, notificationBox, spacer, settingsDropdown);
        return sidebar;
    }

    private void showDashboard() {
        VBox rightContent = new VBox(10, createTopRightIcons(primaryStage), createDashboardGrid(primaryStage));
        rightContent.setPadding(new Insets(20));
        rightContent.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(rightContent, Priority.ALWAYS);
        rootLayout.setCenter(rightContent);
    }

    private void showHostelInfo() {
        BorderPane hostelInfoContent = new HostelRulesView().createMainContent(primaryStage);
        rootLayout.setCenter(hostelInfoContent);
    }

    private void showNotifications() {
        VBox notificationContent = new NotificationPage().createMainContent(primaryStage);
        rootLayout.setCenter(notificationContent);
    }
//     private void showNotifications() {
//     NotificationView notificationView = new NotificationView(primaryStage, "warden");
//     Scene notificationScene = notificationView.getScene();
//     primaryStage.setScene(notificationScene);
// }



    private VBox createSettingsDropdown(Stage stage) {
        Label aboutUsLabel = new Label("About Us");
        Label logoutLabel = new Label("Log Out");

        VBox subMenu = new VBox(10, aboutUsLabel, logoutLabel);
        subMenu.setPadding(new Insets(5, 0, 5, 45));
        subMenu.setVisible(false);
        subMenu.setManaged(false);

        aboutUsLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-cursor: hand;");
        logoutLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-cursor: hand;");

        HBox settingsHeader = createSidebarItemWithIcon("Settings", "Assets\\Images\\sett.png",0);

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

        // ✅ Updated: Open AboutUsView on click
        aboutUsLabel.setOnMouseClicked(e -> {
            try {
//                 Scene aboutScene = new AboutUsView().getScene(stage, stage.getScene());
// stage.setScene(aboutScene);
Node aboutContent = createAboutUsContent();
rootLayout.setCenter(aboutContent);


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

       logoutLabel.setOnMouseClicked(e -> {
    try {
        SigninView signinPage = new SigninView(stage);  // Pass stage if needed
        Scene signinScene = signinPage.getScene();
        stage.setScene(signinScene);
        stage.setTitle("Sign In");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});
        return new VBox(settingsHeader, subMenu);
    }
    
private Node createAboutUsContent() {
        VBox content = new VBox(35);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: white;");

        // Gratitude Section
        Label gratitudeTitle = new Label("Special Gratitude Towards");
        gratitudeTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        gratitudeTitle.setTextFill(Color.web("#5E35B1"));

        VBox gratitudeBox = new VBox(30);
        gratitudeBox.getChildren().addAll(
            createGratitudeCard("\\Assets\\Images\\ssir.jpg", "Shashi Sir",  " is more than just a teacher – he’s a guru who truly transforms students. His unwavering support and deep technical insight laid the strongest foundation for our growth, both as a developer and as a person. Every lesson, every piece of teaching from Shashi Sir shaped not just our technical skills but also boosted our confidence and clarity. We walking away from this journey not just with a completed project, but with immense gratitude for someone who genuinely cares about our success. Thank you, Shashi Sir, for being the pillar behind this transformation."),
            createGratitudeCard("\\Assets\\Images\\Core2web.jpg", "Core2Web", " has always been more than a coding class, but during the development of our HostelVista project, it became a true launchpad for growth. The environment here isn’t just about writing code – it’s about building confidence, mastering problem-solving, and learning how to think like a real-world developers. The culture at Core2Web pushes you to go beyond your limits – whether it’s cracking problems or completing full-fledged projects. We’re incredibly grateful to be a part of this community that shaped us not only as a coders but as a more confident individuals ready for real-world challenges."),
            createGratitudeCard("\\Assets\\Images\\Punam.jpg", "Punam Khedkar", " We experienced the true meaning of mentorship under Punam Khedkar. Her guidance was instrumental in bringing HostelVista to life – from design to deployment. Her mentorship played a crucial role in transforming our vision for HostelVista into a fully working Java-based application with Firebase integration. Her Personal Attention into project and solving every problem we had made a huge difference to our project. We are very greatfull to have her as our mentor.")
        );
        
        // About Us Section
        Label aboutUsTitleBanner = new Label("AboutUs");
        aboutUsTitleBanner.setStyle("-fx-background-color: #D6BCF6; -fx-padding: 10 250; -fx-background-radius: 15; -fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #5E35B1;");
        
        VBox aboutUsDetails = new VBox(20);
        aboutUsDetails.getChildren().addAll(
            createAboutUsText("Welcome to HostelVista – Simplifying Hostel Life, One Click at a Time.", "HostelVista is a smart and reliable hostel and mess management system."),
            createAboutUsText("Why HostelVista?", null),
            createAboutUsSubText("Secure Access & Role-Based Dashboards:", "Whether you're a student, warden, or administrator."),
            createAboutUsSubText("Complaint & Feedback Handling:", "Raise maintenance issues or share your thoughts with just a few taps."),
            createAboutUsText("Built by Students, For Students", "HostelVista isn't just a product — it's a solution crafted by passionate developers.")
        );
        
        // Team Section
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
        
        // Add a back button to this page
        BorderPane pageLayout = new BorderPane(scrollPane);
        pageLayout.setStyle("-fx-border-color: #D6BCF6; -fx-border-width: 2; -fx-border-radius: 20;");
        StackPane.setMargin(pageLayout, new Insets(30));

        SVGPath backArrow = new SVGPath();
        backArrow.setContent("M19 12H5M12 19l-7-7 7-7");
        StackPane backButton = new StackPane(backArrow);
        backButton.setPadding(new Insets(10));
        backButton.setOnMouseClicked(e -> rootLayout.setCenter(new StackPane(content)));
        HBox topBar = new HBox(new Region(), backButton);
        HBox.setHgrow(topBar.getChildren().get(0), Priority.ALWAYS);
        topBar.setPadding(new Insets(10, 10, 0, 0));
        pageLayout.setTop(topBar);

        return pageLayout;
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
    private ImageView createCircularImageView(String imagePath, int size) {
        ImageView imageView = new ImageView();
        try {
            imageView.setImage(new Image(getClass().getResourceAsStream("/" + imagePath)));
            //imageView.setImage(new Image(imagePath));
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imagePath);
        }
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        Circle clip = new Circle(size / 2.0, size / 2.0, size / 2.0);
        imageView.setClip(clip);
        return imageView;
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

    private HBox createSidebarItemWithIcon(String labelText, String imagePath, int topPadding) {
        ImageView icon = new ImageView();
        try {
            icon.setImage(new Image(imagePath));
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imagePath);
        }
        icon.setFitHeight(30);
        icon.setFitWidth(30);

        Label label = new Label(labelText);
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

    private Node createCard(String title, String imagePath, Runnable onClick) {
        VBox outerBox = new VBox(10);
        outerBox.setAlignment(Pos.TOP_CENTER);
        outerBox.setPrefWidth(250);

        StackPane cardBox = new StackPane();
        cardBox.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
        cardBox.setPadding(new Insets(20));
        cardBox.setPrefSize(200, 300);
        cardBox.setMinHeight(140);
        cardBox.setMaxHeight(140);
        cardBox.setMaxWidth(250);

        try {
            Image iconImage = new Image(imagePath);
            ImageView icon = new ImageView(iconImage);
            icon.setFitWidth(100);
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

        outerBox.getChildren().addAll(cardBox, flow);

        outerBox.setOnMouseEntered(e -> {
            outerBox.setScaleX(1.10);
            outerBox.setScaleY(1.10);
            outerBox.setStyle("-fx-cursor: hand;");
            cardBox.setStyle("-fx-background-color: #7f3fe0; -fx-background-radius: 20;");
        });

        outerBox.setOnMouseExited(e -> {
            outerBox.setScaleX(1.0);
            outerBox.setScaleY(1.0);
            cardBox.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
        });

        outerBox.setOnMouseClicked(e -> onClick.run());

        return outerBox;
    }

    public Node createTopRightIcons(Stage primaryStage) {
        HBox topRightBox = new HBox(15);
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.setPadding(new Insets(20, 30, 0, 0));

        ImageView qrIcon = new ImageView("Assets\\Images\\qr.jpg");
        qrIcon.setFitHeight(70);
        qrIcon.setFitWidth(80);

        qrIcon.setOnMouseEntered(e -> {
            qrIcon.setScaleX(1.10);
            qrIcon.setScaleY(1.10);
            qrIcon.setStyle("-fx-cursor: hand;");
        });

        qrIcon.setOnMouseExited(e -> {
            qrIcon.setScaleX(1.0);
            qrIcon.setScaleY(1.0);
        });

        qrIcon.setOnMouseClicked(e -> {
            try {
                HostelAttendanceQRGenerator qrPage = new HostelAttendanceQRGenerator();
                Scene currentDashboardScene = primaryStage.getScene();
                Scene qrScene = qrPage.getScene(primaryStage, currentDashboardScene);
                primaryStage.setScene(qrScene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        topRightBox.getChildren().add(qrIcon);
        return topRightBox;
    }

    public Scene getScene() {
        return scene;
        //return scene();
    }


    public void start(Stage stage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }


    
   
}
