package org.example;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.InputStream;

public class FireBaseBaglama {

    private static Firestore db;

    public static Firestore getDb() {
        if (db == null) {
            try {
                InputStream serviceAccount =
                        FireBaseBaglama.class.getClassLoader()
                                .getResourceAsStream("serviceAccountKey.json");

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                if (FirebaseApp.getApps().isEmpty()) {
                    FirebaseApp.initializeApp(options);
                }

                db = FirestoreClient.getFirestore();
                System.out.println("Firebase bağlantısı kuruldu.");

            } catch (Exception e) {
                System.out.println("Firebase hatası: " + e.getMessage());
            }
        }
        return db;
    }
}