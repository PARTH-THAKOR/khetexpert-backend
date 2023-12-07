// FarmerProfileController Class

package dev.khetexpert.inc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.FarmerProfile;
import dev.khetexpert.inc.service.FarmerProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/farmer")
public class FarmerProfileController {

    private FarmerProfileService farmerProfileService;

    @GetMapping("/get")
    public ResponseEntity<FarmerProfile> getFarmer(@RequestHeader("phoneNumber") String phoneNumber) throws ExecutionException, InterruptedException {
        return farmerProfileService.getFarmer(phoneNumber);
    }

    @PostMapping("/create")
    public ResponseEntity<FarmerProfile> createFarmer(@Valid @RequestBody FarmerProfile farmerProfile) {
        return farmerProfileService.createFarmer(farmerProfile);
    }

    @PutMapping("/update")
    public ResponseEntity<FarmerProfile> updateFarmer(@Valid @RequestBody FarmerProfile farmerProfile) {
        return farmerProfileService.updateFarmer(farmerProfile);
    }

}
