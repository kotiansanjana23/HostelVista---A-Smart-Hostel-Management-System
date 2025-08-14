package com.hostelvista.view;

import com.hostelvista.controller.SignupController;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class SignupView {

    private final Scene scene;

    public TextField emailField, usernameField;
    public PasswordField passwordField;
    public ComboBox<String> comboBox;
    public Button signupButton;
    public Label loginLink;
    public Text errorText;

    public SignupView(Stage stage) {
        // Left section (Image + Welcome)
        Label welcomeLabel = new Label("Welcome!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        welcomeLabel.setTextFill(Color.web("#9B5DE5"));

        ImageView imageView = new ImageView(new Image("Assets/images/image2.jpg"));
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);

        VBox leftBox = new VBox(20, welcomeLabel, imageView);
        leftBox.setPadding(new Insets(40));
        leftBox.setStyle("-fx-background-color: white;");
        leftBox.setPrefWidth(900);

        // Right section (Form)
        Label signUpLabel = new Label("Sign Up");
        signUpLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        signUpLabel.setTextFill(Color.WHITE);

        // Email Field
        Label emailLabel = new Label("Email");
        styleLabel(emailLabel);
        emailField = new TextField();
        emailField.setPromptText("Enter your email");
        styleField(emailField);

        // Username Field
        Label usernameLabel = new Label("Username");
        styleLabel(usernameLabel);
        usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        styleField(usernameField);

        // Password Field
        Label passwordLabel = new Label("Password");
        styleLabel(passwordLabel);
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        styleField(passwordField);

        // Role ComboBox
        Label roleLabel = new Label("User");
        styleLabel(roleLabel);
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Student", "Warden", "Cleaner");
        comboBox.setPromptText("Select Role");
        styleComboBox(comboBox);

        // Sign Up Button
        signupButton = new Button("Sign Up");
        signupButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        signupButton.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-border-color: #ccc;");
        signupButton.setTextFill(Color.web("#9B5DE5"));
        signupButton.setPrefHeight(50);
        signupButton.setMaxWidth(350);

        // Login Link
        loginLink = new Label("Already have an account? Sign In");
        loginLink.setTextFill(Color.WHITE);
        loginLink.setFont(Font.font(15));

        // Error Text
        errorText = new Text();
        errorText.setFill(Color.RED);
        errorText.setFont(Font.font(15));

        VBox formBox = new VBox(10,
                signUpLabel,
                emailLabel, emailField,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                roleLabel, comboBox,
                signupButton,
                loginLink,
                errorText
        );

        formBox.setAlignment(Pos.CENTER_LEFT);
        formBox.setPadding(new Insets(50, 100, 50, 100));
        formBox.setStyle("-fx-background-color: #9B5DE5;" +
                "-fx-background-radius: 100; " +
                "-fx-border-radius: 100; " +
                "-fx-border-color: #ccc;");
        formBox.setPrefWidth(900);

        HBox root = new HBox(formBox, leftBox);
        scene = new Scene(root, 1560, 790);

        // Attach controller
        new SignupController(this, stage);
    }

    private void styleField(TextField field) {
        field.setFont(Font.font(15));
        field.setMaxWidth(350);
        field.setPrefHeight(50);
        field.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-border-color: #ccc;");
        field.setFocusTraversable(false);
    }

    private void styleComboBox(ComboBox<String> box) {
        // Avoid using box.setFont(), use CSS instead
        box.setMaxWidth(350);
        box.setPrefHeight(50);
        box.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-background-radius: 15;" +
                "-fx-border-radius: 15;" +
                "-fx-border-color: #ccc;"
        );
        box.setFocusTraversable(false);
    }

    private void styleLabel(Label label) {
        label.setFont(Font.font(20));
        label.setTextFill(Color.WHITE);
    }

    public Scene getScene() {
        return scene;
    }
}
