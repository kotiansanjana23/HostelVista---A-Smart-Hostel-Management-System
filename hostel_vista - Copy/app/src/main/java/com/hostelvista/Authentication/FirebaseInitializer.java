package com.hostelvista.Authentication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.InputStream;

public class FirebaseInitializer {
    private static Firestore db;

    public static void initialize() {
        // Prevent multiple initializations
        if (!FirebaseApp.getApps().isEmpty()) {
            db = FirestoreClient.getFirestore();
            return;
        }

        try {
            // Load the service account key from resources
            InputStream serviceAccount = FirebaseInitializer.class.getResourceAsStream("/serviceAccountKey.json");

            if (serviceAccount == null) {
                System.err.println("❌ serviceAccountKey.json not found in resources folder.");
                return;
            }

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();

            System.out.println("✅ Firebase initialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Firebase initialization failed.");
        }
    }

    public static Firestore getDB() {
        if (db == null) {
            System.err.println("⚠ Firebase not initialized. Call initialize() first.");
        }
        return db;
}
}