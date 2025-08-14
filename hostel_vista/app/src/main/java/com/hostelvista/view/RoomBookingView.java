package com.hostelvista.view;

import com.hostelvista.controller.RoomBookingController;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class RoomBookingView {
    private Stage stage;
    private RoomBookingController controller;

    public RoomBookingView(Stage stage) {
        this.stage = stage;
        this.controller = new RoomBookingController(stage);
    }

    public Scene getScene() {
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setStyle("-fx-background-color: white;");

        Text header = new Text("Select Room");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        header.setFill(Color.web("#7A3EBD"));

        mainLayout.getChildren().add(header);

        for (int f = 0; f < controller.getFloors().length; f++) {
            String floor = controller.getFloors()[f];
            VBox floorBox = new VBox(10);
            Text floorLabel = new Text(floor);
            floorLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            floorLabel.setFill(Color.web("#7A3EBD"));

            FlowPane roomPane = new FlowPane(15, 10);
            roomPane.setPadding(new Insets(5));
            roomPane.setPrefWrapLength(1000);

            for (int i = 1; i <= controller.getRoomsPerFloor(); i++) {
                Button roomBtn = controller.createRoomButton(f, i);
                roomPane.getChildren().add(roomBtn);
            }

            floorBox.getChildren().addAll(floorLabel, roomPane);
            mainLayout.getChildren().add(floorBox);
        }

        ImageView arrowIcon = new ImageView(new Image("\\Assets\\Images\\backarrow.png"));
        arrowIcon.setFitWidth(50);
        arrowIcon.setFitHeight(50);

        Button backButton = new Button();
        backButton.setGraphic(arrowIcon);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> controller.goBack());

        HBox topRight = new HBox(backButton);
        topRight.setAlignment(Pos.TOP_RIGHT);
        topRight.setPadding(new Insets(10, 10, 0, 0));

        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);

        BorderPane root = new BorderPane();
        root.setTop(topRight);
        root.setCenter(scrollPane);

        return new Scene(root, 1560, 790);
    }
}
