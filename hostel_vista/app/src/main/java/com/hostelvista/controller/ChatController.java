package com.hostelvista.controller;

import com.hostelvista.view.ChatView;

public class ChatController {
    private final ChatView view;

    public ChatController(ChatView view) {
        this.view = view;
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        view.getQuestionComboBox().setOnAction(e -> {
            String selected = view.getQuestionComboBox().getValue();
            if (selected != null) {
                view.getChatArea().appendText("👤 You: " + selected + "\n");

                if ("Other".equalsIgnoreCase(selected)) {
                    view.getChatArea().appendText("🤖 Bot: For any other queries, please contact hostel enquiry 📞 +91-9876543210\n");
                } else {
                    view.getChatArea().appendText("🤖 Bot: " + getBotResponse(selected.toLowerCase()) + "\n");
                }
            }
        });

        view.getCloseBtn().setOnAction(e -> view.getChatStage().close());
    }

    private String getBotResponse(String q) {
        if (q.contains("warden")) return "The warden is in Room 101, available 10AM - 5PM.";
        if (q.contains("wifi")) return "The WiFi password is 'Hostel@123'.";
        if (q.contains("food") || q.contains("mess")) return "Mess timing: Breakfast (7–9AM), Lunch (12–2PM), Dinner (7–9PM).";
        if (q.contains("gate") || q.contains("entry")) return "The main gate closes at 9PM.";
        if (q.contains("cleaning") || q.contains("cleaner")) return "Rooms are cleaned every Monday and Thursday.";
        if (q.contains("room change")) return "Room changes allowed on Saturday with warden approval.";
        if (q.contains("electricity")) return "Please report to hostel office (Ground Floor).";
        if (q.contains("security")) return "24/7 guards and CCTV cameras are active.";
        return "Sorry, I didn’t understand. Please contact hostel enquiry 📞 +91-9876543210";
    }
}

