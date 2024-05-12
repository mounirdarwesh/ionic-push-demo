package org.example;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void initializeFirebaseApp() throws Exception {
        FileInputStream serviceAccount = new FileInputStream("src/main/java/org/example/mediit-termininfo-firebase-adminsdk-u09dy-79ce2733d1.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    public static void sendPushNotification(String token) throws Exception {
        // Define Android-specific notification options
        AndroidConfig androidConfig = AndroidConfig.builder()
                .setPriority(AndroidConfig.Priority.HIGH) // Set high priority for heads-up notification
                .setNotification(AndroidNotification.builder()
                        .setChannelId("test_channel") // Ensure this channel ID is created in your Android app
                        .setTitle("Optional: Override the general title") // Optionally override the title
                        .setBody("Optional: Override the general body") // Optionally override the body
                        .build())
                .build();

        // Create a notification message
        Notification notification = Notification.builder()
                .setTitle("It works")
                .setBody("Hello World!")
                .build();

        // Create and build the message with specified AndroidConfig
        Message message = Message.builder()
//                .setNotification(notification)
                .setAndroidConfig(androidConfig)
                .setToken(token) // Token should be dynamically set based on the device
                .build();

        // Send the message
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);
    }

    public static void main(String[] args) {
        try {
            initializeFirebaseApp();
            sendPushNotification("f9BJWlvgQf6spn4VGeAkaK:APA91bFfv1s5bO3x7a4aCZGCilsFsX5iN8Bx391xU3GVZg93gsxAcVeznbyEmefOA614RCgYcApltJeUricGWxKr_OEXhl4fIk3WrNzXM9p2xYYr8ezTO2IS_dtrI_Zn9AG0Nb8G7Yts"); // Replace with the actual device token
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}