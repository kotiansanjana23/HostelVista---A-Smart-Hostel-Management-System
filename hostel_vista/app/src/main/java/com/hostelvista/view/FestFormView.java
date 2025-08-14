// package com.hostelvista.view;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.stage.Stage;

// public class FestFormView {

//     private GridPane formGrid;
//     private VBox mainContainer;
//     private ScrollPane scrollPane;

//     private Stage stage;

//     // Controls
//     private TextField nameField = new TextField();
//     private TextField collegeId = new TextField();
//     private ComboBox<String> department = new ComboBox<>();
//     private ComboBox<String> year = new ComboBox<>();
//     private ComboBox<String> festName = new ComboBox<>();
//     private ComboBox<String> participationType = new ComboBox<>();
//     private TextField contact = new TextField();
//     private TextField email = new TextField();
//     private CheckBox agreeCheck = new CheckBox("I agree to follow the rules and instructions for the fest.");
//     private Button submitBtn = new Button("REGISTER");
//     private Button backButton = new Button();  // Now uses an icon

//     public FestFormView(Stage stage) {
//         this.stage = stage;

//         formGrid = new GridPane();
//         formGrid.setPadding(new Insets(20));
//         formGrid.setHgap(15);
//         formGrid.setVgap(10);
//         formGrid.setAlignment(Pos.CENTER);
//         formGrid.setStyle("-fx-background-color: #EFEAFE;");
//         formGrid.setMaxWidth(500);

//         Label titleLabel = new Label("STUDENT FEST REGISTRATION");
//         titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #7F3DFF;");
//         formGrid.add(titleLabel, 0, 0, 2, 1);

//         department.getItems().addAll("Computer", "Electronics", "Mechanical", "Civil", "Other");
//         year.getItems().addAll("1st Year", "2nd Year", "3rd Year", "Final Year");
//         festName.getItems().addAll("TechFest", "Cultural Fest", "Sports Meet", "Music Fest", "Other");
//         participationType.getItems().addAll("Solo", "Group", "Volunteer");

//         addRow("Full Name:", nameField, 1);
//         addRow("College ID:", collegeId, 2);
//         addRow("Department:", department, 3);
//         addRow("Year:", year, 4);
//         addRow("Fest Name:", festName, 5);
//         addRow("Participation Type:", participationType, 6);
//         addRow("Contact Number:", contact, 7);
//         addRow("Email:", email, 8);

//         formGrid.add(agreeCheck, 0, 9, 2, 1);

//         // Back button as arrow image
//         backButton.setText("BACK");
//         backButton.setStyle("-fx-background-color: #DCD3FF; -fx-text-fill: #7F3DFF; -fx-font-weight: bold; -fx-background-radius: 20;");

//         // Buttons layout
//         submitBtn.setStyle("-fx-background-color: #7F3DFF; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20;");
//         HBox btnBox = new HBox(15, backButton, submitBtn);
//         btnBox.setAlignment(Pos.CENTER);
//         btnBox.setPadding(new Insets(20, 0, 0, 0));
//         formGrid.add(btnBox, 0, 11, 2, 1);

//         mainContainer = new VBox(formGrid);
//         mainContainer.setAlignment(Pos.CENTER);

//         scrollPane = new ScrollPane(mainContainer);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setStyle("-fx-background: #EFEAFE;");
//     }

//     private void addRow(String labelText, javafx.scene.Node field, int row) {
//         Label label = new Label(labelText);
//         label.setStyle("-fx-text-fill: #3F3F3F; -fx-font-size: 13px;");
//         field.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #DAD2FF; -fx-border-radius: 6; -fx-background-radius: 6;");
//         formGrid.add(label, 0, row);
//         formGrid.add(field, 1, row);
//     }

//     public Scene getScene() {
//         return new Scene(scrollPane, 1560, 790);
//     }

