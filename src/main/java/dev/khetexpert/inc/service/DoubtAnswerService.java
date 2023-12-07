// DoubtAnswerService Class

package dev.khetexpert.inc.service;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import dev.khetexpert.inc.entity.AskToExpert;
import dev.khetexpert.inc.entity.DoubtAnswers;
import dev.khetexpert.inc.exception.KhetExpertError;
import dev.khetexpert.inc.objects.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DoubtAnswerService {

    public static final String COLLECTION = "doubts";
    public static final String INTERNALCOLLECTION = "doubt_solutions";

    public ResponseEntity<ApiResponse> writeDoubtAnswer(String docId, String state, DoubtAnswers doubtAnswer) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = FirestoreClient.getFirestore().collection(COLLECTION).document(state).collection(INTERNALCOLLECTION).document(docId).get().get();
        if (snapshot.exists()) {
            AskToExpert askToExpert = snapshot.toObject(AskToExpert.class);
            if (askToExpert != null) {
                List<DoubtAnswers> doubtAnswers = askToExpert.getDoubtAnswersList();
                doubtAnswers.add(0, doubtAnswer);
                askToExpert.setDoubtAnswersList(doubtAnswers);
                FirestoreClient.getFirestore().collection(COLLECTION).document(state).collection(INTERNALCOLLECTION).document(docId).set(askToExpert);
                return new ResponseEntity<>(ApiResponse.builder().success(true).message("Doubt Answer Posted").build(), HttpStatus.CREATED);
            } else {
                throw new KhetExpertError("Object not found");
            }
        } else {
            throw new KhetExpertError("Document not found");
        }
    }

}
