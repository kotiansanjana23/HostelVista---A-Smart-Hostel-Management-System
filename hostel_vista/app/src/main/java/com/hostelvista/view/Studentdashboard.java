// package com.hostelvista.view;

// import com.hostelvista.controller.StudentAdmissionCont;
// import com.hostelvista.controller.StudentdashboardCont;
// import com.hostelvista.controller.StudyRoomController;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Node;
// import javafx.scene.Scene;
// import javafx.scene.SnapshotParameters;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.image.WritableImage;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.shape.SVGPath;
// import javafx.scene.text.Font;
// import javafx.scene.text.TextFlow;
// import javafx.stage.Stage;

// public class Studentdashboard {

//     private BorderPane rootLayout;
//     private Node rulesContent;
//     private Node notificationContent;
//     private Node aboutUsContent; // To store the "About Us" page
//     private Pane blankContent;
//     private Stage stage;

//     public Studentdashboard(Stage stage2) {
//         //TODO Auto-generated constructor stub
//     }
//     public Scene getScene() {
//          rootLayout = new BorderPane(); // ✅ Initialize rootLayout
//          rootLayout.setPadding(new Insets(10));
//         VBox sidebar = new VBox(20);
//         sidebar.setPadding(new Insets(60));
//         sidebar.setStyle("-fx-background-color:#9B7EDC; -fx-background-radius : 20;");
//         sidebar.setPrefWidth(400);
//         rootLayout.setLeft(sidebar);

//         Label studentName = new Label("Welcome\nStudentName!");
//         studentName.setTextFill(Color.BLUEVIOLET);
//         studentName.setFont(Font.font(16));
//         studentName.setStyle("-fx-background-color: #D2B6F3;  -fx-background-radius: 5;");
//         studentName.setAlignment(Pos.CENTER);
//         studentName.setMaxWidth(600);
//         studentName.setPadding(new Insets(0));
//         HBox.setHgrow(studentName, Priority.ALWAYS);

//         Image profileImage = new Image("\\Assets\\Images\\profile.jpg");
//         ImageView profileImageView = new ImageView(profileImage);
//         profileImageView.setFitWidth(60);
//         profileImageView.setFitHeight(60);
//         profileImageView.setPreserveRatio(false);

//         Circle clip = new Circle(30, 30, 30);
//         profileImageView.setClip(clip);

//         SnapshotParameters parameters = new SnapshotParameters();
//         parameters.setFill(Color.TRANSPARENT);
//         WritableImage clippedImage = profileImageView.snapshot(parameters, null);
//         profileImageView.setClip(null);
//         profileImageView.setImage(clippedImage);

//         HBox studentBox = new HBox(profileImageView, studentName);
//         studentBox.setAlignment(Pos.CENTER_LEFT);
//         studentBox.setStyle("-fx-background-color: #D2B6F3; -fx-padding: 15px; -fx-background-radius: 10;");
//         studentBox.setSpacing(5);
//         studentBox.setMaxWidth(Double.MAX_VALUE);
//         studentBox.setMinHeight(80);

//         HBox homeBox = createSidebarItemWithIcon("Home", "\\Assets\\Images\\hh.png", 70);
//         HBox hostelInfoBox = createSidebarItemWithIcon("Hostel Information", "\\Assets\\Images\\doc.png", 0);
//         hostelInfoBox.setOnMouseClicked(event ->rootLayout.setCenter(rulesContent));
//         HBox notificationBox = createSidebarItemWithIcon("Notification", "Assets\\Images\\noti.png", 0);
//         HBox settingBox = createSidebarItemWithIcon("Setting", "Assets\\Images\\sett.png", 0);

//         Region spacer = new Region();
//         VBox.setVgrow(spacer, Priority.ALWAYS);

//         GridPane grid = new GridPane();
//         grid.setPadding(new Insets(1, 130, 130, 40));
//         grid.setHgap(120);
//         grid.setVgap(30);
//         grid.setAlignment(Pos.CENTER);

//         grid.add(createCard("Admission","\\Assets\\Images\\building1.png"), 0, 0);
//         grid.add(createCard("Mess","\\Assets\\Images\\restuarant.png"), 1, 0);
//         grid.add(createCard("Ambulance","\\Assets\\Images\\ambu.png"), 2, 0);

