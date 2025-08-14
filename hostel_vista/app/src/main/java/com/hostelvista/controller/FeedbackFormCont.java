// package com.hostelvista.controller;

// import com.hostelvista.Authentication.FirebaseInitializer;
// //import com.hostelvista.FirebaseInitializer;
// import com.hostelvista.view.FeedbackFormView;
// import com.hostelvista.view.Studentdashboard;
// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.DocumentReference;
// import com.google.cloud.firestore.Firestore;

// import javafx.stage.Stage;
// import javafx.scene.Scene;

// import java.util.HashMap;
// import java.util.Map;

// public class FeedbackFormCont {

//     private FeedbackFormView view;
//     private Stage stage;

//     public FeedbackFormCont(Stage stage) {
//         this.stage = stage;
//         FirebaseInitializer.initialize();
//         view = new FeedbackFormView();

//         view.getSubmitButton().setOnAction(e -> handleSubmit());
//         view.getBackButton().setOnAction(e -> handleBack());

//         Scene scene = view.getScene();
//         stage.setScene(scene);
//         stage.setTitle("Feedback Form");
//     }

//   private void handleSubmit() {
//     Map<String, Object> feedbackData = new HashMap<>();
//     feedbackData.putAll(view.getRatings());
//     feedbackData.put("remarks", view.getRemarks());

//     Firestore db = FirebaseInitializer.getDB();

//     Thread submissionThread = new Thread(() -> {
//         try {
//             // ✅ Correct way to count documents
//             int currentCount = db.collection("feedback")
//                                  .get()
//                                  .get()
//                                  .getDocuments()
//                                  .size();

//             String documentName = "student " + (currentCount + 1);

//             db.collection("feedback").document(documentName).set(feedbackData).get();  // Wait for completion

//             System.out.println("✅ Feedback saved under document: " + documentName);

//         } catch (Exception ex) {
//             System.err.println("❌ Error in submitting feedback:");
//             ex.printStackTrace();
//         }
//     });

//     submissionThread.setDaemon(true);  // Optional: allows JVM to exit even if this thread is running
//     submissionThread.start();
// }


//     private void handleBack() {
//         Studentdashboard dashboard = new Studentdashboard(stage);
//         Scene dashboardScene = dashboard.getScene();
//         stage.setScene(dashboardScene);
//         stage.setTitle("Student Dashboard");
//     }
// }
package com.hostelvista.controller;

import com.hostelvista.Authentication.FirebaseInitializer;
import com.hostelvista.view.FeedbackFormView;
import com.hostelvista.view.Studentdashboard;
import com.google.cloud.firestore.Firestore;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class FeedbackFormCont {

    private FeedbackFormView view;
    private Stage stage;

    public FeedbackFormCont(Stage stage) {
        this.stage = stage;
        FirebaseInitializer.initialize();
        view = new FeedbackFormView();

        view.getSubmitButton().setOnAction(e -> handleSubmit());
        view.getBackButton().setOnAction(e -> handleBack());

        Scene scene = view.getScene();
        stage.setScene(scene);
        stage.setTitle("Feedback Form");
    }

    private void handleSubmit() {
        Map<String, Object> feedbackData = new HashMap<>();
        feedbackData.putAll(view.getRatings());
        feedbackData.put("remarks", view.getRemarks());

        Firestore db = FirebaseInitializer.getDB();

        Thread submissionThread = new Thread(() -> {
            try {
                // ✅ Count current documents
                int currentCount = db.collection("feedback")
                                     .get()
                                     .get()
                                     .getDocuments()
                                     .size();

                String documentName = "student " + (currentCount + 1);

                db.collection("feedback").document(documentName).set(feedbackData).get(); // Wait for completion

                System.out.println("✅ Feedback saved under document: " + documentName);

                // ✅ Show popup on JavaFX Application Thread
                Platform.runLater(() -> {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Feedback Submitted");
                    alert.setHeaderText(null);
                    alert.setContentText("Thank you! Your feedback has been submitted successfully.");
                    alert.showAndWait();

                    // Optional: Navigate back to dashboard after submission
                    handleBack();
                });

            } catch (Exception ex) {
                System.err.println("❌ Error in submitting feedback:");
                ex.printStackTrace();

                // ✅ Show error popup
                Platform.runLater(() -> {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Submission Failed");
                    alert.setHeaderText("Could not submit feedback");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });
            }
        });

        submissionThread.setDaemon(true);
        submissionThread.start();
    }

    private void handleBack() {
        Studentdashboard dashboard = new Studentdashboard(stage);
        Scene dashboardScene = dashboard.getScene();
        stage.setScene(dashboardScene);
        stage.setTitle("Student Dashboard");
    }
}
