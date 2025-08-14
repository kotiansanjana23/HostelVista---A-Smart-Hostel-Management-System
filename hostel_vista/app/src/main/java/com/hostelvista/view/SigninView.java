package com.hostelvista.view;

import com.hostelvista.controller.SigninController;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class SigninView {

    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;
    public Label signUpLink;
    public Text errorText;
    public Scene scene;

    public VBox rightBox;

    public SigninView(Stage stage) {
        buildUI();
        new SigninController(this, stage); // Connect controller to view
    }

    private void buildUI() {
        // ===== LEFT PANE =====
        Label helloLabel = new Label("Hello!");
        helloLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        helloLabel.setTextFill(Color.web("#9B5DE5"));

        ImageView imageView = new ImageView(new Image("Assets\\Images\\image1.jpg"));
        imageView.setFitWidth(650);
        imageView.setFitHeight(600);

        VBox leftBox = new VBox(20, helloLabel, imageView);
        leftBox.setPadding(new Insets(40));
        leftBox.setStyle("-fx-background-color: white;");
        leftBox.setPrefWidth(900);

        // ===== RIGHT PANE =====
        Label signInLabel = new Label("Sign In");
        signInLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        signInLabel.setTextFill(Color.WHITE);

        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setTextFill(Color.WHITE);

        usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setFont(Font.font(15));
        usernameField.setMaxWidth(350);
        usernameField.setPrefHeight(50);
        usernameField.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-border-color: #ccc;");

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font(20));
        passwordLabel.setTextFill(Color.WHITE);

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setFont(Font.font(15));
        passwordField.setMaxWidth(350);
        passwordField.setPrefHeight(50);
        passwordField.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-border-color: #ccc;");

        errorText = new Text();
        errorText.setFill(Color.RED);
        errorText.setFont(Font.font(15));

        Label forgotPass = new Label("Forget Password?");
        forgotPass.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        forgotPass.setTextFill(Color.WHITE);
        forgotPass.setPadding(new Insets(5, 0, 10, 210));

        loginButton = new Button("Sign In");
        loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        loginButton.setMaxWidth(350);
        loginButton.setPrefHeight(50);
        loginButton.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-border-color: #ccc;");
        loginButton.setTextFill(Color.web("#9B5DE5"));

        signUpLink = new Label("Don’t have an account yet ? SignUp");
        signUpLink.setFont(Font.font(15));
        signUpLink.setTextFill(Color.WHITE);

        VBox fieldBox = new VBox(15, usernameLabel, usernameField, passwordLabel, passwordField, forgotPass, loginButton, errorText, signUpLink);
        fieldBox.setAlignment(Pos.CENTER_LEFT);

        rightBox = new VBox(30, signInLabel, fieldBox);
        rightBox.setPadding(new Insets(150, 100, 100, 100));
        rightBox.setStyle("-fx-background-color: #9B5DE5; -fx-background-radius: 100; -fx-border-radius: 100; -fx-border-color: #ccc;");
        rightBox.setPrefWidth(900);

        HBox root = new HBox(leftBox, rightBox);
        root.setPadding(new Insets(0));
        root.setStyle("-fx-background-radius: 100; -fx-border-radius: 100; -fx-border-color: #ccc;");

        scene = new Scene(root, 1560, 790);
    }

    public Scene getScene() {
        return scene;
    }
}
