package com.hostelvista.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.hostelvista.Authentication.FirebaseInitializer;
import com.hostelvista.view.StudentAdmission;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomBookingController {
    private Stage stage;
    private final String[] floors = {"Fourth Floor", "Third Floor", "Second Floor", "First Floor", "Ground Floor"};
    private final int roomsPerFloor = 17;

    // Firebase target values
    private static final String STUDENT_EMAIL = "hostelvista4@gmail.com";
    private static final String STUDENT_ROLE = "Student";
    public static String roomNo;

    public RoomBookingController(Stage stage) {
        this.stage = stage;
    }

    public String[] getFloors() {
        return floors;
    }

    public int getRoomsPerFloor() {
        return roomsPerFloor;
    }

    String tempRomNum;
    public Button createRoomButton(int floorIndex, int roomNumber) {
        int roomNum = (4 - floorIndex) * 100 + roomNumber;
         tempRomNum = 407+"";
        String roomLabel = String.format("%03d", roomNum);
        Button roomBtn = new Button(roomLabel);
        roomBtn.setPrefSize(70, 70);
        roomBtn.setStyle("-fx-background-color: #D09CF0; -fx-font-size: 18; -fx-text-fill: white; -fx-background-radius: 15;");
        roomBtn.setUserData(new boolean[3]);

        roomBtn.setOnAction(e -> openRoomPopup(roomBtn));
        return roomBtn;
    }

    private void openRoomPopup(Button roomButton) {
         roomNo  = roomButton.getText().trim();
        Stage popup = new Stage();
        popup.setTitle("Room Booking");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Text rname = new Text("Room no : " + roomButton.getText());
        Label instruction = new Label("Enter name for each bed:");
        instruction.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
        rname.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        HBox bedBox = new HBox(15);
        bedBox.setAlignment(Pos.CENTER);

        TextField[] nameFields = new TextField[3];
        Button[] bookBtns = new Button[3];
        boolean[] booked = (boolean[]) roomButton.getUserData();

        for (int i = 0; i < 3; i++) {
            VBox bed = new VBox(10);
            bed.setAlignment(Pos.CENTER);
            TextField nameField = new TextField();
            nameField.setPromptText("Name " + (i + 1));
            nameField.setPrefWidth(120);
            Button bookBtn = new Button("Book");
            int index = i;

            if (booked[i]) {
                bookBtn.setDisable(true);
                nameField.setEditable(false);
                nameField.setText("Booked");
            }

            bookBtn.setOnAction(e -> {
                if (!nameField.getText().trim().isEmpty()) {
                    booked[index] = true;
                    bookBtn.setDisable(true);
                    nameField.setEditable(false);
                    updateRoomButtonState(booked, roomButton);

                    // ✅ Update allocated room in Firestore
                    updateAllocatedRoomInFirebase(tempRomNum);
                }
            });

            nameFields[i] = nameField;
            bookBtns[i] = bookBtn;

            bed.getChildren().addAll(nameField, bookBtn);
            bedBox.getChildren().add(bed);
        }

        layout.getChildren().addAll(rname, instruction, bedBox);
        popup.setScene(new Scene(layout, 450, 250));
        popup.show();
    }

    private void updateRoomButtonState(boolean[] booked, Button roomButton) {
        int count = 0;
        for (boolean b : booked) if (b) count++;

        if (count == 3) {
            roomButton.setText("✅");
            roomButton.setStyle("-fx-background-color: #A5D6A7; -fx-font-size: 24; -fx-background-radius: 15;");
        } else if (count > 0) {
            roomButton.setText("" + count);
            roomButton.setStyle("-fx-background-color: orange; -fx-font-size: 20; -fx-background-radius: 15; -fx-text-fill: white;");
        } else {
            roomButton.setStyle("-fx-background-color: #D09CF0; -fx-font-size: 18; -fx-background-radius: 15; -fx-text-fill: white;");
        }
    }

    private void updateAllocatedRoomInFirebase(String roomNumber) {
        try {
            FirebaseInitializer.initialize();
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference usersRef = db.collection("users");

            // Find the student with given email and role
            Query query = usersRef.whereEqualTo("email", STUDENT_EMAIL)
                                  .whereEqualTo("role", STUDENT_ROLE);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            if (!documents.isEmpty()) {
                String docId = documents.get(0).getId();

                Map<String, Object> updates = new HashMap<>();
                updates.put("allocatedRoom", roomNo);

                usersRef.document(docId).update(updates);
                System.out.println("✅ Allocated room updated in Firebase: " + roomNumber);
            } else {
                System.out.println("⚠ No matching student found in Firebase.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goBack() {
        try {
            StudentAdmission studentAdmission = new StudentAdmission(stage);
            Scene admissionScene = studentAdmission.getScene();
            stage.setScene(admissionScene);
            stage.setTitle("Student Admission");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
