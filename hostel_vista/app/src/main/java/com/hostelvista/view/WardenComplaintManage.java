package com.hostelvista.view;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.hostelvista.Authentication.FirebaseInitializer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.checkerframework.checker.units.qual.t;

public class WardenComplaintManage extends Application {

    private final TableView<ComplaintRow> table = new TableView<>();
    private final ObservableList<ComplaintRow> complaintList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        Scene scene = getScene(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Complaint Management");
        primaryStage.show();
    }

    private void setupTableColumns() {
        TableColumn<ComplaintRow, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<ComplaintRow, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<ComplaintRow, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setCellFactory(column -> new TableCell<>() {
            private final Button acceptButton = new Button("Accept");

            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setGraphic(null);
                    return;
                }

                ComplaintRow complaint = getTableView().getItems().get(getIndex());

                if (status.equalsIgnoreCase("accept")) {
                    Label label = new Label("We will improve");
                    label.setTextFill(Color.web("#9c20ccff"));
                    label.setStyle("-fx-font-weight: bold; -fx-background-radius: 10; -fx-background-color: #f3e7fa;");
                    label.setPadding(new Insets(5, 12, 5, 12));
                    setGraphic(label);
                } else {
                    acceptButton.setStyle("-fx-background-color: #9c20ccff; -fx-text-fill: white; -fx-background-radius: 10;");
                    acceptButton.setOnAction(e -> {
                        updateStatusInFirestore(complaint.getDocumentId(), "accept");
                        complaint.setStatus("accept");
                        getTableView().refresh();
                    });
                    setGraphic(acceptButton);
                }

                setAlignment(Pos.CENTER);
            }
        });

        table.getColumns().clear();
        table.getColumns().addAll(typeCol, descCol, statusCol);
    }

    private void fetchComplaints() {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference complaintsRef = db.collection("complaints");

        ApiFuture<QuerySnapshot> future = complaintsRef.get();

        new Thread(() -> {
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                for (QueryDocumentSnapshot doc : documents) {
                    Map<String, Object> data = doc.getData();
                    ComplaintRow entry = new ComplaintRow(
                            doc.getId(),
                            (String) data.getOrDefault("type", ""),
                            (String) data.getOrDefault("description", ""),
                            (String) data.getOrDefault("status", "")
                    );
                    Platform.runLater(() -> complaintList.add(entry));
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void updateStatusInFirestore(String docId, String newStatus) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("complaints").document(docId);
        docRef.update("status", newStatus);
    }

    public static class ComplaintRow {
        private final String documentId;
        private final String type;
        private final String description;
        private String status;

        public ComplaintRow(String documentId, String type, String description, String status) {
            this.documentId = documentId;
            this.type = type;
            this.description = description;
            this.status = status;
        }

        public String getDocumentId() { return documentId; }
        public String getType() { return type; }
        public String getDescription() { return description; }
        public String getStatus() { return status; }
        public void setStatus(String newStatus) { this.status = newStatus; }
    }

    public Scene getScene(Stage primaryStage) {
        FirebaseInitializer.initialize();
        fetchComplaints();

        Label title = new Label("Complaint Management");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setTextFill(Color.web("#6A1B9A"));
        title.setPadding(new Insets(10));
        title.setAlignment(Pos.CENTER);
        title.setBackground(new Background(new BackgroundFill(Color.web("#E1BEE7"), new CornerRadii(20), Insets.EMPTY)));
        title.setMaxWidth(Double.MAX_VALUE);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        table.setStyle("-fx-border-color: #d2a8f5; -fx-border-radius: 10;");
        setupTableColumns();
        table.setItems(complaintList);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14px; -fx-background-color: #9090beff; -fx-background-radius: 25px;");
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
        //backBox.setAlignment(Pos.CENTER);
        backBox.setPadding(new Insets(10));

        VBox root = new VBox(20, title, table, backBox);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f7f7fc;");

        return new Scene(root, 1560, 790);
    }
}
