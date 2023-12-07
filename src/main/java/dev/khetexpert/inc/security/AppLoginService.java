// AppLoginService Class

package dev.khetexpert.inc.security;

import lombok.AllArgsConstructor;
import dev.khetexpert.inc.constants.Constants;
import dev.khetexpert.inc.exception.KhetExpertError;
import dev.khetexpert.inc.function.Functions;
import dev.khetexpert.inc.objects.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AppLoginService {

    private JavaMailSender javaMailSender;
    private AppLoginRepository appLoginRepository;

    public ResponseEntity<ApiResponse> sendEmailOTP(AppLoginEntity appLoginEntity) {
        AppLoginEntity appLoginEntity1 = appLoginRepository.findByEmail(appLoginEntity.getEmail());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String otp = Functions.randomOTPGenerator();
        mailMessage.setFrom("no.reply.service.roundrobin@gmail.com");
        mailMessage.setTo(appLoginEntity.getEmail());
        mailMessage.setSubject("KhetExpert APIs Access Verification");
        mailMessage.setText(Functions.mailBody(otp));
        javaMailSender.send(mailMessage);
        if (appLoginEntity1 != null) {
            appLoginEntity1.setVerificationCode(otp);
            appLoginRepository.save(appLoginEntity1);
        } else {
            appLoginEntity.setVerificationCode(otp);
            appLoginRepository.save(appLoginEntity);
        }
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("Verification Code Sent Successfully").build(), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> verification(AppLoginEntity appLoginEntity) {
        AppLoginEntity appLoginEntity1 = appLoginRepository.findByEmail(appLoginEntity.getEmail());
        if (appLoginEntity1 != null) {
            if (Objects.equals(appLoginEntity.getVerificationCode(), appLoginEntity1.getVerificationCode())) {
                return new ResponseEntity<>(ApiResponse.builder().success(true).message("ACCESS GRANTED : [TOKEN] -> " + Constants.APP_AUTH_TOKEN).build(), HttpStatus.ACCEPTED);
            } else {
                throw new KhetExpertError("ACCESS DENIED : Verification Code not matched");
            }
        } else {
            throw new KhetExpertError("ACCESS DENIED : Verification Code not exist");
        }
    }

}
