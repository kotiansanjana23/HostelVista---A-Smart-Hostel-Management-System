package com.hostelvista.view;

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

public class WardenFeesManage {

    private final TableView<FeeRow> table = new TableView<>();
    private final ObservableList<FeeRow> feeList = FXCollections.observableArrayList();

    public Scene getScene(Stage primaryStage) {
        FirebaseInitializer.initialize();

        // Title Box with Rounded Purple Background
        Label header = new Label("Fee Management");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        header.setTextFill(Color.web("#6A1B9A"));
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER);
        header.setBackground(new Background(new BackgroundFill(Color.web("#E1BEE7"), new CornerRadii(20), Insets.EMPTY)));
        header.setMaxWidth(Double.MAX_VALUE);

        StackPane headerBox = new StackPane(header);
        headerBox.setPadding(new Insets(20));
        headerBox.setStyle("-fx-background-color: #E1BEE7; -fx-background-radius: 20;");
        headerBox.setAlignment(Pos.CENTER);

        // Back Button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #8662b1ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");
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
        backBox.setPadding(new Insets(10, 0, 0, 10));

        // Table setup
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        table.setStyle("-fx-border-color: #9b59b6; -fx-border-width: 1;");
        setupTableColumns();
        table.setItems(feeList);

        VBox root = new VBox(20, headerBox, table, backBox);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f7f7fc;");
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 1560, 790);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fee Management");
        primaryStage.show();

        fetchFees();
        return scene;
    }

    private void setupTableColumns() {
        table.getColumns().addAll(
                createColumn("Name", "name"),
                createColumn("Number", "number"),
                createColumn("Academic Year", "year"),
                createColumn("Room", "room"),
                createColumn("Course", "course"),
                createColumn("Due-Date", "dueDate"),
                createColumn("Fees","fees"),
                createColumn("Amount", "amount")
        );
    }

    private TableColumn<FeeRow, String> createColumn(String title, String property) {
        TableColumn<FeeRow, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }

    // private TableColumn<FeeRow, String> createFeesColumn() {
    //     TableColumn<FeeRow, String> feesCol = new TableColumn<>("Fees");
    //     feesCol.setCellFactory(column -> new TableCell<>() {
    //         @Override
    //         protected void updateItem(String status, boolean empty) {
    //             super.updateItem(status, empty);
                // if (empty || status == null) {
                //     setGraphic(null);
                // } else {
                //     Label label = new Label(status);
                //     label.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

                //     if (status.equalsIgnoreCase("Paid")) {
                //         label.setStyle(label.getStyle() + "-fx-background-color: green;");
                //     } else if (status.equalsIgnoreCase("Pending")) {
                //         label.setStyle(label.getStyle() + "-fx-background-color: #008b8b;");
                //     } else if (status.equalsIgnoreCase("Overdue")) {
                //         label.setStyle(label.getStyle() + "-fx-background-color: red;");
                //     }

    //                 label.setPadding(new Insets(3, 10, 3, 10));
    //                 label.setStyle(label.getStyle() + " -fx-background-radius: 10;");
    //                 setGraphic(label);
    //                 setText(null);
    //                 setAlignment(Pos.CENTER);
    //             }
    //         }
    //     });
    //     feesCol.setCellValueFactory(new PropertyValueFactory<>("fees"));
    //     return feesCol;
    // }

    private void fetchFees() {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference feesRef = db.collection("fees");

        new Thread(() -> {
            try {
                List<QueryDocumentSnapshot> documents = feesRef.get().get().getDocuments();

                if (documents.isEmpty()) {
                    System.out.println("No documents found in 'fees' collection.");
                }

                for (QueryDocumentSnapshot doc : documents) {
                    Map<String, Object> data = doc.getData();

                    FeeRow fee = new FeeRow(
                            doc.getId(), // name
                            getField(data, "Number"),
                            getField(data, "Academic Year"),
                            getField(data, "Room"),
                            getField(data, "Course"),
                            getField(data, "Due-Date"),
                            getField(data, "Amount"),
                            getField(data, "Fees")
                    );

                    Platform.runLater(() -> feeList.add(fee));
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private String getField(Map<String, Object> data, String key) {
        Object value = data.get(key);
        return value != null ? value.toString() : "";
    }

    public static class FeeRow {
        private final String name, number, year, room, course, dueDate, amount, fees;

        public FeeRow(String name, String number, String year, String room, String course, String dueDate, String amount, String fees) {
            this.name = name;
            this.number = number;
            this.year = year;
            this.room = room;
            this.course = course;
            this.dueDate = dueDate;
            this.amount = amount;
            this.fees = fees;
        }

        public String getName() { return name; }
        public String getNumber() { return number; }
        public String getYear() { return year; }
        public String getRoom() { return room; }
        public String getCourse() { return course; }
        public String getDueDate() { return dueDate; }
        public String getAmount() { return amount; }
        public String getFees() { return fees; }
        
    }

   
}
