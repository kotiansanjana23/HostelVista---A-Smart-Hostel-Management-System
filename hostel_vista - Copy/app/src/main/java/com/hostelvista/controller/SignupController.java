
package com.hostelvista.controller;

import com.hostelvista.view.SignupView;
import com.hostelvista.Authentication.FirebaseAuthService;
import com.hostelvista.model.User;
import com.hostelvista.view.SigninView;
import javafx.stage.Stage;

public class SignupController {

    private final SignupView view;
    private final Stage stage;

    public SignupController(SignupView view, Stage stage) {
        this.view = view;
        this.stage = stage;
        initController();
    }

    private void initController() {
        view.loginLink.setOnMouseClicked(e -> {
            SigninView signinView = new SigninView(stage);
            stage.setScene(signinView.getScene());
        });

    view.signupButton.setOnAction(e -> {
    String email = view.emailField.getText();
    String username = view.usernameField.getText();
    String password = view.passwordField.getText();
    String role = view.comboBox.getValue();

    if (email.isEmpty() || username.isEmpty() || password.isEmpty() || role == null) {
        view.errorText.setText("Please fill all fields.");
        return;
    }

    // ✅ Replace this line:
    // view.errorText.setText("Signed up successfully! (you can implement real logic)");

    // ✅ With this code:
    User user = new User(email, password);
    boolean success = FirebaseAuthService.signUpWithEmailAndPassword(user);

    if (success) {
        view.errorText.setText("Signup successful!");
    } else {
        view.errorText.setText("Signup failed. Try again.");
    }
});

 }
}
