// FarmerProfileService Class

package dev.khetexpert.inc.service;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import dev.khetexpert.inc.entity.FarmerProfile;
import dev.khetexpert.inc.exception.KhetExpertError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FarmerProfileService {

    public static final String COLLECTION = "farmers";

    public ResponseEntity<FarmerProfile> getFarmer(String phoneNumber) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = FirestoreClient.getFirestore().collection(COLLECTION).document(phoneNumber).get().get();
        if (snapshot.exists()) {
            FarmerProfile farmerProfile = snapshot.toObject(FarmerProfile.class);
            if (farmerProfile != null) {
                return new ResponseEntity<>(farmerProfile, HttpStatus.OK);
            } else {
                throw new KhetExpertError("Farmer Not Found");
            }
        } else {
            throw new KhetExpertError("Farmer Not Found");
        }
    }

    public ResponseEntity<FarmerProfile> createFarmer(FarmerProfile farmerProfile) {
        FirestoreClient.getFirestore().collection(COLLECTION).document(farmerProfile.getPhoneNumber()).set(farmerProfile);
        return new ResponseEntity<>(farmerProfile, HttpStatus.CREATED);
    }

    public ResponseEntity<FarmerProfile> updateFarmer(FarmerProfile farmerProfile) {
        FirestoreClient.getFirestore().collection(COLLECTION).document(farmerProfile.getPhoneNumber()).set(farmerProfile);
        return new ResponseEntity<>(farmerProfile, HttpStatus.CREATED);
    }

}
