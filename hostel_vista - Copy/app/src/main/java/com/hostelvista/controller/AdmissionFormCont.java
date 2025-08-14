package com.hostelvista.controller;

import com.hostelvista.Authentication.FirebaseInitializer;
import com.hostelvista.view.AdmissionFormView;
import javafx.application.Application;
import javafx.stage.Stage;

public class AdmissionFormCont extends Application {

    @Override
    public void start(Stage primaryStage) {
        FirebaseInitializer.initialize();  // Ensure Firebase is initialized
        AdmissionFormView view = new AdmissionFormView(primaryStage);
        primaryStage.setScene(view.getScene(primaryStage));
        primaryStage.setTitle("HostelVista - Admission Form");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
