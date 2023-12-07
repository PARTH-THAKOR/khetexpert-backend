// ExpertLoginController Class

package dev.khetexpert.inc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.Expert;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.service.ExpertLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/expert/login")
public class ExpertLoginController {

    private ExpertLoginService expertLoginService;

    @GetMapping("/email")
    public ResponseEntity<ApiResponse> loginExpert(@RequestHeader("ExpertEmail") String expertEmail, @RequestHeader("Password") String password) {
        return expertLoginService.loginExpert(expertEmail, password);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerExpert(@Valid @RequestBody Expert expert) {
        return expertLoginService.registerExpert(expert);
    }

    @PutMapping("/forgot")
    public ResponseEntity<ApiResponse> forgotExpert(@RequestHeader("ExpertEmail") String expertEmail, @RequestHeader("NewPassword") String password) {
        return expertLoginService.forgotPassword(expertEmail, password);
    }

}
