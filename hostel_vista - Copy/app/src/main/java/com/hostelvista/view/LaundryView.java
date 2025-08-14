package com.hostelvista.view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.*;

public class LaundryView {

    private Stage stage;
    private Scene scene;
    private List<String> timeSlots = new ArrayList<>();

    public LaundryView(Stage stage) {
        this.stage = stage;
        initializeUI();
    }

    private void initializeUI() {
        VBox mainLayout = new VBox(30);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setStyle("-fx-background-color: white;");

        // Back button top right
        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: #9d64ff; -fx-text-fill: white; -fx-font-weight: bold;");
        backButton.setOnAction(e -> {
            new com.hostelvista.controller.DashboardGymController(stage).handleBackButton();
        });

        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.getChildren().add(backButton);
        topBar.setPadding(new Insets(0, 20, 0, 0));

        Text infoText = new Text("The laundry timings are 7:00 A.M - 10:00 P.M on weekends.\n" +
                "And 10:00 A.M - 7:00 P.M on working days.");
        infoText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        infoText.setFill(Color.PURPLE);
        infoText.setTextAlignment(TextAlignment.CENTER);

        HBox machinesBox = new HBox(50);
        machinesBox.setAlignment(Pos.CENTER);

        generateTimeSlots();

        machinesBox.getChildren().addAll(
                createMachine("Assets/images/image4.jpg"),
                createMachine("Assets/images/image4.jpg"),
                createMachine("Assets/images/image4.jpg")
        );

        mainLayout.getChildren().addAll(topBar, infoText, machinesBox);

        scene = new Scene(mainLayout,1560, 790);
    }

    public Scene getScene() {
        return scene;
    }

    private void generateTimeSlots() {
        for (int hour = 10; hour < 19; hour++) {
            String from = formatTime(hour);
            String to = formatTime(hour + 1);
            timeSlots.add(from + " - " + to);
        }
    }

    private VBox createMachine(String imagePath) {
        ImageView machineImage = new ImageView(new Image(imagePath));
        machineImage.setFitWidth(300);
        machineImage.setFitHeight(300);

        Label tickLabel = new Label("\u2713"); // Unicode checkmark
        tickLabel.setStyle("-fx-font-size: 72; -fx-text-fill: limegreen; -fx-font-weight: bold;");
        tickLabel.setVisible(false);

        StackPane machinePane = new StackPane(machineImage, tickLabel);
        machinePane.setStyle("-fx-background-color: #9463c5ff; -fx-background-radius: 100;");
        machinePane.setPadding(new Insets(30));

        ComboBox<String> slotBox = new ComboBox<>();
        slotBox.getItems().addAll(timeSlots);
        slotBox.setPromptText("Select Time Slot");
        slotBox.setPrefWidth(170);

        Set<String> bookedSlotsForMachine = new HashSet<>();

        Button bookBtn = new Button("Book Slot");
        bookBtn.setOnAction(e -> {
            String selectedSlot = slotBox.getValue();
            if (selectedSlot == null) {
                showAlert("Please select a time slot first.");
            } else if (bookedSlotsForMachine.contains(selectedSlot)) {
                showAlert("This slot is already booked for this machine.");
            } else {
                bookedSlotsForMachine.add(selectedSlot);
                showAlert("Slot " + selectedSlot + " booked successfully!");

                refreshComboBox(slotBox, bookedSlotsForMachine);

                if (bookedSlotsForMachine.size() == timeSlots.size()) {
                    tickLabel.setVisible(true);
                    slotBox.setDisable(true);
                    bookBtn.setDisable(true);
                }
            }
        });

        refreshComboBox(slotBox, bookedSlotsForMachine);

        VBox vbox = new VBox(10, machinePane, slotBox, bookBtn);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

    private void refreshComboBox(ComboBox<String> comboBox, Set<String> bookedSlots) {
        comboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setDisable(false);
                } else {
                    setText(item);
                    setDisable(bookedSlots.contains(item));
                    setStyle(bookedSlots.contains(item) ? "-fx-text-fill: gray;" : "-fx-text-fill: black;");
                }
            }
        });

        comboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null || bookedSlots.contains(item) ? null : item);
            }
        });
    }

    private String formatTime(int hour) {
        String period = (hour < 12) ? "A.M" : "P.M";
        int displayHour = (hour == 0) ? 12 : (hour > 12) ? hour - 12 : hour;
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

