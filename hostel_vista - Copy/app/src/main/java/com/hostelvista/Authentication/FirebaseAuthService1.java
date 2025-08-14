
package com.hostelvista.Authentication;

import com.hostelvista.model.User;
import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirebaseAuthService1 {

    private static final String API_KEY = "AIzaSyB0v2pnJRXplTgh4TXIcp4Ag3ey-n558Do";

    public static boolean signInWithEmailAndPassword(User user) {
    try {
        URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", user.getEmail());
        requestBody.put("password", user.getPassword());
        requestBody.put("returnSecureToken", true);

        OutputStream os = conn.getOutputStream();
        os.write(requestBody.toString().getBytes());
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        return responseCode == 200;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}
