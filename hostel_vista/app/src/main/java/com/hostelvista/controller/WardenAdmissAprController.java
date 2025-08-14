// package com.hostelvista.controller;

// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.*;
// import com.hostelvista.Authentication.FirebaseInitializer;
// import com.hostelvista.view.AdmissionApprovalApp;

// // import com.hostelvista.view.AdmissionApprovalView;
// import javafx.application.Platform;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.stage.Stage;

// import java.util.List;
// import java.util.Map;

// public class WardenAdmissAprController {
// // new WardenAdmissAprController(view);  // This fetches & binds data

//     private final AdmissionApprovalApp view;
//     private final ObservableList<AdmissionApprovalApp.AdmissionEntry> admissionList = FXCollections.observableArrayList();

//     public WardenAdmissAprController(AdmissionApprovalApp view) {
//         this.view = view;
//         loadAdmissionData();
//     }

//     private void loadAdmissionData() {
//         Firestore db = FirebaseInitializer.getDB();

//         ApiFuture<QuerySnapshot> future = db.collection("admissions").get();

//         new Thread(() -> {
//             try {
//                 List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//                 for (QueryDocumentSnapshot doc : documents) {
//                     Map<String, Object> data = doc.getData();

//                     String name = String.valueOf(data.getOrDefault("name", "N/A"));
//                     String number = String.valueOf(data.getOrDefault("number", "N/A"));
//                     String year = String.valueOf(data.getOrDefault("year", "N/A"));
//                     String room = String.valueOf(data.getOrDefault("room", "N/A"));
//                     String course = String.valueOf(data.getOrDefault("course", "N/A"));
//                     String doa = String.valueOf(data.getOrDefault("doa", "N/A"));
//                     String amount = String.valueOf(data.getOrDefault("amount", "N/A"));
//                     String fees = String.valueOf(data.getOrDefault("fees", "Not Paid"));

//                     AdmissionApprovalApp.AdmissionEntry entry = new AdmissionApprovalApp.AdmissionEntry(
//                             name, number, year, room, course, doa, amount, fees
//                     );
//                     admissionList.add(entry);
//                 }

//                 // Platform.runLater(() -> view.getTable().setItems(admissionList));

//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         }).start();
//     }
// }
