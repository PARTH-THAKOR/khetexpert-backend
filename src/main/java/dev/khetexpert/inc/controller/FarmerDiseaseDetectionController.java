// FarmerDiseaseDetectionController Class

package dev.khetexpert.inc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.FarmerDiseaseDetection;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.service.FarmerDiseaseDetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/disease")
public class FarmerDiseaseDetectionController {

    private FarmerDiseaseDetectionService farmerDiseaseDetectionService;

    @PostMapping("/ask")
    public ResponseEntity<ApiResponse> askDisease(@Valid @RequestBody FarmerDiseaseDetection diseaseDetection) {
        return farmerDiseaseDetectionService.askDisease(diseaseDetection);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateDisease(@RequestBody FarmerDiseaseDetection diseaseDetection) throws ExecutionException, InterruptedException {
        return farmerDiseaseDetectionService.updateDisease(diseaseDetection);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteDisease(@RequestBody FarmerDiseaseDetection diseaseDetection) throws ExecutionException, InterruptedException {
        return farmerDiseaseDetectionService.deleteDisease(diseaseDetection);
    }

}
