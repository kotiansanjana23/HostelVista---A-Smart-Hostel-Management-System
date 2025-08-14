package com.hostelvista.controller;

import com.hostelvista.view.GymView;
import javafx.stage.Stage;

public class GymController {

    public void handleGymFacilitiesClick(Stage stage) {
        try {
            GymView view = new GymView(stage);
            view.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

