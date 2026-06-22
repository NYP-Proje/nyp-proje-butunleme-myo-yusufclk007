package org.example;

import com.google.cloud.firestore.*;

import java.util.ArrayList;
import java.util.List;

public class KimlikIslemleri {

    private static final String KOLEKSIYON = "kimlikler";

    public static List<Kimlik> hepsiniGetir() {
        List<Kimlik> liste = new ArrayList<>();
        try {
            Firestore db = FireBaseBaglama.getDb();
            List<QueryDocumentSnapshot> docs = db
                    .collection(KOLEKSIYON).get().get().getDocuments();

            for (QueryDocumentSnapshot doc : docs) {
                Kimlik k = doc.toObject(Kimlik.class);
                k.setId(doc.getId());
                liste.add(k);
            }
        } catch (Exception e) {
            System.out.println("Listeleme hatası: " + e.getMessage());
        }
        return liste;
    }

    public static void ekle(Kimlik k) {
        try {
            Firestore db = FireBaseBaglama.getDb();
            db.collection(KOLEKSIYON).add(k).get();
            System.out.println("Kimlik eklendi.");
        } catch (Exception e) {
            System.out.println("Ekleme hatası: " + e.getMessage());
        }
    }

    public static void sil(String id) {
        try {
            Firestore db = FireBaseBaglama.getDb();
            db.collection(KOLEKSIYON).document(id).delete().get();
            System.out.println("Kimlik silindi.");
        } catch (Exception e) {
            System.out.println("Silme hatası: " + e.getMessage());
        }
    }
}
