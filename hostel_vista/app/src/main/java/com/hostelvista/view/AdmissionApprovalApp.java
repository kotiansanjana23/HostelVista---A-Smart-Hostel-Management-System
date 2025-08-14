package com.hostelvista.view;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.hostelvista.Authentication.FirebaseInitializer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class AdmissionApprovalApp  {

    private final TableView<AdmissionEntry> table = new TableView<>();
    private final ObservableList<AdmissionEntry> admissionList = FXCollections.observableArrayList();


    public Scene getScene(Stage primaryStage){
        FirebaseInitializer.initialize();
        fetchAdmissions();

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #F3E5F5;");

        Label header = new Label("Admission Approval");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        header.setTextFill(Color.web("#6A1B9A"));
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER);
        header.setBackground(new Background(new BackgroundFill(Color.web("#E1BEE7"), new CornerRadii(20), Insets.EMPTY)));
        header.setMaxWidth(Double.MAX_VALUE);

        StackPane headerBox = new StackPane();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setStyle("-fx-background-color: #E1BEE7; -fx-background-radius: 15;");
        headerBox.setPadding(new Insets(20, 40, 20, 40));
        headerBox.getChildren().add(header);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        table.setStyle("-fx-border-color: #BA68C8; -fx-background-color: white;");

        setupTableColumns();
        table.setItems(admissionList);

        // Increase row height
        table.setRowFactory(tv -> {
            TableRow<AdmissionEntry> row = new TableRow<>();
            row.setPrefHeight(40); // Increased height
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    AdmissionEntry entry = row.getItem();
                    showDetailPage(entry);
                }
            });
            return row;
        });

        // Back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14px;-fx-background-color: #9090beff;-fx-background-radius: 25px;");
        backButton.setOnAction(e -> {
            try {
                Wardendashboard dashboard = new Wardendashboard();
                Scene dashboardScene = dashboard.getScene(primaryStage);
                primaryStage.setScene(dashboardScene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }); 
        
        HBox buttonBox = new HBox(backButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));

        root.getChildren().addAll(headerBox, table, buttonBox);

        Scene scene = new Scene(root, 1560, 790);
        // stage.setScene(scene);
        // stage.setTitle("Admission Approval");
        // stage.show();
        return scene;
    }

    private void setupTableColumns() {
        table.getColumns().addAll(
                createColumn("Name", "name"),
                createColumn("Number", "number"),
                createColumn("Year", "year"),
                createColumn("Room", "room"),
                createColumn("Course", "course"),
                createColumn("DOA", "doa"),
                createColumn("Amount", "amount"),
                createColumn("Fees", "fees"),
                createColumn("Approval", "approvalStatus")
        );
    }

    private TableColumn<AdmissionEntry, String> createColumn(String title, String property) {
        TableColumn<AdmissionEntry, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }

    private void fetchAdmissions() {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference admissionsRef = db.collection("admissions");

        ApiFuture<QuerySnapshot> future = admissionsRef.get();

        new Thread(() -> {
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                for (QueryDocumentSnapshot doc : documents) {
                    Map<String, Object> data = doc.getData();
                    AdmissionEntry entry = new AdmissionEntry(
                            doc.getId(),
                            (String) data.getOrDefault("fullName", ""),
                            (String) data.getOrDefault("phone", ""),
                            (String) data.getOrDefault("admissionYear", "2025"),
                            (String) data.getOrDefault("room", "Not Assigned"),
                            (String) data.getOrDefault("program", "N/A"),
                            (String) data.getOrDefault("admissionDate", ""),
                            "Paid", // Always show Paid
                            (String) data.getOrDefault("approvalStatus", "")
                    );
                    Platform.runLater(() -> admissionList.add(entry));
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void showDetailPage(AdmissionEntry entry) {
        Stage stage = new Stage();
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("Student Admission Details");
        title.setFont(Font.font("Arial", 22));
        title.setStyle("-fx-font-weight: bold;");

        root.getChildren().addAll(
                title,
                new Label("Name: " + entry.getName()),
                new Label("Number: " + entry.getNumber()),
                new Label("Year: " + entry.getYear()),
                new Label("Room: " + entry.getRoom()),
                new Label("Course: " + entry.getCourse()),
                new Label("DOA: " + entry.getDoa()),
                new Label("Amount: " + entry.getAmount()),
                new Label("Fees: " + entry.getFees()),
                new Label("Current Status: " + entry.getApprovalStatus())
        );

        HBox actionBox = new HBox(10);
        actionBox.setAlignment(Pos.CENTER_LEFT);
        Button approveBtn = new Button("Approve");
        Button rejectBtn = new Button("Reject");

        approveBtn.setOnAction(e -> {
            updateApprovalStatus(entry.getId(), "Approved", entry);
            stage.close();
        });

        rejectBtn.setOnAction(e -> {
            updateApprovalStatus(entry.getId(), "Rejected", entry);
            stage.close();
        });

        actionBox.getChildren().addAll(approveBtn, rejectBtn);
        root.getChildren().add(actionBox);

        Scene scene = new Scene(root, 1560, 790);
        stage.setScene(scene);
        stage.setTitle("Admission Details");
        stage.show();
    }

    private void updateApprovalStatus(String docId, String status, AdmissionEntry entry) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("admissions").document(docId);

        docRef.update("approvalStatus", status)
                .addListener(() -> {
                    Platform.runLater(() -> entry.setApprovalStatus(status));
                    table.refresh();
                }, Runnable::run);
    }

    public static class AdmissionEntry {
        private final String id, name, number, year, room, course, doa, amount, fees;
        private String approvalStatus;

        public AdmissionEntry(String id, String name, String number, String year,
                              String room, String course, String doa, String fees, String approvalStatus) {
            this.id = id;
            this.name = name;
            this.number = number;
            this.year = year;
            this.room = room;
            this.course = course;
            this.doa = doa;
            this.amount = "₹32,000";
            this.fees = fees;
            this.approvalStatus = approvalStatus;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getNumber() { return number; }
        public String getYear() { return year; }
        public String getRoom() { return room; }
        public String getCourse() { return course; }
        public String getDoa() { return doa; }
        public String getAmount() { return amount; }
        public String getFees() { return fees; }
        public String getApprovalStatus() { return approvalStatus; }
        public void setApprovalStatus(String status) { this.approvalStatus = status; }
    }

    
}
