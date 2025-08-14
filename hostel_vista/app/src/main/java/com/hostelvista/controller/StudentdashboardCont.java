package com.hostelvista.controller;

import com.hostelvista.view.StudentAdmission;
import com.hostelvista.view.StudyRoomView;
import com.hostelvista.view.AmbulanceView;
import com.hostelvista.view.DashboardExchangeView;
//import com.hostelvista.view.DashboardExchangeView;
import com.hostelvista.view.DashboardFeesView;
import com.hostelvista.view.DashboardGymView;
import com.hostelvista.view.DashboardmessView;
import com.hostelvista.view.FeePaymentView;
import com.hostelvista.view.FeedbackFormView;
//import com.hostelvista.view.LateFeeReceiptView;
import com.hostelvista.view.LateFeePaymentView;

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

public void handleLateFeePaymentClick(Stage stage) {
    LateFeePaymentView view = new LateFeePaymentView(stage);
    Scene scene = view.getScene(); // ✅ get the scene
    stage.setScene(scene); // ✅ navigate
    stage.setTitle("Late Fee Payment");
}
public void handleFeePaymentClick(Stage stage) {
    FeePaymentView view = new FeePaymentView(stage);
    Scene scene = view.getScene(); // ✅ get the scene
    stage.setScene(scene); // ✅ navigate
    stage.setTitle("Late Fee Payment");
}

// public void handleComplaintClick(Stage stage) {
//    new ComplaintController(stage).showForm();
// }
public void handleComplaintClick(Stage stage) {
    ComplaintController controller = new ComplaintController(stage);
    controller.showForm(); // ✅ Show the complaint form
}


public void handleAccessoryClick(Stage stage) {
    System.out.println("Navigating to Accessory (DashboardExchangeView)");
    DashboardExchangeView exchangeView = new DashboardExchangeView(stage, null);
    exchangeView.createUI(); // <== Important!
    stage.setScene(exchangeView.getScene());
    stage.show();
}

    public void start(Stage stage) {
       try {
        com.hostelvista.view.Studentdashboard view = new com.hostelvista.view.Studentdashboard(stage);
        Scene scene = view.getScene();
        stage.setScene(scene);
        stage.setTitle("Student Dashboard");
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
