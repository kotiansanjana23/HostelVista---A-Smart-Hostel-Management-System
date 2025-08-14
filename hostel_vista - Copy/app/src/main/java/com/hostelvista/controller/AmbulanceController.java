package com.hostelvista.controller;

import com.hostelvista.view.Studentdashboard;
import javafx.stage.Stage;

public class AmbulanceController{
    private Stage stage;

    public AmbulanceController(Stage stage) {
        this.stage = stage;
    }

    public void handleBack() {
        Studentdashboard dashboard = new Studentdashboard(stage);
        stage.setScene(dashboard.getScene());
    }
}

