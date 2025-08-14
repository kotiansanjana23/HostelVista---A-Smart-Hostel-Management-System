package com.hostelvista.controller;

import com.hostelvista.view.DashboardmessView;
import com.hostelvista.view.StudentMenuView;
import com.hostelvista.view.Studentdashboard;
import javafx.stage.Stage;

public class DashboardMessController {
    private DashboardmessView view;
    private Stage stage;

    public DashboardMessController(DashboardmessView view, Stage stage) {
        this.view = view;
        this.stage = stage;
    }

    public void handleBackButton() {
        Studentdashboard dashboardView= new Studentdashboard(stage);
        stage.setScene(dashboardView.getScene());
    }

    public void handleMenuCardClick() {
        StudentMenuView menuView = new StudentMenuView(stage);
        stage.setScene(menuView.getScene());
        stage.setTitle("Today’s Menu");
    }
    public void handleMessChangeCardClick() {
    new MessChangeController(stage);
}

}
