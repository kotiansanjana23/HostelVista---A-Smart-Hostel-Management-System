
// package com.hostelvista.controller;

// import com.google.cloud.firestore.Firestore;
// import com.hostelvista.Authentication.FirebaseInitializer;
// import com.hostelvista.view.AdmissionFormView;
// import com.hostelvista.view.Studentdashboard;
// import javafx.stage.Stage;

// import java.util.HashMap;
// import java.util.Map;

// public class AdmissionFormController {

//     private final AdmissionFormView view;
//     private final Stage stage;

//     public AdmissionFormController(AdmissionFormView view, Stage stage) {
//         this.view = view;
//         this.stage = stage;

//         view.backBtn.setOnAction(e -> {
//             Studentdashboard dashboard = new Studentdashboard();
//             stage.setScene(dashboard.getScene(stage));
//         });

//         view.submitBtn.setOnAction(e -> submitForm());
//     }

//     private void submitForm() {
//         FirebaseInitializer.initialize();
//         Map<String, Object> data = new HashMap<>();

//         data.put("fullName", view.fullName.getText());
//         data.put("dob", view.dob.getValue() != null ? view.dob.getValue().toString() : "");
//         data.put("gender", view.gender.getValue());
//         data.put("nationality", view.nationality.getValue());
//         data.put("address", view.address.getText());
//         data.put("city", view.city.getText());
//         data.put("postalCode", view.postalCode.getText());
//         data.put("phone", view.phone.getText());
//         data.put("email", view.email.getText());

//         data.put("program", view.program.getValue());
//         data.put("admissionYear", view.year.getValue());

//         data.put("fatherName", view.fatherName.getText());
//         data.put("fatherOccupation", view.fatherOcc.getText());
//         data.put("fatherPhone", view.fatherPhone.getText());
//         data.put("motherName", view.motherName.getText());
//         data.put("motherOccupation", view.motherOcc.getText());
//         data.put("motherPhone", view.motherPhone.getText());
//         data.put("guardianName", view.guardianName.getText());
//         data.put("guardianRelation", view.guardianRelation.getValue());
//         data.put("guardianPhone", view.guardianPhone.getText());

//         data.put("emergencyName", view.emergencyName.getText());
//         data.put("emergencyRelation", view.emergencyRel.getValue());
//         data.put("emergencyPhone", view.emergencyPhone.getText());

//         data.put("medicalConditions", view.conditions.getText());
//         data.put("allergies", view.allergies.getText());
//         data.put("additionalInfo", view.additionalInfo.getText());
//         data.put("admissionDate", view.declarationDate.getValue() != null ? view.declarationDate.getValue().toString() : "");

//         Firestore db = FirebaseInitializer.getDB();

//         new Thread(() -> {
//             try {
//                 int count = db.collection("admissions").get().get().getDocuments().size();
//                 String docName = "student" + (count + 1);

//                 db.collection("admissions").document(docName).set(data);
//                 System.out.println("✅ Submitted as " + docName);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//                 System.out.println("❌ Failed to submit form");
//             }
//         }).start();
//     }
// }
package com.hostelvista.controller;

import com.google.cloud.firestore.Firestore;
import com.hostelvista.Authentication.FirebaseInitializer;
import com.hostelvista.view.AdmissionFormView;
import com.hostelvista.view.Studentdashboard;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class AdmissionFormController {

    private final AdmissionFormView view;
    private final Stage stage;

    public AdmissionFormController(AdmissionFormView view, Stage stage) {
        this.view = view;
        this.stage = stage;

        view.backBtn.setOnAction(e -> {
            Studentdashboard dashboard = new Studentdashboard();
            stage.setScene(dashboard.getScene(stage));
        });

        view.submitBtn.setOnAction(e -> submitForm());
    }

    private void submitForm() {
        FirebaseInitializer.initialize();
        Map<String, Object> data = new HashMap<>();

        data.put("fullName", view.fullName.getText());
        data.put("dob", view.dob.getValue() != null ? view.dob.getValue().toString() : "");
        data.put("gender", view.gender.getValue());
        data.put("nationality", view.nationality.getValue());
        data.put("address", view.address.getText());
        data.put("city", view.city.getText());
        data.put("postalCode", view.postalCode.getText());
        data.put("phone", view.phone.getText());
        data.put("email", view.email.getText());

        data.put("program", view.program.getValue());
        data.put("admissionYear", view.year.getValue());

        data.put("fatherName", view.fatherName.getText());
        data.put("fatherOccupation", view.fatherOcc.getText());
        data.put("fatherPhone", view.fatherPhone.getText());
        data.put("motherName", view.motherName.getText());
        data.put("motherOccupation", view.motherOcc.getText());
        data.put("motherPhone", view.motherPhone.getText());
        data.put("guardianName", view.guardianName.getText());
        data.put("guardianRelation", view.guardianRelation.getValue());
        data.put("guardianPhone", view.guardianPhone.getText());

        data.put("emergencyName", view.emergencyName.getText());
        data.put("emergencyRelation", view.emergencyRel.getValue());
        data.put("emergencyPhone", view.emergencyPhone.getText());

        data.put("medicalConditions", view.conditions.getText());
        data.put("allergies", view.allergies.getText());
        data.put("additionalInfo", view.additionalInfo.getText());
        data.put("admissionDate", view.declarationDate.getValue() != null ? view.declarationDate.getValue().toString() : "");

        Firestore db = FirebaseInitializer.getDB();

        new Thread(() -> {
            try {
                int count = db.collection("admissions").get().get().getDocuments().size();
                String docName = "student" + (count + 1);

                db.collection("admissions").document(docName).set(data).get(); // wait for completion

                System.out.println("✅ Submitted as " + docName);

                // Show success popup on UI thread
                Platform.runLater(() -> {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Form Submitted");
                    alert.setHeaderText(null);
                    alert.setContentText("Your admission form has been submitted successfully!");
                    alert.showAndWait();
                });

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("❌ Failed to submit form");

                // Show error popup on UI thread
                Platform.runLater(() -> {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Submission Failed");
                    alert.setHeaderText("An error occurred");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });
            }
        }).start();
    }
}
