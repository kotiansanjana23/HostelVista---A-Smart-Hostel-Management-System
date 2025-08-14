package com.hostelvista.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ChatView {
    private Stage chatStage;
    private TextArea chatArea;
    private ComboBox<String> questionComboBox;
    private Button closeBtn;

    public ChatView() {
        chatStage = new Stage();
        chatStage.setTitle("Hostel ChatBot 🤖");

        // 🟣 Chat area styling
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);
        chatArea.setPrefHeight(300);
        chatArea.setStyle("-fx-background-color: #f4f0ff; -fx-border-color: #7f3fe0; -fx-border-radius: 8; -fx-font-size: 14;");
        chatArea.appendText("🤖 Bot: Hello! Please select a question below.\n");

        // 📦 Dropdown styling
        questionComboBox = new ComboBox<>();
        questionComboBox.getItems().addAll(
                "What are the mess timings?",
                "What is the WiFi password?",
                "When does the main gate close?",
                "How can I change my room?",
                "When does cleaning happen?",
                "Where is the warden office?",
                "How to report electricity issues?",
                "What are the security facilities?",
                "Other"
        );
        questionComboBox.setPromptText("Select a question");
        questionComboBox.setPrefWidth(450);
        questionComboBox.setStyle("-fx-background-radius: 10; -fx-border-color: #7f3fe0; -fx-padding: 5;");

        // ❌ Close button styling
        closeBtn = new Button("Close");
        closeBtn.setStyle("-fx-background-color: #7f3fe0; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-weight: bold;");
        closeBtn.setPrefWidth(100);

        // 🤖 Optional: Avatar image
        //ImageView botAvatar = new ImageView(new Image(getClass().getResourceAsStream("/Assets/Images/chatbot.jpg")));
        //botAvatar.setFitHeight(60);
        //botAvatar.setFitWidth(60);

        HBox header = new HBox(10, new Label("🤖Hostel Assistant Bot"));
        header.setAlignment(Pos.CENTER_LEFT);

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 4);");
        layout.getChildren().addAll(header, chatArea, questionComboBox, closeBtn);

        BorderPane root = new BorderPane(layout);
        root.setStyle("-fx-background-color: #e7dcff;");

        Scene scene = new Scene(root,500,500);
        chatStage.setScene(scene);
    }

    public Stage getChatStage() {
        return chatStage;
    }

    public TextArea getChatArea() {
        return chatArea;
    }

    public ComboBox<String> getQuestionComboBox() {
        return questionComboBox;
    }

    public Button getCloseBtn() {
        return closeBtn;
    }

    public void show() {
        chatStage.show();
    }
}