//     // Getter methods for Controller access
//     public TextField getNameField() { return nameField; }
//     public TextField getCollegeId() { return collegeId; }
//     public ComboBox<String> getDepartment() { return department; }
//     public ComboBox<String> getYear() { return year; }
//     public ComboBox<String> getFestName() { return festName; }
//     public ComboBox<String> getParticipationType() { return participationType; }
//     public TextField getContact() { return contact; }
//     public TextField getEmail() { return email; }
//     public CheckBox getAgreeCheck() { return agreeCheck; }
//     public Button getSubmitBtn() { return submitBtn; }
//     public Button getBackButton() { return backButton; }
// }
package com.hostelvista.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FestFormView {

    private GridPane formGrid;
    private VBox mainContainer;
    private ScrollPane scrollPane;
    

    private Stage stage;

    // Controls
    private TextField nameField = new TextField();
    private TextField collegeId = new TextField();
    private ComboBox<String> department = new ComboBox<>();
    private ComboBox<String> year = new ComboBox<>();
    private ComboBox<String> festName = new ComboBox<>();
    private ComboBox<String> participationType = new ComboBox<>();
    private TextField contact = new TextField();
    private TextField email = new TextField();
    private CheckBox agreeCheck = new CheckBox("I agree to follow the rules and instructions for the fest.");
    private Button submitBtn = new Button("REGISTER");
    private Button backButton = new Button("BACK");



    public FestFormView(Stage stage) {
        this.stage = stage;

        formGrid = new GridPane();
        formGrid.setPadding(new Insets(30));
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setMaxWidth(600);
        formGrid.setStyle("-fx-background-color: #EFEAFE; -fx-border-color: #DAD2FF; -fx-border-radius: 15; -fx-background-radius: 15;");

        // Fancy title
        Label titleLabel = new Label("STUDENT FEST REGISTRATION");
        titleLabel.setFont(Font.font("Arial", 24));
        titleLabel.setStyle("-fx-text-fill: #ffffffff; -fx-font-weight: bold;");
        StackPane titlePane = new StackPane(titleLabel);
        titlePane.setStyle("-fx-background-color: #c6b1edff; -fx-background-radius: 20;");
        titlePane.setPadding(new Insets(20, 40, 20, 40));
        titlePane.setMaxWidth(600);
        VBox.setMargin(titlePane, new Insets(30, 0, 30, 0));

        department.getItems().addAll("Computer", "Electronics", "Mechanical", "Civil", "Other");
        year.getItems().addAll("1st Year", "2nd Year", "3rd Year", "Final Year");
        festName.getItems().addAll("TechFest", "Cultural Fest", "Sports Meet", "Music Fest", "Other");
        participationType.getItems().addAll("Solo", "Group", "Volunteer");

        addRow("Full Name:", nameField, 0);
        addRow("College ID:", collegeId, 1);
        addRow("Department:", department, 2);
        addRow("Year:", year, 3);
        addRow("Fest Name:", festName, 4);
        addRow("Participation Type:", participationType, 5);
        addRow("Contact Number:", contact, 6);
        addRow("Email:", email, 7);

        formGrid.add(agreeCheck, 0, 8, 2, 1);
        agreeCheck.setStyle("-fx-font-size: 13px;");

        // Button styling
        backButton.setStyle("-fx-background-color: #DCD3FF; -fx-text-fill: #7F3DFF; -fx-font-weight: bold; -fx-background-radius: 20;");
        submitBtn.setStyle("-fx-background-color: #7F3DFF; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20;");

        HBox buttonBox = new HBox(20, backButton, submitBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(25, 0, 0, 0));
        formGrid.add(buttonBox, 0, 10, 2, 1);

        mainContainer = new VBox();
        mainContainer.setSpacing(10);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(20));
        mainContainer.getChildren().addAll(titlePane, formGrid);

        scrollPane = new ScrollPane(mainContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #EFEAFE;");
    }

    public Stage getStage() {
        return stage;
    }

    private void addRow(String labelText, javafx.scene.Node field, int row) {
        Label label = new Label(labelText);
        label.setStyle("-fx-text-fill: #3F3F3F; -fx-font-size: 14px; -fx-font-weight: bold;");
        field.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #DAD2FF; -fx-border-radius: 6; -fx-background-radius: 6; -fx-padding: 5;");
        field.prefWidth(300);
        formGrid.add(label, 0, row);
        formGrid.add(field, 1, row);
    }

    public Scene getScene() {
        return new Scene(scrollPane, 1560, 790);
    }

    // Getters for controller
    public TextField getNameField() { return nameField; }
    public TextField getCollegeId() { return collegeId; }
    public ComboBox<String> getDepartment() { return department; }
    public ComboBox<String> getYear() { return year; }
    public ComboBox<String> getFestName() { return festName; }
    public ComboBox<String> getParticipationType() { return participationType; }
    public TextField getContact() { return contact; }
    public TextField getEmail() { return email; }
    public CheckBox getAgreeCheck() { return agreeCheck; }
    public Button getSubmitBtn() { return submitBtn; }
    public Button getBackButton() { return backButton; }
}
