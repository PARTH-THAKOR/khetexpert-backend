// DoubtAnswerController Class

package dev.khetexpert.inc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.DoubtAnswers;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.service.DoubtAnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/doubt/answer")
public class DoubtAnswerController {

    private DoubtAnswerService doubtAnswerService;

    @PostMapping("/write")
    public ResponseEntity<ApiResponse> writeDoubtAnswer(@Valid @RequestBody DoubtAnswers doubtAnswers, @RequestHeader("docId") String docId, @RequestHeader("state") String state) throws ExecutionException, InterruptedException {
        return doubtAnswerService.writeDoubtAnswer(docId, state, doubtAnswers);
    }

}
