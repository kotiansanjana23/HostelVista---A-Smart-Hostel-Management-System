package com.hostelvista.view;

import com.hostelvista.controller.StudentAdmissionCont;
import com.hostelvista.controller.StudentdashboardCont;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
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

public class Studentdashboard {

    public Studentdashboard(Stage stage) {
        //TODO Auto-generated constructor stub
    }

    public Scene getScene() {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(60));
        sidebar.setStyle("-fx-background-color:#9B7EDC; -fx-background-radius : 20;");
        sidebar.setPrefWidth(400);

        Label studentName = new Label("Welcome\nStudentName!");
        studentName.setTextFill(Color.BLUEVIOLET);
        studentName.setFont(Font.font(16));
        studentName.setStyle("-fx-background-color: #D2B6F3;  -fx-background-radius: 5;");
        studentName.setAlignment(Pos.CENTER);
        studentName.setMaxWidth(600);
        studentName.setPadding(new Insets(0));
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

        HBox homeBox = createSidebarItemWithIcon("Home", "Assets\\Images\\hh.png", 70);
        HBox hostelInfoBox = createSidebarItemWithIcon("Hostel Information", "Assets\\Images\\doc.png", 0);
        HBox notificationBox = createSidebarItemWithIcon("Notification", "Assets\\Images\\noti.png", 0);
        HBox settingBox = createSidebarItemWithIcon("Setting", "Assets\\Images\\sett.png", 0);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(1, 130, 130, 40));
        grid.setHgap(120);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);

        grid.add(createCard("Admission","\\Assets\\Images\\building1.png"), 0, 0);
        grid.add(createCard("Mess","\\Assets\\Images\\restuarant.png"), 1, 0);
        grid.add(createCard("Ambulance","\\Assets\\Images\\ambu.png"), 2, 0);

        grid.add(createCard("Payment","\\\\Assets\\\\Images\\\\paymentt.png"), 0, 1);
        grid.add(createCard("Accessory","\\\\Assets\\\\Images\\\\bag.png"), 1, 1);
        grid.add(createCard("Fest Tracker","\\\\Assets\\\\Images\\\\arrows.png"), 2, 1);

        grid.add(createCard("Feedback","\\\\Assets\\\\Images\\\\handshake.png"), 0, 2);
        grid.add(createCard("Complaints","\\\\Assets\\\\Images\\\\docc.png"), 1, 2);
        grid.add(createCard("Facilities","\\\\Assets\\\\Images\\\\tt.png"), 2, 2);

        VBox rightContent = new VBox(10, createTopRightIcons(), grid);
        rightContent.setPadding(new Insets(20));
        rightContent.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(rightContent, Priority.ALWAYS);

        sidebar.getChildren().addAll(studentBox, homeBox, hostelInfoBox, notificationBox, spacer, settingBox);

        HBox root = new HBox(sidebar, rightContent);
        HBox.setMargin(sidebar, new Insets(10, 0, 10, 0));

        return new Scene(root, 1560, 790);
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
        new StudentdashboardCont().handleGymClick(stage); // ✅ Use the correct handler
    });
}


        return outerBox;
    }

    private Label createSidebarItem(String text) {
        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Poppins", 18));
        label.setStyle("-fx-font-weight:bold");
        return label;
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

        ImageView qrIcon = new ImageView("Assets\\Images\\qr.jpg");
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

        topRightBox.getChildren().addAll(chatIcon,qrIcon);
        return topRightBox;

        
    }
    

    public void start(Stage stage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}
