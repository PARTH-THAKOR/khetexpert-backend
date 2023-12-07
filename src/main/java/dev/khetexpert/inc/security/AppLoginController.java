// AppLoginController Class

package dev.khetexpert.inc.security;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import dev.khetexpert.inc.objects.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class AppLoginController {

    private AppLoginService appLoginService;

    @PatchMapping
    public ResponseEntity<ApiResponse> appLogin(@Valid @RequestBody AppLoginEntity appLoginEntity) {
        if (appLoginEntity.getVerificationCode() != null) {
            return appLoginService.verification(appLoginEntity);
        } else {
            return appLoginService.sendEmailOTP(appLoginEntity);
        }
    }

}
