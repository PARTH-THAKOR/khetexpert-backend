// ExpertOTPVerificationController Class

package dev.khetexpert.inc.controller;

import lombok.AllArgsConstructor;
import dev.khetexpert.inc.objects.Admin;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.service.ExpertOTPService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Admin
@RestController
@AllArgsConstructor
@RequestMapping("/expert/login")
public class ExpertOTPVerificationController {

    private ExpertOTPService expertOTPService;

    @Admin
    @PatchMapping("/OTPv/register")
    public ResponseEntity<ApiResponse> registerOTPAuthentication(@RequestHeader("Email") String email) {
        return expertOTPService.otpService(email, "KhetExpert Register Verification");
    }

    @Admin
    @PatchMapping("/OTPv/forgot")
    public ResponseEntity<ApiResponse> forgotOTPAuthentication(@RequestHeader("Email") String email) {
        return expertOTPService.otpService(email, "KhetExpert Forgot Password Verification");
    }

    @Admin
    @PatchMapping("/OTPv/verify")
    public ResponseEntity<ApiResponse> OTPVerification(@RequestHeader("Email") String email, @RequestHeader("OTP") String otp) {
        return expertOTPService.otpVerification(email, otp);
    }

}
