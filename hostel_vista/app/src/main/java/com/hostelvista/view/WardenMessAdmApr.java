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

public class WardenMessAdmApr {

    private final TableView<AdmissionEntry> table = new TableView<>();
    private final ObservableList<AdmissionEntry> messList = FXCollections.observableArrayList();

    public Scene getScene(Stage primaryStage) {
        FirebaseInitializer.initialize();
        fetchMessAdmissions();

        VBox root = new VBox(30);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: white;");

        Label headerLabel = new Label("Mess Change Application");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        headerLabel.setTextFill(Color.web("#6A1B9A"));
        headerLabel.setPadding(new Insets(10));
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setBackground(new Background(new BackgroundFill(Color.web("#E1BEE7"), new CornerRadii(20), Insets.EMPTY)));
        headerLabel.setMaxWidth(Double.MAX_VALUE);

        StackPane headerBox = new StackPane(headerLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(15));
        headerBox.setStyle("-fx-background-color: #E1BEE7; -fx-background-radius: 15;");

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        table.setStyle("-fx-border-color: #A020F0;");

        setupTableColumns();
        table.setItems(messList);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #d1a9f9; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20;");
        backButton.setPadding(new Insets(10, 20, 10, 20));
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
        root.getChildren().addAll(headerBox, table, backBox);

        Scene scene = new Scene(root, 1560, 790);
        primaryStage.setTitle("Mess Change Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }

    private void setupTableColumns() {
        TableColumn<AdmissionEntry, String> nameCol = new TableColumn<>("Student Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));

        TableColumn<AdmissionEntry, String> idCol = new TableColumn<>("Student ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        TableColumn<AdmissionEntry, String> roomCol = new TableColumn<>("Room No");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));

        TableColumn<AdmissionEntry, String> currentCol = new TableColumn<>("Current Mess");
        currentCol.setCellValueFactory(new PropertyValueFactory<>("currentMess"));

        TableColumn<AdmissionEntry, String> requestedCol = new TableColumn<>("Requested Mess");
        requestedCol.setCellValueFactory(new PropertyValueFactory<>("requestedMess"));

        TableColumn<AdmissionEntry, String> reasonCol = new TableColumn<>("Reason");
        reasonCol.setCellValueFactory(new PropertyValueFactory<>("reason"));

        TableColumn<AdmissionEntry, String> resultCol = new TableColumn<>("Approval Result");
        resultCol.setCellValueFactory(new PropertyValueFactory<>("approvalResult"));

        TableColumn<AdmissionEntry, Void> actionCol = new TableColumn<>("Action");
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button approveBtn = new Button("Approve");
            private final Button rejectBtn = new Button("Reject");
            private final HBox hbox = new HBox(10);

            {
                approveBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                rejectBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                hbox.setAlignment(Pos.CENTER);
                hbox.getChildren().addAll(approveBtn, rejectBtn);

                approveBtn.setOnAction(e -> handleAction("Approved"));
                rejectBtn.setOnAction(e -> handleAction("Rejected"));
            }

            private void handleAction(String decision) {
                AdmissionEntry entry = getTableView().getItems().get(getIndex());
                String docId = entry.getDocId();
                Firestore db = FirestoreClient.getFirestore();

                db.collection("mess_change").document(docId).update("approvalResult", decision)
                        .addListener(() -> Platform.runLater(() -> {
                            entry.setApprovalResult(decision);
                            table.refresh();
                        }), Runnable::run);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    AdmissionEntry entry = getTableView().getItems().get(getIndex());
                    if (entry.getApprovalResult() == null || entry.getApprovalResult().isEmpty()) {
                        setGraphic(hbox);
                    } else {
                        Label result = new Label(entry.getApprovalResult().equals("Approved") ? "✅ Approved" : "❌ Rejected");
                        result.setFont(Font.font(14));
                        result.setTextFill(Color.BLACK);
                        setGraphic(result);
                    }
                }
            }
        });

        table.getColumns().addAll(nameCol, idCol, roomCol, currentCol, requestedCol, reasonCol, resultCol, actionCol);
    }

    private void fetchMessAdmissions() {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference messRef = db.collection("mess_change");

        ApiFuture<QuerySnapshot> future = messRef.get();

        new Thread(() -> {
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                for (QueryDocumentSnapshot doc : documents) {
                    Map<String, Object> data = doc.getData();
                    AdmissionEntry entry = new AdmissionEntry(
                            doc.getId(),
                            (String) data.getOrDefault("student_name", ""),
                            (String) data.getOrDefault("student_id", ""),
                            (String) data.getOrDefault("room_no", ""),
                            (String) data.getOrDefault("current_mess", ""),
                            (String) data.getOrDefault("requested_mess", ""),
                            (String) data.getOrDefault("reason", ""),
                            (String) data.getOrDefault("approvalResult", "")
                    );
                    Platform.runLater(() -> messList.add(entry));
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static class AdmissionEntry {
        private final String docId;
        private final String studentName, studentId, room, currentMess, requestedMess, reason;
        private String approvalResult;

        public AdmissionEntry(String docId, String studentName, String studentId, String room, String currentMess, String requestedMess, String reason, String approvalResult) {
            this.docId = docId;
            this.studentName = studentName;
            this.studentId = studentId;
            this.room = room;
            this.currentMess = currentMess;
            this.requestedMess = requestedMess;
            this.reason = reason;
            this.approvalResult = approvalResult;
        }

        public String getDocId() { return docId; }
        public String getStudentName() { return studentName; }
        public String getStudentId() { return studentId; }
        public String getRoom() { return room; }
        public String getCurrentMess() { return currentMess; }
        public String getRequestedMess() { return requestedMess; }
        public String getReason() { return reason; }
        public String getApprovalResult() { return approvalResult; }
        public void setApprovalResult(String result) { this.approvalResult = result; }
    }

    
}
