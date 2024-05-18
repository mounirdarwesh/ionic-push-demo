# Firebase Push Notification Java App

## Overview

This Java application demonstrates how to send push notifications using Firebase Cloud Messaging (FCM). The application initializes the Firebase app with provided credentials and sends a push notification to a specified device token.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Firebase account and Firebase project
- Firebase Admin SDK service account JSON file

## Setup

1. **Clone the repository**

   Clone the repository to your local machine:

   ```sh
   git clone git@gitlabmediit.de:mounirdarwish/push-notification-demo-java.git
   cd push-notification-demo-java
   ```

2. **Add Firebase Admin SDK JSON file**

   Download the Firebase Admin SDK JSON file from your Firebase project settings and place it in the `src/main/resources` directory.

3. **Configure the project**

   Ensure that the path to your Firebase Admin SDK JSON file is correct in the `initializeFirebaseApp` method:

   ```java
   FileInputStream serviceAccount = new FileInputStream("src/main/resources/mediit-termininfo-firebase-adminsdk-u09dy-79ce2733d1.json");
   ```

4. **Update the device token**

   Replace the placeholder device token in the `main` method with an actual device token:

   ```java
   String token = "your_device_token_here";
   ```

## Running the Application

1. **Build the project**

   Use your preferred IDE (e.g., IntelliJ IDEA, Eclipse) to build the project, or use the command line with Maven:

   ```sh
   mvn clean install
   ```

2. **Run the application**

   Run the `Main` class from your IDE or from the command line:

   ```sh
   java -cp target/firebase-push-notification-java-1.0-SNAPSHOT.jar org.example.Main
   ```

## Code Explanation

- **Initialize Firebase App**

  The `initializeFirebaseApp` method initializes the Firebase app using the provided service account credentials.

  ```java
  public static void initializeFirebaseApp() throws Exception {
      FileInputStream serviceAccount = new FileInputStream("src/main/resources/mediit-termininfo-firebase-adminsdk-u09dy-79ce2733d1.json");

      FirebaseOptions options = FirebaseOptions.builder()
              .setCredentials(GoogleCredentials.fromStream(serviceAccount))
              .build();

      FirebaseApp.initializeApp(options);
  }
  ```

- **Send Push Notification**

  The `sendPushNotification` method sends a push notification with the specified title and body to a device identified by the provided token.

  ```java
  public static void sendPushNotification(String token) throws Exception {
      AndroidConfig androidConfig = AndroidConfig.builder()
              .setPriority(AndroidConfig.Priority.HIGH)
              .setNotification(AndroidNotification.builder()
                      .setChannelId("mediIT-termininfo")
                      .build())
              .build();

      Notification notification = Notification.builder()
              .setTitle("It works")
              .setBody("Hello World!")
              .build();

      Message message = Message.builder()
              .setNotification(notification)
              .setAndroidConfig(androidConfig)
              .setToken(token)
              .build();

      String response = FirebaseMessaging.getInstance().send(message);
      System.out.println("Successfully sent message: " + response);
  }
  ```

## Notes

- Ensure that the Firebase Admin SDK JSON file is kept secure and not exposed publicly.
- Make sure the device token is valid and the corresponding app is configured to receive push notifications.
