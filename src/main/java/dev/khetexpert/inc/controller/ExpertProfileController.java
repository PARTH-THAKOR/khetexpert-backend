// ExpertProfileController Class

package dev.khetexpert.inc.controller;

import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.Expert;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.service.ExpertProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/expert/profile")
public class ExpertProfileController {

    private ExpertProfileService profileService;

    @GetMapping("/get")
    public ResponseEntity<Expert> getExpert(@RequestHeader("expertId") String expertId) {
        return profileService.getExpert(expertId);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> forgotExpert(@RequestHeader("Email") String email, @RequestBody Expert expert) {
        return profileService.updateExpert(email, expert);
    }

}
