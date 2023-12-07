// AskToExpertService Class

package dev.khetexpert.inc.service;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import dev.khetexpert.inc.entity.AskToExpert;
import dev.khetexpert.inc.exception.KhetExpertError;
import dev.khetexpert.inc.objects.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class AskToExpertService {

    public static final String COLLECTION = "doubts";
    public static final String INTERNALCOLLECTION = "doubt_solutions";

    public ResponseEntity<ApiResponse> askDoubt(AskToExpert askToExpert) {
        long docId = new Date().getTime();
        askToExpert.setDocId(docId + askToExpert.getPhoneNumber());
        FirestoreClient.getFirestore().collection(COLLECTION).document(askToExpert.getState()).collection(INTERNALCOLLECTION).document(docId + askToExpert.getPhoneNumber()).set(askToExpert);
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("Doubt Post Posted").build(), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> deleteDoubt(AskToExpert askToExpert) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = FirestoreClient.getFirestore().collection(COLLECTION).document(askToExpert.getState()).collection(INTERNALCOLLECTION).document(askToExpert.getDocId()).get().get();
        if (snapshot.exists()) {
            FirestoreClient.getFirestore().collection(COLLECTION).document(askToExpert.getState()).collection(INTERNALCOLLECTION).document(askToExpert.getDocId()).delete();
            return new ResponseEntity<>(ApiResponse.builder().success(true).message("Doubt Post Deleted").build(), HttpStatus.CREATED);
        } else {
            throw new KhetExpertError("doubt post not found");
        }
    }

}
