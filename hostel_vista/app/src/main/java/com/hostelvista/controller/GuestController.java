package com.hostelvista.controller;

import com.hostelvista.view.DashboardExchangeView;
import com.hostelvista.view.GuestView;

import javafx.application.HostServices;
//import com.hostelvista.DashboardExchangeView;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuestController {

    private ImageView selectedBed = null;
    private StackPane selectedPane = null;
    private String guestDetails = "";
    private HostServices hostServices;
    private GuestView view;
    private Stage stage;

    public GuestController(Stage stage, HostServices hostServices) {
        this.stage = stage;
         this.hostServices = hostServices;
        this.view = new GuestView(stage);

        createBeds();
        setupActions();

        stage.setTitle("Select Bed In Guest Room");
        stage.setScene(view.getScene());
        stage.show();
    }

    private void createBeds() {
        for (int i = 0; i < 3; i++) {
            StackPane bedPane = createBedPane(view.getBedImage());
            view.getBedsBox().getChildren().add(bedPane);
        }
    }

    private StackPane createBedPane(javafx.scene.image.Image image) {
        ImageView bedView = new ImageView(image);
        bedView.setFitWidth(400);
        bedView.setFitHeight(400);

        Label tick = new Label("✅");
        tick.setStyle("-fx-font-size: 40px;");
        tick.setVisible(false);

        StackPane pane = new StackPane(bedView, tick);
        pane.setOnMouseClicked(e -> selectBed(bedView, pane, tick));
        return pane;
    }

    private void selectBed(ImageView bed, StackPane pane, Label tick) {
        if (selectedBed != null && selectedPane != null) {
            selectedPane.setStyle("");
            ((Label) selectedPane.getChildren().get(1)).setVisible(false);
        }
        selectedBed = bed;
        selectedPane = pane;
        pane.setStyle("-fx-border-color: limegreen; -fx-border-width: 5;");
        tick.setVisible(true);
    }

    private void setupActions() {
        view.getEnterDetailsButton().setOnAction(e -> openDetailsDialog());
        // view.getPayButton().setOnAction(e -> {
        //     if (selectedBed != null) {
        //         hostServices.showDocument("https://rzp.io/l/yourpaymentlink");
        //     } else {
        //         showAlert("Please select a bed before proceeding.");
        //     }
        // });
        view.getPayButton().setOnAction(e -> {
    if (selectedBed != null) {
        showQRCodeWindow(); // 👈 New method to show PhonePe QR
    } else {
        showAlert("Please select a bed before proceeding.");
    }
});


        view.getBackButton().setOnAction(e -> {
            DashboardExchangeView dashboardexchange = new DashboardExchangeView(stage, hostServices);
            dashboardexchange.start(stage);
        });
    }
    private void showQRCodeWindow() {
    Stage qrStage = new Stage();
    qrStage.setTitle("Scan to Pay with PhonePe");

    // Load your PhonePe QR image (make sure it's in your resources/images folder)
    ImageView qrImage = new ImageView(new javafx.scene.image.Image(
        getClass().getResourceAsStream("/Assets/Images/qr_pay.jpg")
    ));
    qrImage.setFitWidth(300);
    qrImage.setFitHeight(300);

    Label instructions = new Label("Scan this QR with your PhonePe app to complete the payment.");
    instructions.setStyle("-fx-font-size: 14px; -fx-padding: 10;");

    Button doneButton = new Button("Payment Done");
    doneButton.setOnAction(e -> {
        qrStage.close();
        showAlert("Thank you! Your payment has been recorded.");
    });

    VBox layout = new VBox(15, qrImage, instructions, doneButton);
    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

    qrStage.setScene(new Scene(layout));
    qrStage.show();
}


    private void openDetailsDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Guest Details");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter your name or details:");

        dialog.showAndWait().ifPresent(input -> {
            guestDetails = input;
            showAlert("Details Saved: " + guestDetails);
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void start(Stage currentStage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}
