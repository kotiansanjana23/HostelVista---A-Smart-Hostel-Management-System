package com.hostelvista.controller;

import com.hostelvista.view.DashboardmessView;
import com.hostelvista.view.StudentMenuView;
import javafx.stage.Stage;

public class StudentMenuController {
    public void showMenuScene(Stage stage) {
        StudentMenuView view = new StudentMenuView(stage);
        stage.setScene(view.getScene()); // 🔥 THIS IS CRUCIAL
        stage.setTitle("Today’s Menu");
    }
    public void goBackToDashboard(Stage stage) {
    DashboardmessView dashboardView = new DashboardmessView(stage);
    stage.setScene(dashboardView.getScene());
    stage.setTitle("Dashboard - Mess");
}

}
