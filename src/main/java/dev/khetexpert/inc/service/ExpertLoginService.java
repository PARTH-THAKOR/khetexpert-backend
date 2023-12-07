// ExpertService Class

package dev.khetexpert.inc.service;

import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.Expert;
import dev.khetexpert.inc.exception.KhetExpertError;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.repository.ExpertRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ExpertLoginService {

    private ExpertRepository expertRepository;

    public ResponseEntity<ApiResponse> loginExpert(String email, String password) {
        Expert expert = expertRepository.findByExpertEmailId(email);
        if (expert != null) {
            if (Objects.equals(password, expert.getPassword())) {
                return new ResponseEntity<>(ApiResponse.builder().success(true).message(expert.getExpertId()).build(), HttpStatus.OK);
            } else {
                throw new KhetExpertError("Password Not Matched");
            }
        } else {
            throw new KhetExpertError("Expert Not Found");
        }
    }

    public ResponseEntity<ApiResponse> forgotPassword(String email, String password) {
        Expert expert = expertRepository.findByExpertEmailId(email);
        if (expert != null) {
            expert.setPassword(password);
            expertRepository.save(expert);
            return new ResponseEntity<>(ApiResponse.builder().success(true).message("password updated Successfully").build(), HttpStatus.CREATED);
        } else {
            throw new KhetExpertError("Expert Not Found");
        }
    }

    public ResponseEntity<ApiResponse> registerExpert(Expert expert) {
        Expert expert2 = expertRepository.findByExpertEmailId(expert.getExpertEmailId());
        if (expert2 == null) {
            expertRepository.save(expert);
            return new ResponseEntity<>(ApiResponse.builder().success(true).message("Expert Registered").build(), HttpStatus.CREATED);
        } else {
            throw new KhetExpertError("Expert already exist");
        }
    }

}
