// ExpertOTPService Class

package dev.khetexpert.inc.service;

import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.ExpertOTPs;
import dev.khetexpert.inc.exception.KhetExpertError;
import dev.khetexpert.inc.function.Functions;
import dev.khetexpert.inc.objects.Admin;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.repository.ExpertOTPRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ExpertOTPService {

    private JavaMailSender javaMailSender;
    private ExpertOTPRepository expertOTPRepository;

    @Admin
    public ResponseEntity<ApiResponse> otpService(String email, String subject) {
        ExpertOTPs expertOTPs = expertOTPRepository.findByEmail(email);
        if (expertOTPs == null) {
            ExpertOTPs expertOTPs1 = new ExpertOTPs();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            String otp = Functions.randomOTPGenerator();
            mailMessage.setFrom("no.reply.service.roundrobin@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(Functions.mailBody(otp));
            javaMailSender.send(mailMessage);
            expertOTPs1.setOTP(otp);
            expertOTPs1.setEmail(email);
            expertOTPRepository.save(expertOTPs1);
            return new ResponseEntity<>(ApiResponse.builder().success(true).message("OTP send Successfully").build(), HttpStatus.CREATED);
        } else {
            throw new KhetExpertError("OTP already sent");
        }
    }

    @Admin
    public ResponseEntity<ApiResponse> otpVerification(String email, String otp) {
        ExpertOTPs otpClass = expertOTPRepository.findByEmail(email);
        if (otpClass != null) {
            if (Objects.equals(otp, otpClass.getOTP())) {
                return new ResponseEntity<>(ApiResponse.builder().success(true).message("Email Verified").build(), HttpStatus.ACCEPTED);
            } else {
                throw new KhetExpertError("OTP not matched");
            }
        } else {
            throw new KhetExpertError("OTP not exist");
        }
    }

}
