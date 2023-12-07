// DiseaseAnswerController Class

package dev.khetexpert.inc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.DiseaseAnswers;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.service.DiseaseAnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/disease/answer")
public class DiseaseAnswerController {

    private DiseaseAnswerService diseaseAnswerService;

    @PostMapping("/write")
    public ResponseEntity<ApiResponse> writeDiseaseAnswer(@Valid @RequestBody DiseaseAnswers diseaseAnswers, @RequestHeader("docId") String docId, @RequestHeader("state") String state) throws ExecutionException, InterruptedException {
        return diseaseAnswerService.writeDiseaseAnswer(docId, state, diseaseAnswers);
    }

}
