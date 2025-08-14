package com.hostelvista.view;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.hostelvista.Authentication.FirebaseInitializer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Wardenroom  {

    private final TableView<StudentRow> table = new TableView<>();

    public Scene getScene(Stage primaryStage) {
        FirebaseInitializer.initialize();  // Firebase init
        fetchRoomData();

        // Room Management header
        Label headerText = new Label("Room Management");
        headerText.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        headerText.setTextFill(Color.web("#6A1B9A"));
        headerText.setPadding(new Insets(10));
        headerText.setAlignment(Pos.CENTER);
        headerText.setBackground(new Background(new BackgroundFill(Color.web("#E1BEE7"), new CornerRadii(20), Insets.EMPTY)));
        headerText.setMaxWidth(Double.MAX_VALUE);

        // Back Button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            try {
                Wardendashboard dashboard = new Wardendashboard();
                Scene dashboardScene = dashboard.getScene(primaryStage);
                primaryStage.setScene(dashboardScene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }); 
        backButton.setStyle("-fx-background-color: #E5D4FA; -fx-text-fill: #6A1B9A; -fx-font-weight: bold; -fx-background-radius: 15; -fx-padding: 5 15;");
        HBox backBox = new HBox(backButton);
        backBox.setPadding(new Insets(10, 0, 0, 10));

        Button addStudentBtn = new Button("Add Student");
        addStudentBtn.setStyle(btnStyle());
        addStudentBtn.setOnAction(e -> showStudentDialog());

        HBox topButtons = new HBox(addStudentBtn);
        topButtons.setAlignment(Pos.CENTER);
        topButtons.setPadding(new Insets(10));

        setupTable();

        VBox root = new VBox(10, headerText, topButtons, table, backBox);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #f7f7fc;");

        Scene scene = new Scene(root, 1560, 790);
        primaryStage.setTitle("Room Management");
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }

    private void setupTable() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<StudentRow, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<StudentRow, String> numberCol = new TableColumn<>("Number");
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<StudentRow, String> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));

        TableColumn<StudentRow, String> feesCol = new TableColumn<>("Fees");
        feesCol.setCellValueFactory(new PropertyValueFactory<>("fees"));
        feesCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setGraphic(null);
                    return;
                }
                Label label = new Label(status);
                label.setTextFill(Color.WHITE);
                label.setPadding(new Insets(3, 10, 3, 10));
                label.setStyle("-fx-background-radius: 10; -fx-font-weight: bold;");
                switch (status.toLowerCase()) {
                    case "paid" -> label.setStyle(label.getStyle() + "-fx-background-color: green;");
                    case "pending" -> label.setStyle(label.getStyle() + "-fx-background-color: #FFA500;");
                    case "overdue" -> label.setStyle(label.getStyle() + "-fx-background-color: red;");
                }
                setGraphic(label);
                setText(null);
                setAlignment(Pos.CENTER);
            }
        });

        TableColumn<StudentRow, String> approvalCol = new TableColumn<>("Approval");
        approvalCol.setCellValueFactory(new PropertyValueFactory<>("fees"));
        approvalCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String fees, boolean empty) {
                super.updateItem(fees, empty);
                if (empty || fees == null) {
                    setGraphic(null);
                    return;
                }
                Circle circle = new Circle(8);
                switch (fees.toLowerCase()) {
                    case "paid" -> circle.setFill(Color.web("#9B59B6"));
                    case "pending" -> circle.setFill(Color.ORANGE);
                    default -> {
                        circle.setFill(Color.TRANSPARENT);
                        circle.setStroke(Color.web("#9B59B6"));
                    }
                }
                setGraphic(circle);
                setAlignment(Pos.CENTER);
            }
        });

        table.getColumns().addAll(nameCol, numberCol, roomCol, feesCol, approvalCol);
    }

    private void fetchRoomData() {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference roomRef = db.collection("room");

        ApiFuture<QuerySnapshot> future = roomRef.get();

        new Thread(() -> {
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                for (QueryDocumentSnapshot doc : documents) {
                    Map<String, Object> data = doc.getData();
                    StudentRow row = new StudentRow(
                            (String) data.getOrDefault("fullName", ""),
                            (String) data.getOrDefault("phone", ""),
                            (String) data.getOrDefault("room", ""),
                            (String) data.getOrDefault("Fees", "")
                    );
                    Platform.runLater(() -> table.getItems().add(row));
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private String btnStyle() {
        return "-fx-background-color: #E5D4FA; -fx-border-color: #9B59B6; " +
                "-fx-border-radius: 20; -fx-background-radius: 20; -fx-text-fill: #6A1B9A; " +
                "-fx-font-weight: bold; -fx-padding: 10 20 10 20;";
    }

    private void showStudentDialog() {
        Dialog<StudentRow> dialog = new Dialog<>();
        dialog.setTitle("Add Student");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField nameField = new TextField();
        TextField numberField = new TextField();
        TextField roomField = new TextField();
        ComboBox<String> feesBox = new ComboBox<>();
        feesBox.getItems().addAll("Paid", "Pending", "Overdue");
        feesBox.setValue("Pending");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Number:"), 0, 1);
        grid.add(numberField, 1, 1);
        grid.add(new Label("Room:"), 0, 2);
        grid.add(roomField, 1, 2);
        grid.add(new Label("Fees:"), 0, 3);
        grid.add(feesBox, 1, 3);

        dialog.getDialogPane().setContent(grid);
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return new StudentRow(nameField.getText(), numberField.getText(), roomField.getText(), feesBox.getValue());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(student -> table.getItems().add(student));
    }

    public static class StudentRow {
        private final String name, number, room, fees;

        public StudentRow(String name, String number, String room, String fees) {
            this.name = name;
            this.number = number;
            this.room = room;
            this.fees = fees;
        }

        public String getName() {
            return name;
        }

        public String getNumber() {
            return number;
        }

        public String getRoom() {
            return room;
        }

        public String getFees() {
            return fees;
        }

        @Override
        public String toString() {
            return name + " | " + number + " | " + room + " | " + fees;
        }
    }

    
}
