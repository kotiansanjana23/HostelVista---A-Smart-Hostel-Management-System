// package com.hostelvista.controller;

// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.DocumentReference;
// import com.google.cloud.firestore.Firestore;
// import com.hostelvista.Authentication.FirebaseInitializer;
// import com.hostelvista.view.FestFormView;
// import com.hostelvista.view.Studentdashboard;

// import javafx.application.Platform;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.stage.Stage;

// import java.util.HashMap;
// import java.util.Map;

// public class FestFormController {

//     private final FestFormView view;
//     private final Stage stage;

//     // Main constructor used when opening the Fest Form
//     public FestFormController(Stage stage) {
//         this.stage = stage;
//         FirebaseInitializer.initialize();

//         this.view = new FestFormView(stage);
//         initHandlers();

//         stage.setScene(view.getScene());
//         stage.setTitle("HostelVista - Fest Registration");
//     }

//     // Alternate constructor if you already have the view
//     public FestFormController(FestFormView view) {
//         this.stage = view.getStage();
//         this.view = view;
//         FirebaseInitializer.initialize();
//         initHandlers();
//     }

//     private void initHandlers() {
//         view.getSubmitBtn().setOnAction(e -> handleSubmit());
//         view.getBackButton().setOnAction(e -> goBack());
//     }

//     private void handleSubmit() {
//         if (!view.getAgreeCheck().isSelected()) {
//             showAlert("Terms Agreement", "You must agree to the terms before submitting.");
//             return;
//         }

//         Map<String, Object> data = new HashMap<>();
//         data.put("fullName", view.getNameField().getText());
//         data.put("collegeId", view.getCollegeId().getText());
//         data.put("department", view.getDepartment().getValue());
//         data.put("year", view.getYear().getValue());
//         data.put("festName", view.getFestName().getValue());
//         data.put("participationType", view.getParticipationType().getValue());
//         data.put("contact", view.getContact().getText());
//         data.put("email", view.getEmail().getText());
//         data.put("agreedToRules", view.getAgreeCheck().isSelected());

//         Firestore db = FirebaseInitializer.getDB();

//         new Thread(() -> {
//             try {
//                 ApiFuture<DocumentReference> future = db.collection("fest_forms").add(data);
//                 future.get(); // Wait for completion
//                 System.out.println("✅ Fest form submitted: " + data);

//                 Platform.runLater(() ->
//                         showAlert("Success", "Your fest registration has been submitted!")
//                 );

//             } catch (Exception e) {
//                 e.printStackTrace();
//                 Platform.runLater(() ->
//                         showAlert("Error", "Failed to submit the fest registration.")
//                 );
//             }
//         }).start();
//     }

//     private void showAlert(String title, String message) {
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.show();
//     }

//     private void goBack() {
//         Studentdashboard dashboard = new Studentdashboard(stage);
//         stage.setScene(dashboard.getScene());
//         stage.setTitle("Student Dashboard");
//     }
// }
package com.hostelvista.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.hostelvista.Authentication.FirebaseInitializer;
import com.hostelvista.view.FestFormView;
import com.hostelvista.view.Studentdashboard;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class FestFormController {

    private final FestFormView view;
    private final Stage stage;

    // Main constructor used when opening the Fest Form
    public FestFormController(Stage stage) {
        this.stage = stage;
        FirebaseInitializer.initialize();

        this.view = new FestFormView(stage);
        initHandlers();

        stage.setScene(view.getScene());
        stage.setTitle("HostelVista - Fest Registration");
    }

    // Alternate constructor if you already have the view
    public FestFormController(FestFormView view) {
        this.stage = view.getStage();
        this.view = view;
        FirebaseInitializer.initialize();
        initHandlers();
    }

    private void initHandlers() {
        view.getSubmitBtn().setOnAction(e -> handleSubmit());
        view.getBackButton().setOnAction(e -> goBack());
    }

    private void handleSubmit() {
        if (!view.getAgreeCheck().isSelected()) {
            showAlert("Terms Agreement", "You must agree to the terms before submitting.");
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("fullName", view.getNameField().getText());
        data.put("collegeId", view.getCollegeId().getText());
        data.put("department", view.getDepartment().getValue());
        data.put("year", view.getYear().getValue());
        data.put("festName", view.getFestName().getValue());
        data.put("participationType", view.getParticipationType().getValue());
        data.put("contact", view.getContact().getText());
        data.put("email", view.getEmail().getText());
        data.put("agreedToRules", view.getAgreeCheck().isSelected());

        Firestore db = FirebaseInitializer.getDB();

        new Thread(() -> {
            try {
                ApiFuture<DocumentReference> future = db.collection("fest_forms").add(data);
                future.get(); // Wait for completion
                System.out.println("✅ Fest form submitted: " + data);

                // ✅ Show popup immediately after successful registration
                Platform.runLater(() -> {
                    showAlert("Success", "Your fest registration has been submitted!");
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    showAlert("Error", "Failed to submit the fest registration.");
                });
            }
        }).start();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    private void goBack() {
        Studentdashboard dashboard = new Studentdashboard(stage);
        stage.setScene(dashboard.getScene());
        stage.setTitle("Student Dashboard");
    }
}
