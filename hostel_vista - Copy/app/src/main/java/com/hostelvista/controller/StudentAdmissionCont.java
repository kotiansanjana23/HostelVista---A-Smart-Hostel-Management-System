// package com.hostelvista.controller;

// //import com.hostelproj.RoomBookingUI;
// import com.hostelvista.view.StudentAdmission;
// //import com.hostelvista.Studentdashboard; // this refers to your main class again, if reused
// import javafx.stage.Stage;

// public class StudentAdmissionCont {
//     private Stage stage;

//     public StudentAdmissionCont(Stage stage) {
//         this.stage = stage;
//     }

//     // public void handleRoomClick() {
//     //     try {
//     //         RoomBookingUI bookingPage = new RoomBookingUI();
//     //         bookingPage.start(stage);
//     //     } catch (Exception e) {
//     //         e.printStackTrace();
//     //     }
//     // }

//     public void handleAdmissionClick() {
//         try {
//             StudentAdmission admissionPage = new StudentAdmission(stage);
//             admissionPage.start(stage);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }  

package com.hostelvista.controller;

import com.hostelvista.view.AdmissionFormView;
import com.hostelvista.view.RoomBookingView;
import com.hostelvista.view.StudentAdmission;
import com.hostelvista.view.Studentdashboard;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentAdmissionCont {
    private Stage stage;

    public StudentAdmissionCont(Stage stage) {
        this.stage = stage;
    }

    public void handleAdmissionClick() {
        try {
            StudentAdmission admissionPage = new StudentAdmission(stage);
            stage.setScene(admissionPage.getScene());
            stage.setTitle("Student Admission");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // In StudentAdmissionCont.java

public void handleRoomBookingClick() {
    try {
        RoomBookingView bookingView = new RoomBookingView(stage);
        Scene bookingScene = bookingView.getScene();
        stage.setScene(bookingScene);
        stage.setTitle("Room Booking");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void handleAdmissionFormClick() {
    try {
        AdmissionFormView admissionPage = new AdmissionFormView(stage); // View class
        stage.setScene(admissionPage.getScene(stage));                    // Set the Scene from View
        stage.setTitle("Student Admission");
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void goBack() {
    try {
        // Go back to the main Student Dashboard
        Studentdashboard dashboard = new Studentdashboard(stage);
        Scene dashboardScene = dashboard.getScene();
        stage.setScene(dashboardScene);
        stage.setTitle("Student Dashboard");
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}

