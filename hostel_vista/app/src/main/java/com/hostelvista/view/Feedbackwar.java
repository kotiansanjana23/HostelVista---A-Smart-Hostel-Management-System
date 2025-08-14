package com.hostelvista.view;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.hostelvista.Authentication.FirebaseInitializer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Feedbackwar {

    public static class FeedbackEntry {
        private final String name;
        private final int warden;
        private final int cleaningStaff;
        private final int security;
        private final String remark;

        public FeedbackEntry(String name, int warden, int cleaningStaff, int security, String remark) {
            this.name = name;
            this.warden = warden;
            this.cleaningStaff = cleaningStaff;
            this.security = security;
            this.remark = remark;
        }

        public String getName() { return name; }
        public int getWarden() { return warden; }
        public int getCleaningStaff() { return cleaningStaff; }
        public int getSecurity() { return security; }
        public String getRemark() { return remark; }
    }

    private final TableView<FeedbackEntry> table = new TableView<>();
    private final ObservableList<FeedbackEntry> feedbackList = FXCollections.observableArrayList();

    public Scene getScene(Stage primaryStage) {
        FirebaseInitializer.initialize();
        fetchFeedbackData();

        // Title box
        Label title = new Label("Student Feedback");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setTextFill(Color.web("#6A1B9A"));
        title.setPadding(new Insets(20));
        title.setAlignment(Pos.CENTER);
        title.setBackground(new Background(new BackgroundFill(Color.web("#E1BEE7"), new CornerRadii(30), Insets.EMPTY)));
        title.setMaxWidth(Double.MAX_VALUE);

        // Table setup
        table.setItems(feedbackList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        table.setStyle("-fx-control-inner-background: white; -fx-background-color: transparent;");
        setupTableColumns();

        // Back Button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14px; -fx-background-color: #a080f0; -fx-text-fill: white; -fx-background-radius: 25px; -fx-padding: 8 20 8 20;");
        backButton.setOnAction(e -> {
            try {
                Wardendashboard dashboard = new Wardendashboard();
                Scene dashboardScene = dashboard.getScene(primaryStage);
                primaryStage.setScene(dashboardScene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox backBox = new HBox(backButton);
        backBox.setAlignment(Pos.CENTER_LEFT);
        backBox.setPadding(new Insets(10, 0, 0, 0));

        VBox root = new VBox(20, title, table, backBox);
        root.setPadding(new Insets(30));
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        return new Scene(root, 1560, 790);
    }

    private void setupTableColumns() {
        TableColumn<FeedbackEntry, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<FeedbackEntry, Integer> wardenCol = new TableColumn<>("Warden");
        wardenCol.setCellValueFactory(new PropertyValueFactory<>("warden"));
        wardenCol.setCellFactory(col -> getStarCell());

        TableColumn<FeedbackEntry, Integer> cleaningCol = new TableColumn<>("Cleaning Staff");
        cleaningCol.setCellValueFactory(new PropertyValueFactory<>("cleaningStaff"));
        cleaningCol.setCellFactory(col -> getStarCell());

        TableColumn<FeedbackEntry, Integer> securityCol = new TableColumn<>("Security");
        securityCol.setCellValueFactory(new PropertyValueFactory<>("security"));
        securityCol.setCellFactory(col -> getStarCell());

        TableColumn<FeedbackEntry, String> remarkCol = new TableColumn<>("Remark");
        remarkCol.setCellValueFactory(new PropertyValueFactory<>("remark"));

        table.getColumns().addAll(nameCol, wardenCol, cleaningCol, securityCol, remarkCol);
    }

    private TableCell<FeedbackEntry, Integer> getStarCell() {
        return new TableCell<>() {
            @Override
            protected void updateItem(Integer rating, boolean empty) {
                super.updateItem(rating, empty);
                setBackground(Background.EMPTY);
                if (empty || rating == null) {
                    setGraphic(null);
                } else {
                    HBox stars = new HBox(3);
                    for (int i = 1; i <= 5; i++) {
                        Label star = new Label(i <= rating ? "★" : "☆");
                        star.setStyle("-fx-font-size: 18px; -fx-text-fill: #8245C6;");
                        stars.getChildren().add(star);
                    }
                    setGraphic(stars);
                }
            }
        };
    }

    private void fetchFeedbackData() {
        Firestore db = FirebaseInitializer.getDB();
        CollectionReference feedbackRef = db.collection("feedback");

        ApiFuture<QuerySnapshot> future = feedbackRef.get();

        new Thread(() -> {
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                for (QueryDocumentSnapshot doc : documents) {
                    Map<String, Object> data = doc.getData();

                    // System.out.println("Document ID: " + name);
System.out.println("Warden: " + data.get("1. Warden"));
System.out.println("Cleaning Staff: " + data.get("2. Cleaning Staff"));
System.out.println("Security: " + data.get("3. Security"));
System.out.println("Remark: " + data.get("remarks"));


                    String name = doc.getId();
                    int warden = parseIntSafe(data.get("1. Warden"));
                    int cleaning = parseIntSafe(data.get("2. Cleaning Staff"));
                    int security = parseIntSafe(data.get("3. Security"));
                    String remark = data.get("remarks") != null ? data.get("remarks").toString() : "";

                    FeedbackEntry entry = new FeedbackEntry(name, warden, cleaning, security, remark);
                    Platform.runLater(() -> feedbackList.add(entry));
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private int parseIntSafe(Object value) {
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return 0;
            }
        }else if (value != null) {
        System.err.println("Unexpected value type for rating: " + value.getClass().getName());
    }
        return 0;
    }
}