//         grid.add(createCard("Payment","\\Assets\\Images\\paymentt.png"), 0, 1);
//         grid.add(createCard("Accessory","\\Assets\\Images\\bag.png"), 1, 1);
//         grid.add(createCard("Fest Tracker","\\Assets\\Images\\arrows.png"), 2, 1);

//         grid.add(createCard("Feedback","\\Assets\\Images\\handshake.png"), 0, 2);
//         grid.add(createCard("Complaints","\\Assets\\Images\\docc.png"), 1, 2);
//         grid.add(createCard("Facilities","\\Assets\\Images\\tt.png"), 2, 2);

//         VBox rightContent = new VBox(10, createTopRightIcons(), grid);
//         rightContent.setPadding(new Insets(20));
//         rightContent.setAlignment(Pos.TOP_RIGHT);
         
//         rootLayout.setCenter(createDashboardContent()); // dashboard on the center
//         HBox.setHgrow(rightContent, Priority.ALWAYS);

//         sidebar.getChildren().addAll(studentBox, homeBox, hostelInfoBox, notificationBox, spacer, settingBox);

//         HBox root = new HBox(sidebar, rightContent);
//          // Assign content used in sidebar buttons
//     rulesContent = createHostelRulesContent();
//     blankContent = new StackPane(); // Placeholder content
//         HBox.setMargin(sidebar, new Insets(10, 0, 10, 0));

//         return new Scene(root, 1560, 790);
//     }
//     private VBox createDashboardContent() {
//     GridPane grid = new GridPane();
//     // populate grid as before...

//     VBox rightContent = new VBox(10, createTopRightIcons(), grid);
//     rightContent.setPadding(new Insets(20));
//     rightContent.setAlignment(Pos.TOP_RIGHT);

//     return rightContent;
// }


//     private HBox createSidebarItemWithIcon(String text, String imagePath, int topPadding) {
//         ImageView icon = new ImageView(imagePath);
//         icon.setFitHeight(30);
//         icon.setFitWidth(30);

//         Label label = createSidebarItem(text);
//         label.setPadding(new Insets(0, 0, 0, 10));

//         HBox box = new HBox(icon, label);
//         box.setAlignment(Pos.CENTER_LEFT);
//         box.setSpacing(10);
//         box.setPadding(new Insets(topPadding, 0, 0, 0));
//         return box;
//     }

//     private Node createCard(String title, String imagePath) {
//         VBox outerBox = new VBox(10);
//         outerBox.setAlignment(Pos.TOP_CENTER);
//         outerBox.setPrefWidth(250);

//         StackPane cardBox = new StackPane();
//         cardBox.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
//         cardBox.setPadding(new Insets(20));
//         cardBox.setPrefSize(200, 300);
//         cardBox.setMinHeight(140);
//         cardBox.setMaxHeight(140);
//         cardBox.setMaxWidth(250);

//         try {
//             Image iconImage = new Image(imagePath);
//             ImageView icon = new ImageView(iconImage);
//             icon.setFitWidth(100);
//             icon.setFitHeight(100);
//             cardBox.getChildren().add(icon);
//         } catch (Exception e) {
//             System.err.println("Image failed to load: " + title);
//             e.printStackTrace();
//             Label fallback = new Label("No Image");
//             fallback.setTextFill(Color.WHITE);
//             fallback.setFont(Font.font(14));
//             cardBox.getChildren().add(fallback);
//         }

//         Label label1 = new Label(title);
//         label1.setFont(Font.font(25));
//         label1.setTextFill(Color.BLUEVIOLET);
//         label1.setWrapText(true);
//         label1.setMaxWidth(200);
//         label1.setAlignment(Pos.CENTER);
//         label1.setStyle("-fx-font-weight: bold");

//         TextFlow flow = new TextFlow(label1);
//         flow.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
//         flow.setMaxWidth(200);
//         flow.setStyle("-fx-padding: 5;");
//         flow.setLineSpacing(3);

//         outerBox.getChildren().addAll(cardBox, flow);

//         outerBox.setOnMouseEntered(e -> {
//             outerBox.setScaleX(1.10);
//             outerBox.setScaleY(1.10);
//             outerBox.setStyle("-fx-cursor: hand;");
//             cardBox.setStyle("-fx-background-color: #7f3fe0; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 4);");
//         });

