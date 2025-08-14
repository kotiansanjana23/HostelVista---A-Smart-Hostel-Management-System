
// // package com.hostelvista.controller;

// // import com.hostelvista.Authentication.FirebaseAuthService;
// // import com.hostelvista.Authentication.FirebaseAuthService1;
// // import com.hostelvista.model.User;
// // import com.hostelvista.view.SigninView;
// // import com.hostelvista.view.Studentdashboard;
// // import javafx.application.Platform;
// // import javafx.scene.Scene;
// // import javafx.stage.Stage;

// // public class SigninController {

// //     private final SigninView view;
// //     private final Stage stage;

// //     public SigninController(SigninView view, Stage stage) {
// //         this.view = view;
// //         this.stage = stage;
// //         initController();
// //     }

// //     private void initController() {
// //         view.loginButton.setOnAction(e -> {
// //             String email = view.usernameField.getText();
// //             String password = view.passwordField.getText();

// //             if (email.isEmpty() || password.isEmpty()) {
// //                 view.errorText.setText("Please enter a valid email and password.");
// //                 view.errorText.setVisible(true);
// //                 return;
// //             }

// //             User user = new User(email, password);

// //             boolean success = FirebaseAuthService1.signInWithEmailAndPassword(user);
// //             if (success) {
// //                 view.errorText.setVisible(false);
// //                 Platform.runLater(() -> {
// //     try {
// //         Studentdashboard dashboard = new Studentdashboard(stage);
// //         Scene dashboardScene = dashboard.getScene();
// //         stage.setScene(dashboardScene);
// //         stage.setTitle("Student Dashboard");
// //     } catch (Exception ex) {
// //         ex.printStackTrace();
// //     }
// // });

// // }
// // else {
// //                 view.errorText.setText("Invalid email or password.");
// //                 view.errorText.setVisible(true);
// //             }
// //         });

// //         view.signUpLink.setOnMouseClicked(e -> {
// //             com.hostelvista.view.SignupView signupView = new com.hostelvista.view.SignupView(stage);
// //             stage.setScene(signupView.getScene());
// //         });
// //     }
// // }
// // package com.hostelvista.controller;

// // import com.google.cloud.firestore.DocumentSnapshot;
// // import com.google.cloud.firestore.Firestore;
// // import com.google.firebase.cloud.FirestoreClient;
// // import com.hostelvista.Authentication.FirebaseAuthService1;
// // import com.hostelvista.model.User;
// // import com.hostelvista.view.SigninView;
// // import com.hostelvista.view.Studentdashboard;
// // import javafx.application.Platform;
// // import javafx.scene.Scene;
// // import javafx.stage.Stage;

// // public class SigninController {

// //     private final SigninView view;
// //     private final Stage stage;

// //     public SigninController(SigninView view, Stage stage) {
// //         this.view = view;
// //         this.stage = stage;
// //         initController();
// //     }

// //     private void initController() {
// //         view.loginButton.setOnAction(e -> {
// //             String email = view.usernameField.getText();
// //             String password = view.passwordField.getText();

// //             if (email.isEmpty() || password.isEmpty()) {
// //                 view.errorText.setText("Please enter a valid email and password.");
// //                 view.errorText.setVisible(true);
// //                 return;
// //             }

// //             User user = new User(email, password);

// //             boolean success = FirebaseAuthService1.signInWithEmailAndPassword(user);
// //             if (success) {
// //                 view.errorText.setVisible(false);

// //                 // ✅ Fetch user role from Firestore using Admin SDK (sync)
// //                 try {
// //                     Firestore db = FirestoreClient.getFirestore();
// //                     DocumentSnapshot document = db.collection("users").document(email).get().get();

// //                     if (document.exists()) {
// //                         String role = document.getString("role");

