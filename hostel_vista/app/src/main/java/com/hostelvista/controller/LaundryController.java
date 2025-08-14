package com.hostelvista.controller;

import com.hostelvista.view.DashboardGymView;
import com.hostelvista.view.LaundryView;
import javafx.stage.Stage;

public class LaundryController {

    private Stage stage;

    public LaundryController(Stage stage) {
        this.stage = stage;
    }

    // 🔙 Handle Back button from Laundry view
    public void handleBackButton() {
        DashboardGymView dashboardGymView = new DashboardGymView(stage);
        stage.setScene(dashboardGymView.getScene());
        stage.setTitle("Gym Dashboard");
    }

    // 🧺 Handle laundry card click from gym dashboard
    public void openLaundryView() {
        LaundryView laundryView = new LaundryView(stage);
        stage.setScene(laundryView.getScene());
        stage.setTitle("Laundry Booking");
    }
}

