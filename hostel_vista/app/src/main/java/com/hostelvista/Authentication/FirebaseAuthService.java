
// // package com.hostelvista.Authentication;

// // import com.hostelvista.model.User;
// // import org.json.JSONObject;
// // import java.io.OutputStream;
// // import java.net.HttpURLConnection;
// // import java.net.URL;

// // public class FirebaseAuthService {

// //     private static final String API_KEY = "AIzaSyB0v2pnJRXplTgh4TXIcp4Ag3ey-n558Do";

// //     public static boolean signUpWithEmailAndPassword(User user) {
// //     try {
// //         URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY);
// //         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
// //         conn.setRequestMethod("POST");
// //         conn.setRequestProperty("Content-Type", "application/json");
// //         conn.setDoOutput(true);

// //         JSONObject requestBody = new JSONObject();
// //         requestBody.put("email", user.getEmail());
// //         requestBody.put("password", user.getPassword());
// //         requestBody.put("returnSecureToken", true);

// //         OutputStream os = conn.getOutputStream();
// //         os.write(requestBody.toString().getBytes());
// //         os.flush();
// //         os.close();

// //         int responseCode = conn.getResponseCode();
// //         return responseCode == 200;

// //     } catch (Exception e) {
// //         e.printStackTrace();
// //         return false;
// //     }
// // }

// // }
// package com.hostelvista.Authentication;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.cloud.firestore.Firestore;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import com.google.firebase.auth.FirebaseAuth;
// import com.google.firebase.auth.UserRecord;
// import com.google.firebase.cloud.FirestoreClient;
// import com.hostelvista.model.User;

// import java.io.FileInputStream;
// import java.util.HashMap;
// import java.util.Map;

// public class FirebaseAuthService {

//     public static boolean signUpWithEmailAndPassword(User user, String role) {
//         try {
//             FirebaseAuth auth = FirebaseAuth.getInstance();

//             UserRecord.CreateRequest request = new UserRecord.CreateRequest()
//                     .setEmail(user.getEmail())
//                     .setPassword(user.getPassword());

//             UserRecord userRecord = auth.createUser(request);

//             if (userRecord != null) {
//                 Firestore db = FirestoreClient.getFirestore();
//                 Map<String, Object> userData = new HashMap<>();
//                 userData.put("email", user.getEmail());
//                 userData.put("role", role);  // âœ… store Student/Warden

//                 db.collection("users").document(user.getEmail()).set(userData);
//                 return true;
//             }
//         } catch (Exception e) {
//              System.out.println("🔥 Signup Exception: " + e.getMessage());
//             e.printStackTrace();
//         }
//         return false;
//     }
// }
package com.hostelvista.Authentication;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.hostelvista.model.User;

import java.util.HashMap;
import java.util.Map;

public class FirebaseAuthService {

    public static boolean signUpWithEmailAndPassword(User user, String role) {
        try {
            FirebaseAuth auth = FirebaseAuth.getInstance();

            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword());

            UserRecord userRecord = auth.createUser(request); // ✅ UID is here

            if (userRecord != null) {
                Firestore db = FirestoreClient.getFirestore();

                Map<String, Object> userData = new HashMap<>();
                userData.put("email", user.getEmail());
                userData.put("role", role);  // ✅ Student or Warden

                // ✅ Save by UID, not email
                db.collection("users").document(userRecord.getUid()).set(userData);

                return true;
            }
        } catch (Exception e) {
            System.out.println("🔥 Signup Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