//         outerBox.setOnMouseExited(e -> {
//             outerBox.setScaleX(1.0);
//             outerBox.setScaleY(1.0);
//             cardBox.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
//         });

//         if (title.equalsIgnoreCase("Admission")) {
//         outerBox.setOnMouseClicked(e -> {
//             Stage stage = (Stage) outerBox.getScene().getWindow();
//             StudentAdmissionCont controller = new StudentAdmissionCont(stage);
//             controller.handleAdmissionClick();
//         });
//     }
//     if (title.equalsIgnoreCase("Feedback")) {
//     outerBox.setOnMouseClicked(e -> {
//         Stage stage = (Stage) outerBox.getScene().getWindow();
//         StudentdashboardCont controller = new StudentdashboardCont();
//         controller.handleFeedbackClick(stage);
//     });
// }
// if (title.equalsIgnoreCase("Fest Tracker")) {
//     outerBox.setOnMouseClicked(e -> {
//         Stage stage = (Stage) outerBox.getScene().getWindow();
//         StudentdashboardCont controller = new StudentdashboardCont();
//         controller.handleFestTrackerClick(stage);
//     });
// } 
// if (title.equalsIgnoreCase("Payment")) {
//     outerBox.setOnMouseClicked(e -> {
//         Stage stage = (Stage) outerBox.getScene().getWindow();
//         DashboardFeesView feesView = new DashboardFeesView(stage);
//         Scene feesScene = feesView.getScene();
//         stage.setScene(feesScene);
//     });
// }
// if (title.equalsIgnoreCase("Complaints")) {
//     outerBox.setOnMouseClicked(e -> {
//         Stage stage = (Stage) outerBox.getScene().getWindow();
//         StudentdashboardCont controller = new StudentdashboardCont();
//         controller.handleComplaintClick(stage);
//     });
// }
// if(title.equalsIgnoreCase("Mess")){
//     outerBox.setOnMouseClicked(e -> {
//         Stage stage = (Stage) outerBox.getScene().getWindow();
//         StudentdashboardCont controller = new StudentdashboardCont();
//         controller.handleMessClick(stage);
//     });
// }

// if (title.equalsIgnoreCase("Ambulance")) {
//     outerBox.setOnMouseClicked(event -> {
//         Stage stage = (Stage) outerBox.getScene().getWindow();
//         StudentdashboardCont controller = new StudentdashboardCont();
//         controller.handleAmbulanceClick(stage);
//     });
// }

// if (title.equalsIgnoreCase("Facilities")) {
//     outerBox.setOnMouseClicked(event -> {
//         Stage stage = (Stage) outerBox.getScene().getWindow();
//         new StudentdashboardCont().handleGymClick(stage); // ✅ Use the correct handler
//     });
// }
// if (title.equalsIgnoreCase("Accessory")) {
//     outerBox.setOnMouseClicked(e -> {
//         Stage stage = (Stage) outerBox.getScene().getWindow();
//         new StudentdashboardCont().handleAccessoryClick(stage); // make sure method exists
//     });
// }
//         return outerBox;
//     }

//     private Label createSidebarItem(String text) {
//         Label label = new Label(text);
//         label.setTextFill(Color.WHITE);
//         label.setFont(Font.font("Poppins", 18));
//         label.setStyle("-fx-font-weight:bold");
//         return label;
//     }

//     private Node createTopRightIcons() {
//         HBox topRightBox = new HBox(15);
//         topRightBox.setAlignment(Pos.TOP_RIGHT);
//         topRightBox.setPadding(new Insets(20, 30, 0, 0));

//     ImageView chatIcon = new ImageView("\\Assets\\Images\\chatbot.jpg");
//     chatIcon.setStyle("-fx-background-radius:20");
//     chatIcon.setFitHeight(70);
//     chatIcon.setFitWidth(80);
    
//     chatIcon.setOnMouseEntered(e -> {
//         chatIcon.setScaleX(1.10);
//         chatIcon.setScaleY(1.10);
//         chatIcon.setStyle("-fx-cursor: hand;");
//         chatIcon.setStyle("-fx-background-color: #7f3fe0; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 4);");
//     });

