
package com.hostelvista.controller;

import com.hostelvista.Authentication.FirebaseAuthService;
import com.hostelvista.Authentication.FirebaseAuthService1;
import com.hostelvista.model.User;
import com.hostelvista.view.SigninView;
import com.hostelvista.view.Studentdashboard;
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
            String email = view.usernameField.getText();
            String password = view.passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                view.errorText.setText("Please enter a valid email and password.");
                view.errorText.setVisible(true);
                return;
            }

            User user = new User(email, password);

            boolean success = FirebaseAuthService1.signInWithEmailAndPassword(user);
            if (success) {
                view.errorText.setVisible(false);
                Platform.runLater(() -> {
    try {
        Studentdashboard dashboard = new Studentdashboard(stage);
        Scene dashboardScene = dashboard.getScene();
        stage.setScene(dashboardScene);
        stage.setTitle("Warden Dashboard");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});

}
else {
                view.errorText.setText("Invalid email or password.");
                view.errorText.setVisible(true);
            }
        });

        view.signUpLink.setOnMouseClicked(e -> {
            com.hostelvista.view.SignupView signupView = new com.hostelvista.view.SignupView(stage);
            stage.setScene(signupView.getScene());
        });
    }
}
