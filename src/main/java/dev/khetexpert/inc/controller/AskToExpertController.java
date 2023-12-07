// AskToExpertController Class

package dev.khetexpert.inc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.AskToExpert;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.service.AskToExpertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/doubt")
public class AskToExpertController {

    private AskToExpertService askToExpertService;

    @PostMapping("/ask")
    public ResponseEntity<ApiResponse> askDoubt(@Valid @RequestBody AskToExpert diseaseDetection) {
        return askToExpertService.askDoubt(diseaseDetection);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteDoubt(@RequestBody AskToExpert diseaseDetection) throws ExecutionException, InterruptedException {
        return askToExpertService.deleteDoubt(diseaseDetection);
    }

}
