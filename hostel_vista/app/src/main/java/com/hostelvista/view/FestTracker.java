package com.hostelvista.view;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.hostelvista.Authentication.FirebaseInitializer;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FestTracker {

    public static class FestEntry {
        private final String id;
        private final SimpleStringProperty fullName;
        private final SimpleStringProperty festName;
        private final SimpleStringProperty participationType;
        private final SimpleStringProperty year;
        private final SimpleStringProperty department;
        private final SimpleStringProperty collegeId;
        private final SimpleStringProperty contact;
        private final SimpleBooleanProperty approved;

        public FestEntry(String id, String fullName, String festName, String participationType,
                         String year, String department, String collegeId, String contact, boolean approved) {
            this.id = id;
            this.fullName = new SimpleStringProperty(fullName);
            this.festName = new SimpleStringProperty(festName);
            this.participationType = new SimpleStringProperty(participationType);
            this.year = new SimpleStringProperty(year);
            this.department = new SimpleStringProperty(department);
            this.collegeId = new SimpleStringProperty(collegeId);
            this.contact = new SimpleStringProperty(contact);
            this.approved = new SimpleBooleanProperty(approved);
        }

        public String getId() { return id; }
        public String getFullName() { return fullName.get(); }
        public String getFestName() { return festName.get(); }
        public String getParticipationType() { return participationType.get(); }
        public String getYear() { return year.get(); }
        public String getDepartment() { return department.get(); }
        public String getCollegeId() { return collegeId.get(); }
        public String getContact() { return contact.get(); }
        public boolean isApproved() { return approved.get(); }
        public SimpleBooleanProperty approvedProperty() { return approved; }
    }

    private final TableView<FestEntry> table = new TableView<>();
    private final ObservableList<FestEntry> festList = FXCollections.observableArrayList();

    public Scene getScene(Stage primaryStage) {
        FirebaseInitializer.initialize();
        fetchFestRequests();

        Label title = new Label("Fest Tracker");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setTextFill(Color.PURPLE);
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        title.setBackground(new Background(new BackgroundFill(Color.web("#E1BEE7"), new CornerRadii(30), Insets.EMPTY)));
        title.setPadding(new Insets(20));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        table.setStyle("-fx-background-color: #E6E6FA;");
        table.setItems(festList);

        table.getColumns().addAll(
                createColumn("Full Name", FestEntry::getFullName),
                createColumn("Fest Name", FestEntry::getFestName),
                createColumn("Participation Type", FestEntry::getParticipationType),
                createColumn("Year", FestEntry::getYear),
                createColumn("Department", FestEntry::getDepartment),
                createColumn("College ID", FestEntry::getCollegeId),
                createColumn("Contact", FestEntry::getContact),
                createStatusColumn()
        );

        ScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background: transparent;");

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

        VBox root = new VBox(20, title, scrollPane, buttonBox);
        root.setPadding(new Insets(30));

        return new Scene(root, 1560, 800);
    }

    private TableColumn<FestEntry, String> createColumn(String title, java.util.function.Function<FestEntry, String> extractor) {
        TableColumn<FestEntry, String> col = new TableColumn<>(title);
        col.setCellValueFactory(data -> new ReadOnlyStringWrapper(extractor.apply(data.getValue())));
        return col;
    }

    private TableColumn<FestEntry, Boolean> createStatusColumn() {
        TableColumn<FestEntry, Boolean> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> data.getValue().approvedProperty());

        statusCol.setCellFactory(col -> new TableCell<>() {
            private final Button acceptButton = new Button("Accept");

            {
                acceptButton.setStyle("-fx-background-color: #7a5fd1; -fx-text-fill: white; -fx-background-radius: 15px;");
                acceptButton.setOnAction(event -> {
                    FestEntry entry = getTableView().getItems().get(getIndex());
                    updateApprovalStatus(entry.getId());
                });
            }

            @Override
            protected void updateItem(Boolean approved, boolean empty) {
                super.updateItem(approved, empty);
                if (empty || approved == null) {
                    setGraphic(null);
                } else if (approved) {
                    Label approvedLabel = new Label("Approved");
                    approvedLabel.setTextFill(Color.web("#4CAF50"));
                    setGraphic(approvedLabel);
                } else {
                    setGraphic(acceptButton);
                }
            }
        });

        return statusCol;
    }

    private void updateApprovalStatus(String docId) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("fest_forms").document(docId);

        docRef.update("approved", true).addListener(() -> {
            Platform.runLater(() -> {
                festList.clear();
                fetchFestRequests();
            });
        }, Runnable::run);
    }

    /** Fetch all fest forms into the table **/
    private void fetchFestRequests() {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference festRef = db.collection("fest_forms");

        ApiFuture<QuerySnapshot> future = festRef.get();
        new Thread(() -> {
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                for (QueryDocumentSnapshot doc : documents) {
                    Map<String, Object> data = doc.getData();
                    FestEntry entry = new FestEntry(
                            doc.getId(),
                            (String) data.getOrDefault("fullName", ""),
                            (String) data.getOrDefault("festName", ""),
                            (String) data.getOrDefault("participationType", ""),
                            (String) data.getOrDefault("year", ""),
                            (String) data.getOrDefault("department", ""),
                            (String) data.getOrDefault("collegeId", ""),
                            (String) data.getOrDefault("contact", ""),
                            Boolean.TRUE.equals(data.get("approved"))
                    );
                    Platform.runLater(() -> festList.add(entry));
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // /** Fetch a single fest form and fill a FestFormView **/
    // public void loadFestFormData(FestFormView view, String collegeId) {
    //     Firestore db = FirestoreClient.getFirestore();

    //     new Thread(() -> {
    //         try {
    //             List<QueryDocumentSnapshot> docs = db.collection("fest_forms")
    //                     .whereEqualTo("collegeId", collegeId)
    //                     .get()
    //                     .get()
    //                     .getDocuments();

    //             if (!docs.isEmpty()) {
    //                 Map<String, Object> data = docs.get(0).getData();

    //                 Platform.runLater(() -> {
    //                     view.getNameField().setText((String) data.getOrDefault("fullName", ""));
    //                     view.getCollegeId().setText((String) data.getOrDefault("collegeId", ""));
    //                     view.getDepartment().setValue((String) data.getOrDefault("department", ""));
    //                     view.getYear().setValue((String) data.getOrDefault("year", ""));
    //                     view.getFestName().setValue((String) data.getOrDefault("festName", ""));
    //                     view.getParticipationType().setValue((String) data.getOrDefault("participationType", ""));
    //                     view.getContact().setText((String) data.getOrDefault("contact", ""));
    //                     view.getEmail().setText((String) data.getOrDefault("email", ""));
    //                     view.getAgreeCheck().setSelected(Boolean.TRUE.equals(data.get("agreedToRules")));
    //                 });
    //             }
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     }).start();
    // }
}
