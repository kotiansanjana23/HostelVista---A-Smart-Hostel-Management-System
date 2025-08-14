package com.hostelvista.controller;

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

public class RoomBookingController {
    private Stage stage;
    private final String[] floors = { "Fourth Floor", "Third Floor", "Second Floor", "First Floor", "Ground Floor" };
    private final int roomsPerFloor = 17;

    public static String roomNo ;
    public RoomBookingController(Stage stage) {
        this.stage = stage;
    }

    public String[] getFloors() {
        return floors;
    }

    public int getRoomsPerFloor() {
        return roomsPerFloor;
    }

    public Button createRoomButton(int floorIndex, int roomNumber) {
        int roomNum = (4 - floorIndex) * 100 + roomNumber;
        String roomLabel = String.format("%03d", roomNum);
        Button roomBtn = new Button(roomLabel);
        roomBtn.setPrefSize(70, 70);
        roomBtn.setStyle("-fx-background-color: #D09CF0; -fx-font-size: 18; -fx-text-fill: white; -fx-background-radius: 15;");
        roomBtn.setUserData(new boolean[3]);

        roomBtn.setOnAction(e -> openRoomPopup(roomBtn));
        return roomBtn;
    }

    

    private void openRoomPopup(Button roomButton) {
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
