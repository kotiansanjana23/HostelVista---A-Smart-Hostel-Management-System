package com.hostelvista.view;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HostelAttendanceQRGenerator {

    private static final int SERVER_PORT = 8080;
    private static HttpServer server;

    /**
     * Pass the primaryStage and the dashboard Scene to return back when back button pressed.
     */
    public Scene getScene(Stage primaryStage, Scene dashboardScene) {
        primaryStage.setTitle("Hostel Attendance QR Generator");

        Label titleLabel = new Label("Attendance QR Code");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.web("#5E35B1"));

        Button generateQRButton = new Button("Generate Today's Attendance QR");
        generateQRButton.setStyle(
            "-fx-background-color: #7E57C2; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: bold; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 10 20; " +
            "-fx-font-size: 14px;" +
            "-fx-cursor: hand;"
        );

        ImageView qrImageView = new ImageView();
        qrImageView.setFitWidth(300);
        qrImageView.setFitHeight(300);

        generateQRButton.setOnAction(e -> {
            try {
                String localIp = InetAddress.getLocalHost().getHostAddress();
                String attendanceUrl = "http://" + localIp + ":" + SERVER_PORT + "/attendance?date=" + LocalDate.now();
                System.out.println("Generating QR for URL: " + attendanceUrl);

                String encodedUrl = URLEncoder.encode(attendanceUrl, StandardCharsets.UTF_8);
                String apiUrl = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + encodedUrl;

                InputStream inputStream = new URL(apiUrl).openStream();
                Image qrImage = new Image(inputStream);
                qrImageView.setImage(qrImage);

                // Start server once QR is generated
                startServer();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button backButton = new Button("Back to Dashboard");
        backButton.setStyle(
            "-fx-background-color: #ccc; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 8 16; " +
            "-fx-background-radius: 15; " +
            "-fx-cursor: hand;"
        );
        backButton.setOnAction(e -> {
            primaryStage.setScene(dashboardScene);
        });

        VBox layout = new VBox(20, titleLabel, generateQRButton, qrImageView, backButton);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center; -fx-background-color: #F8F7FF;");
        layout.setAlignment(Pos.CENTER);

        primaryStage.setOnCloseRequest(event -> {
            if (server != null) {
                server.stop(0);
                System.out.println("Attendance server stopped.");
            }
        });

        return new Scene(layout, 1560, 790);
    }

    public static void startServer() throws Exception {
        if (server == null) {
            server = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
            server.createContext("/attendance", new AttendanceHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("Attendance server started on port " + SERVER_PORT);
        }
    }

    static class AttendanceHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            String date = params.getOrDefault("date", "N/A");

            String htmlResponse = "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Attendance Marked</title>"
                + "<style>"
                + "  body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; font-family: Arial, sans-serif; background-color: #EFEAFE; color: #5E35B1; text-align: center; }"
                + "  .container { background-color: white; padding: 40px; border-radius: 20px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }"
                + "  .checkmark { width: 80px; height: 80px; border-radius: 50%; display: block; stroke-width: 3; stroke: #4CAF50; stroke-miterlimit: 10; margin: 10px auto; box-shadow: inset 0px 0px 0px #4CAF50; animation: fill .4s ease-in-out .4s forwards, scale .3s ease-in-out .9s both; }"
                + "  .checkmark__circle { stroke-dasharray: 166; stroke-dashoffset: 166; stroke-width: 2; stroke-miterlimit: 10; stroke: #7ac142; fill: none; animation: stroke .6s cubic-bezier(0.65, 0, 0.45, 1) forwards; }"
                + "  .checkmark__check { transform-origin: 50% 50%; stroke-dasharray: 48; stroke-dashoffset: 48; animation: stroke .3s cubic-bezier(0.65, 0, 0.45, 1) .8s forwards; }"
                + "  @keyframes stroke { 100% { stroke-dashoffset: 0; } }"
                + "  @keyframes scale { 0%, 100% { transform: none; } 50% { transform: scale3d(1.1, 1.1, 1); } }"
                + "  @keyframes fill { 100% { box-shadow: inset 0px 0px 0px 50px #4CAF50; } }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "  <div class='container'>"
                + "    <svg class='checkmark' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 52 52'><circle class='checkmark_circle' cx='26' cy='26' r='25' fill='none'/><path class='checkmark_check' fill='none' d='M14.1 27.2l7.1 7.2 16.7-16.8'/></svg>"
                + "    <h2>Attendance Marked Successfully!</h2>"
                + "    <p>Date: " + date + "</p>"
                + "  </div>"
                + "</body>"
                + "</html>";

            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, htmlResponse.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(htmlResponse.getBytes(StandardCharsets.UTF_8));
            }
        }

        private Map<String, String> queryToMap(String query) {
            Map<String, String> result = new HashMap<>();
            if (query == null) return result;
            for (String param : query.split("&")) {
                String[] entry = param.split("=");
                if (entry.length > 1) {
                    result.put(entry[0], entry[1]);
                } else {
                    result.put(entry[0], "");
                }
            }
            return result;
        }
    }
}
