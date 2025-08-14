package com.hostelvista;

import com.hostelvista.Authentication.FirebaseInitializer;
//import com.hostelvista.controller.MainLayoutController;
import com.hostelvista.view.SigninView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
       FirebaseInitializer.initialize();


        SigninView signinView = new SigninView(primaryStage);
        primaryStage.setScene(signinView.getScene());
        primaryStage.setTitle("HostelVista");
         primaryStage.getIcons().add(
            new Image(getClass().getResourceAsStream("/Assets/Images/hostelvista.jpg"))
        );
        primaryStage.show();
    }
    // Webcam webcam = Webcam.getDefault();
    //     // webcam.setViewSize(new Dimension(640, 480));
    //     webcam.open();

    //     JFrame window = new JFrame("QR Scanner");
    //     JLabel imageLabel = new JLabel();
    //     window.add(imageLabel);
    //     window.setSize(640, 480);
    //     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     window.setVisible(true);

    //     new Thread(() -> {
    //         while (true) {
    //             BufferedImage image = webcam.getImage();
    //             if (image != null) {
    //                 imageLabel.setIcon(new ImageIcon(image));
    //                 String result = decodeQRCode(image);
    //                 if (result != null) {
    //                     System.out.println("QR Code: " + result);
    //                     JOptionPane.showMessageDialog(window, "QR Code: " + result);
    //                     break;
    //                 }
    //             }
    //         }
    //     }).start();
    // }

    // private static String decodeQRCode(BufferedImage image) {
    //     try {
    //         LuminanceSource source = new BufferedImageLuminanceSource(image);
    //         BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
    //         Result result = new MultiFormatReader().decode(bitmap);
    //         return result.getText();
    //     } catch (NotFoundException e) {
    //         return null; // No QR found
    //     }
    // }
    public static void main(String[] args) {
        launch(args);
    }

    
}