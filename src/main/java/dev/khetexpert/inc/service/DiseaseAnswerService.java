// DiseaseAnswerService Class

package dev.khetexpert.inc.service;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import dev.khetexpert.inc.entity.DiseaseAnswers;
import dev.khetexpert.inc.entity.FarmerDiseaseDetection;
import dev.khetexpert.inc.exception.KhetExpertError;
import dev.khetexpert.inc.objects.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DiseaseAnswerService {

    public static final String COLLECTION = "disease";
    public static final String INTERNALCOLLECTION = "disease_detection";

    public ResponseEntity<ApiResponse> writeDiseaseAnswer(String docId, String state, DiseaseAnswers diseaseAnswer) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = FirestoreClient.getFirestore().collection(COLLECTION).document(state).collection(INTERNALCOLLECTION).document(docId).get().get();
        if (snapshot.exists()) {
            FarmerDiseaseDetection farmerDiseaseDetection = snapshot.toObject(FarmerDiseaseDetection.class);
            if (farmerDiseaseDetection != null) {
                List<DiseaseAnswers> diseaseAnswers = farmerDiseaseDetection.getDiseaseAnswersList();
                diseaseAnswers.add(0, diseaseAnswer);
                farmerDiseaseDetection.setDiseaseAnswersList(diseaseAnswers);
                FirestoreClient.getFirestore().collection(COLLECTION).document(state).collection(INTERNALCOLLECTION).document(docId).set(farmerDiseaseDetection);
                return new ResponseEntity<>(ApiResponse.builder().success(true).message("Disease Answer Posted").build(), HttpStatus.CREATED);
            } else {
                throw new KhetExpertError("Object not found");
            }
        } else {
            throw new KhetExpertError("Document not found");
        }
    }

}
