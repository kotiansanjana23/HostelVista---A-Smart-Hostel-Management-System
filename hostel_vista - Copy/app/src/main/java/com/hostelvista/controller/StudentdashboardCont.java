package com.hostelvista.controller;

import com.hostelvista.view.StudentAdmission;
import com.hostelvista.view.AmbulanceView;
import com.hostelvista.view.DashboardFeesView;
import com.hostelvista.view.DashboardGymView;
import com.hostelvista.view.DashboardmessView;
import com.hostelvista.view.FeedbackFormView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentdashboardCont {

    public void handleAdmissionClick(Stage stage) {
        try {
            StudentAdmission view = new StudentAdmission(stage); // Custom view class
            Scene admissionScene = view.getScene();
            stage.setScene(admissionScene);
            stage.setTitle("Student Admission");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void handleFeesClick(Stage stage) {
    DashboardFeesView view = new DashboardFeesView(stage);
    Scene feesScene = view.getScene();
    stage.setScene(feesScene);
}
public void handleMessClick(Stage stage) {
    DashboardmessView view = new DashboardmessView(stage);
    Scene messScene = view.getScene();
    stage.setScene(messScene);
}
public void handleAmbulanceClick(Stage stage) {
    AmbulanceView view = new AmbulanceView(stage);
    stage.setScene(view.getScene());
    stage.setTitle("Emergency Contacts");
}
public void handleGymClick(Stage stage) {
    DashboardGymView view = new DashboardGymView(stage);
    stage.setScene(view.getScene());
    stage.setTitle("Gym Facilities");
}



    public void handleFeedbackClick(Stage stage) {
        new FeedbackFormCont(stage); // ✅ This line is essential for opening the feedback view
    }

    public void handleFestTrackerClick(Stage stage) {
    new FestFormController(stage); // This assumes you have a FestTrackerFormCont class like FeedbackFormCont
}

public void handleComplaintClick(Stage stage) {
    new ComplaintFormController(stage);
}



    public void start(Stage stage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}