// //                         Platform.runLater(() -> {
// //                             try {
// //                                 if ("Student".equalsIgnoreCase(role)) {
// //                                     Studentdashboard dashboard = new Studentdashboard(stage);
// //                                     stage.setScene(dashboard.getScene());
// //                                     stage.setTitle("Student Dashboard");
// //                                 } else if ("Warden".equalsIgnoreCase(role)) {
// //                                     com.hostelvista.view.Wardendashboard wardenDashboard = new com.hostelvista.view.Wardendashboard(stage);
// //                                     stage.setScene(wardenDashboard.getScene());
// //                                     stage.setTitle("Warden Dashboard");
// //                                 } else {
// //                                     view.errorText.setText("Unknown role: " + role);
// //                                     view.errorText.setVisible(true);
// //                                 }
// //                             } catch (Exception ex) {
// //                                 ex.printStackTrace();
// //                             }
// //                         });
// //                     } else {
// //                         view.errorText.setText("User role not found.");
// //                         view.errorText.setVisible(true);
// //                     }
// //                 } catch (Exception ex) {
// //                     ex.printStackTrace();
// //                     view.errorText.setText("Failed to fetch user role.");
// //                     view.errorText.setVisible(true);
// //                 }

// //             } else {
// //                 view.errorText.setText("Invalid email or password.");
// //                 view.errorText.setVisible(true);
// //             }
// //         });

// //         view.signUpLink.setOnMouseClicked(e -> {
// //             com.hostelvista.view.SignupView signupView = new com.hostelvista.view.SignupView(stage);
// //             stage.setScene(signupView.getScene());
// //         });
// //     }
// // }
// package com.hostelvista.controller;

// import com.google.cloud.firestore.DocumentSnapshot;
// import com.google.cloud.firestore.Firestore;
// import com.google.firebase.auth.FirebaseAuth;
// import com.google.firebase.auth.UserRecord;
// import com.google.firebase.cloud.FirestoreClient;
// import com.hostelvista.Authentication.FirebaseAuthService1;
// import com.hostelvista.model.User;
// import com.hostelvista.view.SigninView;
// import com.hostelvista.view.Studentdashboard;
// import javafx.application.Platform;
// import javafx.stage.Stage;

// public class SigninController {

//     private final SigninView view;
//     private final Stage stage;

//     public SigninController(SigninView view, Stage stage) {
//         this.view = view;
//         this.stage = stage;
//         initController();
//     }

//     private void initController() {
//         view.loginButton.setOnAction(e -> {
//             String email = view.usernameField.getText().trim();
//             String password = view.passwordField.getText().trim();

//             if (email.isEmpty() || password.isEmpty()) {
//                 view.errorText.setText("Please enter both email and password.");
//                 view.errorText.setVisible(true);
//                 return;
//             }

//             User user = new User(email, password);

//             boolean success = FirebaseAuthService1.signInWithEmailAndPassword(user);
//             if (success) {
//                 view.errorText.setVisible(false);

//                 try {
//                     Firestore db = FirestoreClient.getFirestore();

//                     // ✅ Get UID from FirebaseAuth
//                     UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
//                     String uid = userRecord.getUid();

//                     // ✅ Use UID to fetch role from Firestore
//                     DocumentSnapshot document = db.collection("users").document(uid).get().get();

//                     if (document.exists()) {
//                         String role = document.getString("role");

//                         Platform.runLater(() -> {
//                             try {
//                                 if ("Student".equalsIgnoreCase(role)) {
//                                     Studentdashboard dashboard = new Studentdashboard(stage);
//                                     stage.setScene(dashboard.getScene());
//                                     stage.setTitle("Student Dashboard");
//                                 } else if ("Warden".equalsIgnoreCase(role)) {
//                                     com.hostelvista.view.Wardendashboard wardenDashboard = new com.hostelvista.view.Wardendashboard(stage);
//                                     stage.setScene(wardenDashboard.getScene());
//                                     stage.setTitle("Warden Dashboard");
//                                 } else {
//                                     view.errorText.setText("Unknown role: " + role);
//                                     view.errorText.setVisible(true);
//                                 }
//                             } catch (Exception ex) {
//                                 ex.printStackTrace();
//                                 view.errorText.setText("Dashboard loading failed.");
//                                 view.errorText.setVisible(true);
//                             }
//                         });
//                     } else {
//                         view.errorText.setText("User role not found in Firestore.");
//                         view.errorText.setVisible(true);
//                     }

