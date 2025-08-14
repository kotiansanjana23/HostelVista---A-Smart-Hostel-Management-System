// package com.hostelvista.controller;

// import com.google.cloud.firestore.*;
// import com.google.firebase.cloud.FirestoreClient;
// import com.hostelvista.view.NotificationView;
// import javafx.application.Platform;
// import javafx.stage.Stage;

// public class NotificationController {
//     private NotificationView view;
//     private Stage stage;

//     public NotificationController(Stage stage, String role, String userId) {
//         this.stage = stage;
//         this.view = new NotificationView();
//         listenForNotifications(role, userId);
//         stage.setScene(view.getScene());
//     }

//     private void listenForNotifications(String role, String userId) {
//         Firestore db = FirestoreClient.getFirestore();

//         db.collection("notifications")
//             .whereEqualTo("recipientRole", role)
//             .whereEqualTo("recipientId", userId)
//             .orderBy("timestamp", Query.Direction.DESCENDING)
//             .addSnapshotListener((snapshots, e) -> {
//                 if (e != null || snapshots == null) return;

//                 view.getNotificationArea().clear();
//                 for (QueryDocumentSnapshot doc : snapshots) {
//                     String message = doc.getString("message");
//                     Platform.runLater(() -> {
//                         view.getNotificationArea().appendText("🔔 " + message + "\n\n");
//                     });
//                 }
//             });
//     }
// }

package com.hostelvista.controller;

import com.hostelvista.view.NotificationView;
import com.hostelvista.view.Studentdashboard;
import com.hostelvista.view.Studentdashboard;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NotificationController {
    private final Stage stage;
    private final String role;

    public NotificationController(Stage stage, String role) {
        this.stage = stage;
        this.role = role;

        // Show popup notification when the view is loaded
        showPopup();
    }

    // Method to return back to previous dashboard
    public void goBack() {
        if ("student".equalsIgnoreCase(role)) {
            Studentdashboard studentdashboardView = new Studentdashboard(stage);
            Scene dashboardScene = studentdashboardView.getScene();
            stage.setScene(dashboardScene);
        }
        // You can add more roles like 'warden' here if needed
    }

    // Popup logic
    private void showPopup() {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You have a new hostel notice!");
            alert.showAndWait();
        });
    }
}
