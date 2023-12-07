// FarmerDiseaseDetectionService Class

package dev.khetexpert.inc.service;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import dev.khetexpert.inc.entity.FarmerDiseaseDetection;
import dev.khetexpert.inc.exception.KhetExpertError;
import dev.khetexpert.inc.objects.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class FarmerDiseaseDetectionService {

    public static final String COLLECTION = "disease";
    public static final String INTERNALCOLLECTION = "disease_detection";

    public ResponseEntity<ApiResponse> askDisease(FarmerDiseaseDetection farmerDiseaseDetection) {
        long docId = new Date().getTime();
        farmerDiseaseDetection.setDocId(docId + farmerDiseaseDetection.getPhoneNumber());
        FirestoreClient.getFirestore().collection(COLLECTION).document(farmerDiseaseDetection.getState()).collection(INTERNALCOLLECTION).document(docId + farmerDiseaseDetection.getPhoneNumber()).set(farmerDiseaseDetection);
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("Disease Post Posted").build(), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> updateDisease(FarmerDiseaseDetection farmerDiseaseDetection) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = FirestoreClient.getFirestore().collection(COLLECTION).document(farmerDiseaseDetection.getState()).collection(INTERNALCOLLECTION).document(farmerDiseaseDetection.getDocId()).get().get();
        if (snapshot.exists()) {
            FarmerDiseaseDetection farmerDiseaseDetection1 = snapshot.toObject(FarmerDiseaseDetection.class);
            if (farmerDiseaseDetection1 != null) {
                farmerDiseaseDetection1.setQuestion(farmerDiseaseDetection.getQuestion());
                FirestoreClient.getFirestore().collection(COLLECTION).document(farmerDiseaseDetection1.getState()).collection(INTERNALCOLLECTION).document(farmerDiseaseDetection1.getDocId()).set(farmerDiseaseDetection1);
                return new ResponseEntity<>(ApiResponse.builder().success(true).message("Disease Post Updated").build(), HttpStatus.CREATED);
            } else {
                throw new KhetExpertError("Object Not Found");
            }
        } else {
            throw new KhetExpertError("Post Not Found");
        }
    }

    public ResponseEntity<ApiResponse> deleteDisease(FarmerDiseaseDetection farmerDiseaseDetection) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = FirestoreClient.getFirestore().collection(COLLECTION).document(farmerDiseaseDetection.getState()).collection(INTERNALCOLLECTION).document(farmerDiseaseDetection.getDocId()).get().get();
        if (snapshot.exists()) {
            FirestoreClient.getFirestore().collection(COLLECTION).document(farmerDiseaseDetection.getState()).collection(INTERNALCOLLECTION).document(farmerDiseaseDetection.getDocId()).delete();
            return new ResponseEntity<>(ApiResponse.builder().success(true).message("Disease Post Deleted").build(), HttpStatus.CREATED);
        } else {
            throw new KhetExpertError("disease post not found");
        }
    }

}
