package com.hostelvista.controller;

import com.hostelvista.view.DashboardGymView;
import com.hostelvista.view.StudyRoomView;
import javafx.stage.Stage;

public class StudyRoomController {

    private final Stage stage;

    public StudyRoomController(Stage stage) {
        this.stage = stage;
    }

    public void handleStudyRoomClick() {
        StudyRoomView view = new StudyRoomView(stage);
        stage.setScene(view.getScene());
        stage.setTitle("Study Table Booking");
    }

    public void handleBackButton() {
        DashboardGymView view = new DashboardGymView(stage);
        stage.setScene(view.getScene());
        stage.setTitle("Gym Dashboard");
    }
}

