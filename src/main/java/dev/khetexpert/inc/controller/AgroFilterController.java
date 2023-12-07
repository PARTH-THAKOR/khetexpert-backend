// AgroFilterController

package dev.khetexpert.inc.controller;

import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.AgroFilter;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.service.AgroFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/filter")
public class AgroFilterController {

    private AgroFilterService agroFilterService;

    @GetMapping
    public ResponseEntity<ApiResponse> agroFilter(@RequestHeader("prompt") String prompt) {
        return agroFilterService.filter(prompt);
    }

    @PostMapping("/agriWords")
    public ResponseEntity<ApiResponse> addAgriWords(@RequestBody AgroFilter agroFilter) {
        return agroFilterService.addAgriWords(agroFilter);
    }

    @PostMapping("/disease")
    public ResponseEntity<ApiResponse> addDisease(@RequestBody AgroFilter agroFilter) {
        return agroFilterService.addDisease(agroFilter);
    }

    @PostMapping("/vegetables")
    public ResponseEntity<ApiResponse> addVegetables(@RequestBody AgroFilter agroFilter) {
        return agroFilterService.addVegetables(agroFilter);
    }

    @PostMapping("/fertilizers")
    public ResponseEntity<ApiResponse> addFertilizers(@RequestBody AgroFilter agroFilter) {
        return agroFilterService.addFertilizers(agroFilter);
    }

    @PostMapping("/fruits")
    public ResponseEntity<ApiResponse> addFruits(@RequestBody AgroFilter agroFilter) {
        return agroFilterService.addFruits(agroFilter);
    }

}