//     chatIcon.setOnMouseExited(e -> {
//         chatIcon.setScaleX(1.0);
//        chatIcon.setScaleY(1.0);
//        chatIcon.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
//     });

//         ImageView qrIcon = new ImageView("\\Assets\\Images\\qr.jpg");
//         qrIcon.setStyle("-fx-background-radius:20");
//         qrIcon.setFitHeight(70);
//         qrIcon.setFitWidth(80);

//         qrIcon.setOnMouseEntered(e -> {
//             qrIcon.setScaleX(1.10);
//             qrIcon.setScaleY(1.10);
//             qrIcon.setStyle("-fx-cursor: hand; -fx-background-color: #7f3fe0; -fx-background-radius: 20;");
//         });

//         qrIcon.setOnMouseExited(e -> {
//             qrIcon.setScaleX(1.0);
//             qrIcon.setScaleY(1.0);
//             qrIcon.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
//         });

//         topRightBox.getChildren().addAll(chatIcon,qrIcon);
//         return topRightBox;        
//     }
//     public void start(Stage stage) {
//         this.stage = stage;
//     }

//     public void show(Stage stage) {
//     Scene scene = getScene(); // this builds the UI
//     stage.setScene(scene);
//     stage.setTitle("Student Dashboard");
//     stage.show();
// }

//     public static Scene getDashboardScene(Stage stage2) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getDashboardScene'");
//     }

// private Node createHostelRulesContent() {
//         BorderPane rulesPageLayout = new BorderPane();
//         rulesPageLayout.setStyle("-fx-background-color: #EFEAFE; -fx-border-color: #D6BCF6; -fx-border-width: 2; -fx-border-radius: 20;");
//         StackPane.setMargin(rulesPageLayout, new Insets(30));
//         Label title = new Label("Hostel Rules & Regulations");
//         title.setFont(Font.font("Arial", 26));
//         title.setStyle("-fx-text-fill: #9f5fbfff; -fx-font-weight: bold;");
//         SVGPath backArrow = new SVGPath();
//         backArrow.setContent("M19 12H5M12 19l-7-7 7-7");
//         backArrow.setStroke(Color.web("#555555"));
//         backArrow.setStrokeWidth(2.5);
//         StackPane backButton = new StackPane(backArrow);
//         backButton.setPadding(new Insets(10));
//         backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #E0E0E0; -fx-background-radius: 50; -fx-cursor: hand;"));
//         backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: transparent;"));
//         backButton.setOnMouseClicked(e -> rootLayout.setCenter(new StackPane(blankContent)));
//         BorderPane topBar = new BorderPane();
//         topBar.setLeft(title);
//         topBar.setRight(backButton);
//         topBar.setPadding(new Insets(20, 30, 10, 30));
//         rulesPageLayout.setTop(topBar);
//         VBox rulesContainer = new VBox(20);
//         rulesContainer.setAlignment(Pos.TOP_CENTER);
//         String[][] rulesData = {
//             {"1. Attendance and Curfew", "All students must return to the hostel before 9:00 PM.", "Attendance will be taken daily at 9:15 PM.", "Late entries without permission will be reported to the warden."},
//             {"2. Cleanliness", "Rooms must be kept clean and tidy at all times.", "Littering in hallways and bathrooms is strictly prohibited."},
//             {"3. Visitors Policy", "No visitors allowed in rooms without prior permission.", "Visiting hours are from 5:00 PM to 7:00 PM only."},
//             {"4. Noise Control", "Maintain silence after 10:00 PM.", "No loud music or shouting inside hostel premises."},
//             {"5. Mess Conduct", "Follow mess timings strictly.", "Wasting food is not tolerated.", "Maintain decorum during meal times."}
//         };
//         for (String[] ruleGroup : rulesData) {
//             rulesContainer.getChildren().add(createRuleBox(ruleGroup));
//         }
//         ScrollPane scrollPane = new ScrollPane(rulesContainer);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setStyle("-fx-background: #EFEAFE; -fx-border-color: transparent;");
//         scrollPane.setPadding(new Insets(10, 30, 20, 30));
//         rulesPageLayout.setCenter(scrollPane);
//         return rulesPageLayout;
//     }
//     private VBox createRuleBox(String[] ruleGroup) {
//         VBox box = new VBox(8);
//         box.setPadding(new Insets(15));
//         box.setStyle("-fx-background-color: white; -fx-border-color: #DAD2FF; -fx-border-radius: 12; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(127,61,255,0.15), 8, 0, 0, 4);");
//         Label mainRule = new Label(ruleGroup[0]);
//         mainRule.setFont(Font.font("Arial", 18));
//         mainRule.setStyle("-fx-text-fill: #9a54baff; -fx-font-weight: bold; -fx-underline: true;");
//         box.getChildren().add(mainRule);
//         for (int i = 1; i < ruleGroup.length; i++) {
//             Label subRule = new Label("• " + ruleGroup[i]);
//             subRule.setFont(Font.font("Arial", 15));
//             subRule.setTextFill(Color.web("#3F3F3F"));
//             subRule.setWrapText(true);
//             subRule.setPadding(new Insets(0, 0, 0, 10));
//             box.getChildren().add(subRule);
//         }
//         return box;
//     }
   
