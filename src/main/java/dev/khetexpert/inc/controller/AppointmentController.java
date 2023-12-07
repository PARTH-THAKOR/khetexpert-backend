// AppointmentController Class

package dev.khetexpert.inc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.Appointment;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    @PostMapping("/request")
    public ResponseEntity<ApiResponse> requestAppointment(@Valid @RequestBody Appointment appointment) {
        return appointmentService.requestAppointment(appointment);
    }

    @PutMapping("/accept")
    public ResponseEntity<ApiResponse> acceptAppointment(@RequestBody Appointment appointment) throws ExecutionException, InterruptedException {
        return appointmentService.acceptAppointment(appointment);
    }

    @PutMapping("/reject")
    public ResponseEntity<ApiResponse> rejectAppointment(@RequestBody Appointment appointment) throws ExecutionException, InterruptedException {
        return appointmentService.rejectAppointment(appointment);
    }

    @PutMapping("/postpone")
    public ResponseEntity<ApiResponse> postponeAppointment(@RequestBody Appointment appointment) throws ExecutionException, InterruptedException {
        return appointmentService.postponeAppointment(appointment);
    }

}
