// package com.hostelvista.controller;

// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.*;
// import com.google.firebase.cloud.FirestoreClient;
// import com.hostelvista.Authentication.FirebaseInitializer;
// import com.hostelvista.view.DashboardmessView;
// import com.hostelvista.view.MessChangeView;
// import javafx.application.Platform;
// import javafx.stage.Stage;

// import java.util.HashMap;
// import java.util.Map;

// public class MessChangeController {
//     private Stage stage;
//     private MessChangeView view;

//     public MessChangeController(Stage stage) {
//         FirebaseInitializer.initialize(); // Initialize Firebase once
//         this.stage = stage;
//         this.view = new MessChangeView();
//         initialize();
//         stage.setScene(view.getScene());
//     }

//     private void initialize() {
//         view.backBtn.setOnAction(e -> {
//             DashboardmessView dashboard = new DashboardmessView(stage);
//             stage.setScene(dashboard.getScene());
//         });

//         view.submitBtn.setOnAction(e -> {
//             String name = view.nameField.getText();
//             String id = view.idField.getText();
//             String room = view.roomField.getText();
//             String currentMess = view.currentMessBox.getValue();
//             String newMess = view.newMessBox.getValue();
//             String reason = view.reasonArea.getText();

//             // Validation
//             if (name.isEmpty() || id.isEmpty() || room.isEmpty() || currentMess == null || newMess == null || reason.isEmpty()) {
//                 System.out.println("⚠️ Please fill in all fields!");
//                 return;
//             }

//             // Prepare data
//             Map<String, Object> request = new HashMap<>();
//             request.put("student_name", name);
//             request.put("student_id", id);
//             request.put("room_no", room);
//             request.put("current_mess", currentMess);
//             request.put("requested_mess", newMess);
//             request.put("reason", reason);
//             request.put("status", "Pending");
//             request.put("timestamp", System.currentTimeMillis());

//             // Push to Firebase Firestore with custom document ID
//             new Thread(() -> {
//                 try {
//                     Firestore db = FirestoreClient.getFirestore();

//                     // Get all existing documents to find the next available student number
//                     ApiFuture<QuerySnapshot> future = db.collection("mess_change").get();
//                     QuerySnapshot snapshot = future.get(); // blocking call

//                     int nextId = snapshot.size() + 1;
//                     String documentId = "student " + nextId;

//                     ApiFuture<WriteResult> result = db.collection("mess_change")
//                             .document(documentId)
//                             .set(request);

//                     // Wait for result and then print success
//                     result.get();

//                     Platform.runLater(() -> {
//                         System.out.println("✅ Mess change request saved with ID: " + documentId);
//                         // Optionally: clear fields or show confirmation popup here
//                     });

//                 } catch (Exception ex) {
//                     Platform.runLater(() -> {
//                         System.out.println("❌ Error saving to Firebase: " + ex.getMessage());
//                     });
//                     ex.printStackTrace();
//                 }
//             }).start(); // Run database logic in a background thread
//         });
//     }
// }
package com.hostelvista.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.hostelvista.Authentication.FirebaseInitializer;
import com.hostelvista.view.DashboardmessView;
import com.hostelvista.view.MessChangeView;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class MessChangeController {
    private Stage stage;
    private MessChangeView view;

    public MessChangeController(Stage stage) {
        FirebaseInitializer.initialize(); // Initialize Firebase once
        this.stage = stage;
        this.view = new MessChangeView();
        initialize();
        stage.setScene(view.getScene());
    }

    private void initialize() {
        view.backBtn.setOnAction(e -> {
            DashboardmessView dashboard = new DashboardmessView(stage);
            stage.setScene(dashboard.getScene());
        });

        view.submitBtn.setOnAction(e -> {
            String name = view.nameField.getText();
            String id = view.idField.getText();
            String room = view.roomField.getText();
            String currentMess = view.currentMessBox.getValue();
            String newMess = view.newMessBox.getValue();
            String reason = view.reasonArea.getText();

            // Validation
            if (name.isEmpty() || id.isEmpty() || room.isEmpty() || currentMess == null || newMess == null || reason.isEmpty()) {
                System.out.println("⚠️ Please fill in all fields!");
                return;
            }

            // Prepare data
            Map<String, Object> request = new HashMap<>();
            request.put("student_name", name);
            request.put("student_id", id);
            request.put("room_no", room);
            request.put("current_mess", currentMess);
            request.put("requested_mess", newMess);
            request.put("reason", reason);
            request.put("status", "Pending");
            request.put("timestamp", System.currentTimeMillis());

            // Push to Firebase Firestore with custom document ID
            new Thread(() -> {
                try {
                    Firestore db = FirestoreClient.getFirestore();

                    // Get all existing documents to find the next available student number
                    ApiFuture<QuerySnapshot> future = db.collection("mess_change").get();
                    QuerySnapshot snapshot = future.get(); // blocking call

                    int nextId = snapshot.size() + 1;
                    String documentId = "student " + nextId;

                    ApiFuture<WriteResult> result = db.collection("mess_change")
                            .document(documentId)
                            .set(request);

                    // Wait for result and then print success
                    result.get();

                    Platform.runLater(() -> {
                        System.out.println("✅ Mess change request saved with ID: " + documentId);
                        showAlert("Success", "Your mess change request has been submitted!");
                    });

                } catch (Exception ex) {
                    Platform.runLater(() -> {
                        System.out.println("❌ Error saving to Firebase: " + ex.getMessage());
                        showAlert("Error", "Failed to submit your mess change request.");
                    });
                    ex.printStackTrace();
                }
            }).start(); // Run database logic in a background thread
        });
    }

    // Reusable popup alert method
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