// }  

package com.hostelvista.view;

import com.hostelvista.controller.ChatController;
import com.hostelvista.controller.StudentAdmissionCont;
import com.hostelvista.controller.StudentdashboardCont;
import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
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

public class Studentdashboard {

    private BorderPane rootLayout;
    private Node rulesContent;
    private Node notificationContent;
    private Node aboutUsContent;
    private Pane blankContent;
    private Stage stage;
    private Node dashboardContent;


    public Studentdashboard(Stage stage) {
        this.stage = stage;
    }

    public Studentdashboard() {
        //TODO Auto-generated constructor stub
    }

    public Scene getScene() {
        rootLayout = new BorderPane();
        rootLayout.setStyle("-fx-background-color: #F8F7FF;");
        rootLayout.setPadding(new Insets(10));

        // --- Create Sidebar ---
        VBox sidebar = createSidebar(stage);
        rootLayout.setLeft(sidebar);
        BorderPane.setMargin(sidebar, new Insets(10));

        // --- Create Content Panes ---
        rulesContent = createHostelRulesContent();
        notificationContent = createNotificationContent();
        aboutUsContent = createAboutUsContent();

        blankContent = new Pane();
        blankContent.setStyle("-fx-background-color: white; -fx-background-radius: 20;");
        StackPane.setMargin(blankContent, new Insets(30));

//     Button notificationButton = new Button("Notifications");
//     notificationButton.setOnAction(e -> {
//     NotificationView notificationView = new NotificationView(stage, "student");
//     stage.setScene(notificationView.getScene());
// });

// Add this button to your existing layout
// sidebar.getChildren().add(notificationButton); // or wherever you're adding nav buttons


//         dashboardContent = new VBox(10, createTopRightIcons());
// dashboardContent.setStyle("-fx-background-color: white; -fx-background-radius: 20;");
// dashboardContent.setPadding(new Insets(20));
// dashboardContent.setAlignment(Pos.TOP_RIGHT);
//         // --- Set initial content ---
        rootLayout.setCenter(new StackPane(blankContent));

        // Create dashboard grid content
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(1, 130, 130, 40));
        grid.setHgap(120);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);

        grid.add(createCard("Admission", "\\Assets\\Images\\building1.png"), 0, 0);
        grid.add(createCard("Mess", "\\Assets\\Images\\restuarant.png"), 1, 0);
        grid.add(createCard("Ambulance", "\\Assets\\Images\\ambu.png"), 2, 0);

        grid.add(createCard("Payment", "\\Assets\\Images\\paymentt.png"), 0, 1);
        grid.add(createCard("Accessory", "\\Assets\\Images\\bag.png"), 1, 1);
        grid.add(createCard("Fest Tracker", "\\Assets\\Images\\arrows.png"), 2, 1);

        grid.add(createCard("Feedback", "\\Assets\\Images\\handshake.png"), 0, 2);
        grid.add(createCard("Complaints", "\\Assets\\Images\\docc.png"), 1, 2);
        grid.add(createCard("Facilities", "\\Assets\\Images\\tt.png"), 2, 2);

        // dashboardContent = new VBox(10, createTopRightIcons(),grid);
        // dashboardContent.setStyle("-fx-background-color: white; -fx-background-radius: 20;");

        VBox rightContent = new VBox(10, createTopRightIcons(), grid);
        dashboardContent=rightContent;

        rightContent.setPadding(new Insets(20));
        rightContent.setAlignment(Pos.TOP_RIGHT);

        // Set the dashboard content as default
        rootLayout.setCenter(rightContent);
        return new Scene(rootLayout, 1560, 790);
    }

    private VBox createSidebar(Stage stage) {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20, 30, 20, 30));
        sidebar.setStyle("-fx-background-color:#9B7EDC; -fx-background-radius: 20;");
        sidebar.setPrefWidth(300);

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

        //HBox studentBox = createProfileSection();
        HBox homeBox = createSidebarItemWithIcon("Home", "\\Assets\\Images\\hh.png", 40);
        HBox hostelInfoBox = createSidebarItemWithIcon("Hostel Information", "\\Assets\\Images\\doc.png", 0);
        HBox notificationBox = createSidebarItemWithIcon("Notification", "\\Assets\\Images\\noti.png", 0);
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Settings dropdown
        VBox settingsContainer = createSettingsDropdown(stage);

        // Click handlers
        hostelInfoBox.setOnMouseClicked(event -> rootLayout.setCenter(rulesContent));
        notificationBox.setOnMouseClicked(event -> rootLayout.setCenter(notificationContent));
        homeBox.setOnMouseClicked(event -> rootLayout.setCenter(new StackPane(dashboardContent)));

        sidebar.getChildren().addAll(studentBox, homeBox, hostelInfoBox, notificationBox, spacer, settingsContainer);
        return sidebar;
    }

    private VBox createSettingsDropdown(Stage stage) {
        // Sub-items that will be hidden/shown
        Label aboutUsLabel = new Label("About Us");
        Label logoutLabel = new Label("Log Out");
        
        VBox subMenu = new VBox(10, aboutUsLabel, logoutLabel);
        subMenu.setPadding(new Insets(5, 0, 5, 45));
        subMenu.setVisible(false);
        subMenu.setManaged(false);

        // Style for sub-menu items
        aboutUsLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: normal; -fx-cursor: hand;");
        logoutLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: normal; -fx-cursor: hand;");
        
        // Add hover effects for sub-items
        aboutUsLabel.setOnMouseEntered(e -> aboutUsLabel.setUnderline(true));
        aboutUsLabel.setOnMouseExited(e -> aboutUsLabel.setUnderline(false));
       logoutLabel.setOnMouseEntered(e -> logoutLabel.setUnderline(true));
       logoutLabel.setOnMouseExited(e -> logoutLabel.setUnderline(false));
        

        // Header for the dropdown (Icon, "Settings", Arrow)
        HBox settingsHeader = createSidebarItemWithIcon("Settings", "\\assets\\images\\sett.png", 0);
        SVGPath dropdownArrow = new SVGPath();
        dropdownArrow.setContent("M7 10l5 5 5-5z"); // Down arrow
        dropdownArrow.setFill(Color.WHITE);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        settingsHeader.getChildren().addAll(spacer, dropdownArrow);

        // Click logic to expand/collapse
        settingsHeader.setOnMouseClicked(e -> {
            boolean isVisible = !subMenu.isVisible();
            subMenu.setVisible(isVisible);
            subMenu.setManaged(isVisible);
            
            // Animate arrow rotation
            RotateTransition rt = new RotateTransition(Duration.millis(200), dropdownArrow);
            rt.setToAngle(isVisible ? 180 : 0);
            rt.play();
        });
        
        // Click logic for sub-items
        aboutUsLabel.setOnMouseClicked(e -> rootLayout.setCenter(aboutUsContent));
        // logoutLabel.setOnMouseClicked(e -> {
        //     System.out.println("Logout clicked. Closing application.");
        //     stage.close();
        // });
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
        backButton.setOnMouseClicked(e -> rootLayout.setCenter(new StackPane(blankContent)));
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

    private Node createNotificationContent() {
        VBox contentVBox = new VBox(20);
        contentVBox.setPadding(new Insets(30));
        contentVBox.setStyle("-fx-background-color: white;");
        HBox topBar = createNotificationTopBar();
        contentVBox.getChildren().add(topBar);
        String[] titles = {"Admission", "Mess", "Feedback", "Complaints", "Ambulance"};
        String[] descriptions = {
            "New admission guidelines for 2025 batch released.",
            "Today's lunch includes Paneer Butter Masala & Rice.",
            "Feedback portal is open till Friday midnight.",
            "Your water leakage complaint has been resolved.",
            "Ambulance will be on standby during the college fest."
        };
        for (int i = 0; i < titles.length; i++) {
            contentVBox.getChildren().add(createNotificationTile(titles[i], descriptions[i]));
        }
        ScrollPane scrollPane = new ScrollPane(contentVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent; -fx-border-color: #D6BCF6; -fx-border-width: 2; -fx-border-radius: 20;");
        StackPane.setMargin(scrollPane, new Insets(30));
        return scrollPane;
    }

    private HBox createNotificationTopBar() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        SVGPath backArrow = new SVGPath();
        backArrow.setContent("M19 12H5M12 19l-7-7 7-7");
        backArrow.setStroke(Color.web("#9f57c6"));
        backArrow.setStrokeWidth(2.5);
        StackPane backButton = new StackPane(backArrow);
        backButton.setPadding(new Insets(10));
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #E1D5F8; -fx-background-radius: 50; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: transparent;"));
        backButton.setOnMouseClicked(e -> rootLayout.setCenter(new StackPane(blankContent)));
        return new HBox(spacer, backButton);
    }

    private VBox createNotificationTile(String titleText, String descriptionText) {
        VBox tile = new VBox(5);
        tile.setPadding(new Insets(30));
        tile.setAlignment(Pos.CENTER_LEFT);
        tile.setStyle("-fx-background-color: white;-fx-border-color: #9f57c6;-fx-border-width: 1.2;-fx-border-radius: 40;-fx-background-radius: 40;");
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

    private Node createHostelRulesContent() {
        BorderPane rulesPageLayout = new BorderPane();
        rulesPageLayout.setStyle("-fx-background-color: #EFEAFE; -fx-border-color: #D6BCF6; -fx-border-width: 2; -fx-border-radius: 20;");
        StackPane.setMargin(rulesPageLayout, new Insets(30));
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
        backButton.setOnMouseClicked(e -> rootLayout.setCenter(new StackPane(blankContent)));
        BorderPane topBar = new BorderPane();
        topBar.setLeft(title);
        topBar.setRight(backButton);
        topBar.setPadding(new Insets(20, 30, 10, 30));
        rulesPageLayout.setTop(topBar);
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
        rulesPageLayout.setCenter(scrollPane);
        return rulesPageLayout;
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

    private HBox createProfileSection() {
        Label studentName = new Label("Hi, StudentName!");
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

    private Node createCard(String title, String imagePath) {
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
            System.err.println("Image failed to load: " + title);
            e.printStackTrace();
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
            cardBox.setStyle("-fx-background-color: #7f3fe0; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 4);");
        });

        outerBox.setOnMouseExited(e -> {
            outerBox.setScaleX(1.0);
            outerBox.setScaleY(1.0);
            cardBox.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
        });

        if (title.equalsIgnoreCase("Admission")) {
            outerBox.setOnMouseClicked(e -> {
                Stage stage = (Stage) outerBox.getScene().getWindow();
                StudentAdmissionCont controller = new StudentAdmissionCont(stage);
                controller.handleAdmissionClick();
            });
        }
        if (title.equalsIgnoreCase("Feedback")) {
            outerBox.setOnMouseClicked(e -> {
                Stage stage = (Stage) outerBox.getScene().getWindow();
                StudentdashboardCont controller = new StudentdashboardCont();
                controller.handleFeedbackClick(stage);
            });
        }
        if (title.equalsIgnoreCase("Fest Tracker")) {
            outerBox.setOnMouseClicked(e -> {
                Stage stage = (Stage) outerBox.getScene().getWindow();
                StudentdashboardCont controller = new StudentdashboardCont();
                controller.handleFestTrackerClick(stage);
            });
        } 
        if (title.equalsIgnoreCase("Payment")) {
            outerBox.setOnMouseClicked(e -> {
                Stage stage = (Stage) outerBox.getScene().getWindow();
                DashboardFeesView feesView = new DashboardFeesView(stage);
                Scene feesScene = feesView.getScene();
                stage.setScene(feesScene);
            });
        }
        if (title.equalsIgnoreCase("Complaints")) {
            outerBox.setOnMouseClicked(e -> {
                Stage stage = (Stage) outerBox.getScene().getWindow();
                StudentdashboardCont controller = new StudentdashboardCont();
                controller.handleComplaintClick(stage);
            });
        }
        if(title.equalsIgnoreCase("Mess")){
            outerBox.setOnMouseClicked(e -> {
                Stage stage = (Stage) outerBox.getScene().getWindow();
                StudentdashboardCont controller = new StudentdashboardCont();
                controller.handleMessClick(stage);
            });
        }
        if (title.equalsIgnoreCase("Ambulance")) {
            outerBox.setOnMouseClicked(event -> {
                Stage stage = (Stage) outerBox.getScene().getWindow();
                StudentdashboardCont controller = new StudentdashboardCont();
                controller.handleAmbulanceClick(stage);
            });
        }
        if (title.equalsIgnoreCase("Facilities")) {
            outerBox.setOnMouseClicked(event -> {
                Stage stage = (Stage) outerBox.getScene().getWindow();
                new StudentdashboardCont().handleGymClick(stage);
            });
        }
        if (title.equalsIgnoreCase("Accessory")) {
            outerBox.setOnMouseClicked(e -> {
                Stage stage = (Stage) outerBox.getScene().getWindow();
                new StudentdashboardCont().handleAccessoryClick(stage);
            });
        }
        return outerBox;
    }

    private Node createTopRightIcons() {
        HBox topRightBox = new HBox(15);
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.setPadding(new Insets(20, 30, 0, 0));

        ImageView chatIcon = new ImageView("\\Assets\\Images\\chatbot.jpg");
        chatIcon.setStyle("-fx-background-radius:20");
        chatIcon.setFitHeight(70);
        chatIcon.setFitWidth(80);
        
        chatIcon.setOnMouseEntered(e -> {
            chatIcon.setScaleX(1.10);
            chatIcon.setScaleY(1.10);
            chatIcon.setStyle("-fx-cursor: hand;");
            chatIcon.setStyle("-fx-background-color: #7f3fe0; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 4);");
        });

        chatIcon.setOnMouseExited(e -> {
            chatIcon.setScaleX(1.0);
            chatIcon.setScaleY(1.0);
            chatIcon.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
        });
       chatIcon.setOnMouseClicked(e -> {
    ChatView chatView = new ChatView();
    ChatController chatController = new ChatController(chatView);
    chatView.show();
});


        ImageView qrIcon = new ImageView("\\Assets\\Images\\qr.jpg");
        qrIcon.setStyle("-fx-background-radius:20");
        qrIcon.setFitHeight(70);
        qrIcon.setFitWidth(80);

        qrIcon.setOnMouseEntered(e -> {
            qrIcon.setScaleX(1.10);
            qrIcon.setScaleY(1.10);
            qrIcon.setStyle("-fx-cursor: hand; -fx-background-color: #7f3fe0; -fx-background-radius: 20;");
        });

        qrIcon.setOnMouseExited(e -> {
            qrIcon.setScaleX(1.0);
            qrIcon.setScaleY(1.0);
            qrIcon.setStyle("-fx-background-color: #9959e7ff; -fx-background-radius: 20;");
        });
        qrIcon.setOnMouseClicked(e -> {
    QRScannerView scannerView = new QRScannerView(stage);  // pass existing Stage
    scannerView.show();
});

        topRightBox.getChildren().addAll(chatIcon, qrIcon);
        return topRightBox;        
    }

    public void show(Stage stage) {
        Scene scene = getScene();
        stage.setScene(scene);
        stage.setTitle("Student Dashboard");
        //stage.getIcons().add(new Image("\\Assests\\Images\\hostelvista.jpg"));
        stage.show();
    }

    public static Scene getDashboardScene(Stage stage) {
        Studentdashboard dashboard = new Studentdashboard(stage);
        return dashboard.getScene();
    }

    public void start(Stage primaryStage) {
       
    }

    public Scene getScene(Stage stage2) {
        return getScene();
    }

    
}