//                 } catch (Exception ex) {
//                     ex.printStackTrace();
//                     view.errorText.setText("Error retrieving user role: " + ex.getMessage());
//                     view.errorText.setVisible(true);
//                 }

//             } else {
//                 view.errorText.setText("Invalid email or password.");
//                 view.errorText.setVisible(true);
//             }
//         });

//         view.signUpLink.setOnMouseClicked(e -> {
//             com.hostelvista.view.SignupView signupView = new com.hostelvista.view.SignupView(stage);
//             stage.setScene(signupView.getScene());
//         });
//     }
// }
package com.hostelvista.controller;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.hostelvista.Authentication.FirebaseAuthService1;
import com.hostelvista.model.User;
import com.hostelvista.view.SigninView;
import com.hostelvista.view.Studentdashboard;
import com.hostelvista.view.Wardendashboard;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SigninController {

    private final SigninView view;
    private final Stage stage;

    public SigninController(SigninView view, Stage stage) {
        this.view = view;
        this.stage = stage;
        initController();
    }

    private void initController() {
        view.loginButton.setOnAction(e -> {
            String email = view.usernameField.getText().trim();
            String password = view.passwordField.getText().trim();

            if (email.isEmpty() || password.isEmpty()) {
                view.errorText.setText("Please enter both email and password.");
                view.errorText.setVisible(true);
                return;
            }

            User user = new User(email, password);

            boolean success = FirebaseAuthService1.signInWithEmailAndPassword(user);
            if (success) {
                view.errorText.setVisible(false);

                try {
                    // ✅ Step 1: Get UID
                    UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
                    String uid = userRecord.getUid();
                    System.out.println("✅ Firebase UID: " + uid);

                    // ✅ Step 2: Get Firestore document by UID
                    Firestore db = FirestoreClient.getFirestore();
                    DocumentSnapshot document = db.collection("users").document(uid).get().get();

                    if (document.exists()) {
                        System.out.println("✅ Firestore Document Data: " + document.getData());
                        String role = document.getString("role");
                        System.out.println("✅ Role: " + role);

                        Platform.runLater(() -> {
                            try {
                                if ("Student".equalsIgnoreCase(role)) {
                                    Studentdashboard dashboard = new Studentdashboard(stage);
                                    stage.setScene(dashboard.getScene());
                                    stage.setTitle("Student Dashboard");
                                } else if ("Warden".equalsIgnoreCase(role)) {
                                   com.hostelvista.view.Wardendashboard wardenDashboard = new com.hostelvista.view.Wardendashboard(stage);
                                   
                                 // Wardendashboard wardendashboard = new Wardendashboard(stage);
                                   //  stage.setScene(wardendashboard.getScene());
                                    Scene scene = wardenDashboard.getScene();
                                    stage.setScene(scene);
                                    stage.setTitle("Warden Dashboard");
                                } else {
                                    view.errorText.setText("Unknown role: " + role);
                                    view.errorText.setVisible(true);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                view.errorText.setText("Dashboard loading failed: " + ex.getMessage());
                                view.errorText.setVisible(true);
                            }
                        });
                    } else {
                        view.errorText.setText("User role not found in Firestore.");
                        view.errorText.setVisible(true);
                        System.out.println("❌ No Firestore document for UID: " + uid);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    view.errorText.setText("Error retrieving user role: " + ex.getMessage());
                    view.errorText.setVisible(true);
                }

            } else {
                view.errorText.setText("Invalid email or password.");
                view.errorText.setVisible(true);
            }
        });

        // Signup redirection
        view.signUpLink.setOnMouseClicked(e -> {
            com.hostelvista.view.SignupView signupView = new com.hostelvista.view.SignupView(stage);
            stage.setScene(signupView.getScene());
        });
    }
}
