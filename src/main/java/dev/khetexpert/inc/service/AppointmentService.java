// AppointmentService Class

package dev.khetexpert.inc.service;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import dev.khetexpert.inc.entity.Appointment;
import dev.khetexpert.inc.exception.KhetExpertError;
import dev.khetexpert.inc.objects.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class AppointmentService {

    public static final String COLLECTION = "appointment";

    public ResponseEntity<ApiResponse> requestAppointment(Appointment appointment) {
        long docId = new Date().getTime();
        appointment.setMessage("Requested for Appointment");
        appointment.setDocId(docId + appointment.getExpertEmailId() + appointment.getFarmerMobileNumber());
        FirestoreClient.getFirestore().collection(COLLECTION).document(docId + appointment.getExpertEmailId() + appointment.getFarmerMobileNumber()).set(appointment);
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("Requested for Appointment").build(), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> acceptAppointment(Appointment appointment) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = FirestoreClient.getFirestore().collection(COLLECTION).document(appointment.getDocId()).get().get();
        if (snapshot.exists()) {
            Appointment appointment1 = snapshot.toObject(Appointment.class);
            if (appointment1 != null) {
                appointment1.setMessage("Accepted Today At : " + appointment.getHour() + ":" + appointment.getMinutes() + " " + appointment.getAmpm());
                appointment1.setAccepted(true);
                appointment1.setHour(appointment.getHour());
                appointment1.setMinutes(appointment.getMinutes());
                appointment1.setAmpm(appointment.getAmpm());
                FirestoreClient.getFirestore().collection(COLLECTION).document(appointment.getDocId()).set(appointment1);
                return new ResponseEntity<>(ApiResponse.builder().success(true).message("Requested ACCEPTED").build(), HttpStatus.OK);
            } else {
                throw new KhetExpertError("Object Parsing Error");
            }
        } else {
            throw new KhetExpertError("Appointment Not found");
        }
    }

    public ResponseEntity<ApiResponse> rejectAppointment(Appointment appointment) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = FirestoreClient.getFirestore().collection(COLLECTION).document(appointment.getDocId()).get().get();
        if (snapshot.exists()) {
            Appointment appointment1 = snapshot.toObject(Appointment.class);
            if (appointment1 != null) {
                appointment1.setRejected(true);
                appointment1.setMessage("Rejected due to some issues");
                FirestoreClient.getFirestore().collection(COLLECTION).document(appointment.getDocId()).set(appointment1);
                return new ResponseEntity<>(ApiResponse.builder().success(true).message("Requested REJECTED").build(), HttpStatus.OK);
            } else {
                throw new KhetExpertError("Object Parsing Error");
            }
        } else {
            throw new KhetExpertError("Appointment Not found");
        }
    }

    public ResponseEntity<ApiResponse> postponeAppointment(Appointment appointment) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = FirestoreClient.getFirestore().collection(COLLECTION).document(appointment.getDocId()).get().get();
        if (snapshot.exists()) {
            Appointment appointment1 = snapshot.toObject(Appointment.class);
            if (appointment1 != null) {
                appointment1.setAccepted(false);
                appointment1.setMessage("Postpone due to some issues");
                FirestoreClient.getFirestore().collection(COLLECTION).document(appointment.getDocId()).set(appointment1);
                return new ResponseEntity<>(ApiResponse.builder().success(true).message("Requested POSTPONED").build(), HttpStatus.OK);
            } else {
                throw new KhetExpertError("Object Parsing Error");
            }
        } else {
            throw new KhetExpertError("Appointment Not found");
        }
    }

}
