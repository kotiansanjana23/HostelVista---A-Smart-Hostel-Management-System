package com.hostelvista;

import com.hostelvista.view.SigninView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SigninView signinView = new SigninView(primaryStage);
        primaryStage.setScene(signinView.getScene());
        primaryStage.setTitle("HostelVista");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}