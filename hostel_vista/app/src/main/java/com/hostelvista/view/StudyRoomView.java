package com.hostelvista.view;

import com.hostelvista.controller.StudyRoomController;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.*;

public class StudyRoomView {

    private Stage stage;
    private final List<String> timeSlots = new ArrayList<>();
    private Scene scene;

    public StudyRoomView(Stage stage) {
        this.stage = stage;
        scene = createStudyRoomScene();
    }

    public Scene getScene() {
        return scene;
    }

    private Scene createStudyRoomScene() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: white;");

        // 🔙 Back Button with Arrow Icon (Right side)
        InputStream is = getClass().getResourceAsStream("/Assets/Images/backarrow.png");
        ImageView arrowIcon = null;
        if (is != null) {
            Image arrowImg = new Image(is);
            arrowIcon = new ImageView(arrowImg);
            arrowIcon.setFitWidth(40);
            arrowIcon.setFitHeight(40);
        } else {
            System.out.println("⚠️ Back arrow image not found");
        }

        Button backBtn = new Button();
        if (arrowIcon != null) backBtn.setGraphic(arrowIcon);
        backBtn.setStyle("-fx-background-color: transparent;");
        backBtn.setOnAction(e -> new StudyRoomController(stage).handleBackButton());

        HBox topBar = new HBox(backBtn);
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPadding(new Insets(10, 20, 0, 0)); // Top, Right, Bottom, Left

        Text title = new Text("Available study tables are shown below.\nTimings: 10:00 A.M - 6:00 P.M");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.PURPLE);
        title.setTextAlignment(TextAlignment.CENTER);

        generateTimeSlots();

        GridPane grid = new GridPane();
        grid.setHgap(40);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));

        for (int i = 0; i < 8; i++) {
            VBox tableBox = createTablePane(i);
            grid.add(tableBox, i % 4, i / 4);
        }

        root.getChildren().addAll(topBar, title, grid);
        return new Scene(root, 1560, 790);
    }

    private void generateTimeSlots() {
        for (int hour = 10; hour < 18; hour += 2) {
            String from = formatTime(hour);
            String to = formatTime(hour + 2);
            timeSlots.add(from + " - " + to);
        }
    }

    private VBox createTablePane(int index) {
        ImageView imageView = new ImageView(new Image("Assets/images/image5.jpg"));
        imageView.setFitWidth(180);
        imageView.setFitHeight(160);

        Rectangle clip = new Rectangle(180, 160);
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        imageView.setClip(clip);

        Label tick = new Label("✓");
        tick.setStyle("-fx-font-size: 48; -fx-text-fill: limegreen; -fx-font-weight: bold;");
        tick.setVisible(false);

        StackPane imagePane = new StackPane(imageView, tick);
        imagePane.setPadding(new Insets(10));
        imagePane.setAlignment(Pos.CENTER);

        ComboBox<String> slotBox = new ComboBox<>();
        slotBox.getItems().addAll(timeSlots);
        slotBox.setPromptText("Select Time Slot");
        slotBox.setPrefWidth(200);

        Set<String> bookedSlots = new HashSet<>();

        Button bookBtn = new Button("Book");
        bookBtn.setOnAction(e -> {
            String selected = slotBox.getValue();
            if (selected == null) {
                showAlert("Please select a time slot.");
            } else if (bookedSlots.contains(selected)) {
                showAlert("Slot already booked.");
            } else {
                bookedSlots.add(selected);
                showAlert("Table " + (index + 1) + " booked for " + selected);
                refreshComboBox(slotBox, bookedSlots);
                if (bookedSlots.size() == timeSlots.size()) {
                    tick.setVisible(true);
                    slotBox.setDisable(true);
                    bookBtn.setDisable(true);
                }
            }
        });

        refreshComboBox(slotBox, bookedSlots);

        VBox tableBox = new VBox(15, imagePane, slotBox, bookBtn);
        tableBox.setAlignment(Pos.CENTER);
        return tableBox;
    }

    private void refreshComboBox(ComboBox<String> comboBox, Set<String> bookedSlots) {
        comboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(item);
                if (empty || item == null) {
                    setText(null);
                } else if (bookedSlots.contains(item)) {
                    setTextFill(Color.GRAY);
                    setDisable(true);
                } else {
                    setTextFill(Color.BLACK);
                    setDisable(false);
                }
            }
        });
    }

    private String formatTime(int hour) {
        String period = (hour < 12) ? "A.M" : "P.M";
        int displayHour = (hour > 12) ? hour - 12 : hour;
        return String.format("%d:00 %s", displayHour, period);
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking Info");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
