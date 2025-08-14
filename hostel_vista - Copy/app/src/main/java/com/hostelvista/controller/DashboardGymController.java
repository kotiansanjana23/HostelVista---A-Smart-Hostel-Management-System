package com.hostelvista.controller;

import com.hostelvista.view.GymView;
import com.hostelvista.view.LaundryView;
import com.hostelvista.view.GymView;
import com.hostelvista.view.Studentdashboard;
import com.hostelvista.view.StudyRoomView;

import javafx.stage.Stage;

public class DashboardGymController {

    private Stage stage;

    public DashboardGymController(Stage stage) {
        this.stage = stage;
    }

    // 🔙 Handle Back button in the Gym Dashboard
   public void handleBackButton() {
        try {
            Studentdashboard dashboardview = new Studentdashboard(stage);
            stage.setScene(dashboardview.getScene()); // ✅ Make sure Studentdashboard has getScene()
            stage.setTitle("Student Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 💪 Handle click on the "Facilities" card inside Dashboardgym
    public void handleGymFacilitiesClick() {
        GymView view = new GymView();
        view.start(stage); // Load GymFacilitiesView scene into the current stage
    }
    public void handleLaundryClick() {
        LaundryView view = new LaundryView(stage);
        stage.setScene(view.getScene()); // This assumes LaundryView has getScene()
        stage.setTitle("Laundry Booking");
    }
    public void handleStudyRoomClick() {
        StudyRoomView view = new StudyRoomView(stage); // ✅ You must have this class
        stage.setScene(view.getScene());
        stage.setTitle("Study Table Booking");
    }
}
